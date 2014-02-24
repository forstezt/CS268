using System;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;
using SC = System.Collections;
using SD = System.Data;
using SDS = System.Data.SqlClient;
using ST = System.Type;

public partial class NoWizardGridView : System.Web.UI.Page {
    protected SD.DataView       dv = new SD.DataView();
    private  SDS.SqlDataAdapter da = new SDS.SqlDataAdapter();
    private   SD.DataSet        ds = new SD.DataSet();
    private  SDS.SqlConnection  cn = new SDS.SqlConnection();

    protected void Page_Load(object sender, EventArgs e) {
        cn.ConnectionString = System.Configuration.ConfigurationManager.ConnectionStrings["studentConnectionString"].ConnectionString;
        da.SelectCommand = new SDS.SqlCommand();
        da.SelectCommand.Connection = cn;
        if (!IsPostBack) {  // only need to do this the first time the page is accessed
            SD.DataTable dt = new SD.DataTable("table");
            SD.DataRow dr;
            dt.Columns.Add(new SD.DataColumn("StudentID", ST.GetType("System.Int32")));
            dt.Columns.Add(new SD.DataColumn("StudentLastName", ST.GetType("System.String")));
            dt.Columns.Add(new SD.DataColumn("StudentFirstName", ST.GetType("System.String")));
            dt.Columns.Add(new SD.DataColumn("StudentMI", ST.GetType("System.String")));

            da.SelectCommand.CommandText =
                "SELECT StudentID, StudentLastName, StudentFirstName, StudentMI " +
                "FROM UniversityStudent " +
                "ORDER BY StudentLastName, StudentFirstName";
            da.Fill(ds, "students");

            foreach (SD.DataRow row in ds.Tables["students"].Rows) {
                dr = dt.NewRow();
                dr[0] = row["StudentID"];
                dr[1] = row["StudentLastName"];
                dr[2] = row["StudentFirstName"];
                dr[3] = row["StudentMI"];
                dt.Rows.Add(dr);
            }
            dv.Table = dt;
            gvStudent.DataSource = dv;
            gvStudent.DataBind();

            Session["gvStudentTable"] = dv;
        }
    }
    protected void gvStudent_RowEditing(object sender, GridViewEditEventArgs e) {
        dv = (SD.DataView)Session["gvStudentTable"]; // saved this in the ddlAdvisor_SelectedIndexChanged event handler
        gvStudent.DataSource = dv;
        gvStudent.EditIndex = e.NewEditIndex;
        gvStudent.DataBind();
    }
    protected void gvStudent_RowUpdating(object sender, GridViewUpdateEventArgs e) {
        GridViewRow row = gvStudent.Rows[e.RowIndex];
        TextBox last = (TextBox)row.FindControl("txtLast");
        TextBox first = (TextBox)row.FindControl("txtFirst");
        TextBox mi = (TextBox)row.FindControl("txtMI");

        SD.DataTable dt = ((SD.DataView)Session["gvStudentTable"]).Table;
        dt.Rows[row.DataItemIndex]["StudentLastName"] = last.Text;
        dt.Rows[row.DataItemIndex]["StudentFirstName"] = first.Text;
        dt.Rows[row.DataItemIndex]["StudentMI"] = mi.Text;

        gvStudent.EditIndex = -1;
        gvStudent.DataSource = dt;
        gvStudent.DataBind();

        // push the update through to the database?
        updateStudent(last.Text, first.Text, mi.Text, Convert.ToInt32(dt.Rows[row.DataItemIndex]["StudentID"]));
    }
    private void updateStudent(string last, string first, string mi, Int32 studentid) {
        SDS.SqlCommand sqlCommand = new SDS.SqlCommand();
        sqlCommand.Connection = cn;
        sqlCommand.CommandText = "UPDATE universitystudent SET StudentLastName = @StudentLastName, " +
                                                              "StudentFirstName = @StudentFirstName, " +
                                                              "StudentMI = @StudentMI " +
                                 "WHERE StudentID = " + studentid.ToString();
        sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("StudentLastName", last));
        sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("StudentFirstName", first));
        sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("StudentMI", mi));

        try {
            cn.Open();
            sqlCommand.ExecuteNonQuery();
        }
        catch (Exception ex) {
            System.Console.WriteLine(ex.Message);
        }
        finally {
            cn.Close();
        }
    }
    protected void gvStudent_RowCancelingEdit(object sender, GridViewCancelEditEventArgs e) {
        gvStudent.EditIndex = -1;
        gvStudent.DataSource = ((SD.DataView)Session["gvStudentTable"]).Table; // saved this in the ddlAdvisor_SelectedIndexChanged event handler
        gvStudent.DataBind();
    }
}
