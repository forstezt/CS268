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


public partial class MasterDetailAdvanced : System.Web.UI.Page {
    protected SD.DataView        dv = new SD.DataView();
    private  SDS.SqlDataAdapter  da = new SDS.SqlDataAdapter();
    private   SD.DataSet         ds = new SD.DataSet();
    private  SDS.SqlConnection   cn = new SDS.SqlConnection();

    protected void Page_Load(object sender, EventArgs e) {
        cn.ConnectionString = System.Configuration.ConfigurationManager.ConnectionStrings["studentConnectionString"].ConnectionString;
        da.SelectCommand = new SDS.SqlCommand();
        da.SelectCommand.Connection = cn;

        if (!IsPostBack) {  // only need to this the first time the page is accessed
            da.SelectCommand.CommandText =
                "SELECT InstructorID, InstructorLastName + ', ' + InstructorFirstName AS AdvisorName, InstructorLastName, InstructorFirstName " +
                "FROM UniversityInstructor " +
                "WHERE InstructorID IN (SELECT DISTINCT AdvisorID FROM UniversityStudent) " +
                "ORDER BY AdvisorName";
            cn.Open();
            da.Fill(ds, "advisors");
            cn.Close();
            Session["advisors"] = ds.Tables["advisors"];

            ddlAdvisor.DataSource = ds.Tables["advisors"];
            ddlAdvisor.DataTextField = "AdvisorName";
            ddlAdvisor.DataValueField = "InstructorID";
            ddlAdvisor.DataBind();
            ddlAdvisor.Items.Insert(0, new ListItem("Select an Advisor"));
            ddlAdvisor.Items.Insert(1, new ListItem("Display all Advisors and Advisees"));

            ddlAdvisor.SelectedIndex = 0;
            gvStudent.DataSource = null;
        }
    }
    protected void ddlAdvisor_SelectedIndexChanged(object sender, EventArgs e) {
        imgStudent.Visible = false;
        lblMsg.Visible = false;
        gvStudent.Visible = false;
        if (ddlAdvisor.SelectedIndex > 0) {
            if (ddlAdvisor.SelectedIndex == 1) {
                gvStudent.DataSource = CreateDataSourceAll();
            }
            else {
                gvStudent.DataSource = CreateDataSource(ddlAdvisor.SelectedIndex, ddlAdvisor.SelectedItem.ToString());
            }
            gvStudent.Visible = true;
            Session["gvStudentTable"] = gvStudent.DataSource;
            gvStudent.DataBind();
            gvStudent.SelectedIndex = 0;
            displayImage(0);
        }
    }
    private SC.ICollection CreateDataSourceAll() {
        SD.DataTable dt = new SD.DataTable("table");
        SD.DataRow dr;
        dt.Columns.Add(new SD.DataColumn("StudentID", ST.GetType("System.Int32")));
        dt.Columns.Add(new SD.DataColumn("StudentLastName", ST.GetType("System.String")));
        dt.Columns.Add(new SD.DataColumn("StudentFirstName", ST.GetType("System.String")));
        dt.Columns.Add(new SD.DataColumn("StudentMI", ST.GetType("System.String")));
        dt.Columns.Add(new SD.DataColumn("StudentImagefile", ST.GetType("System.String")));
        dt.Columns.Add(new SD.DataColumn("AdvisorName", ST.GetType("System.String")));

        da.SelectCommand.CommandText =
            "SELECT StudentID, StudentLastName, StudentFirstName, StudentMI, StudentImagefile, " +
                   "InstructorLastName + ', ' + InstructorFirstName AdvisorName " +
            "FROM UniversityStudent INNER JOIN UniversityInstructor " +
            "ON UniversityStudent.AdvisorID = UniversityInstructor.InstructorID " +
            "ORDER BY AdvisorName, StudentLastName, StudentFirstName";
        da.Fill(ds, "students");

        foreach (SD.DataRow row in ds.Tables["students"].Rows) {
            dr = dt.NewRow();
            dr[0] = row["StudentID"];
            dr[1] = row["StudentLastName"];
            dr[2] = row["StudentFirstName"];
            dr[3] = row["StudentMI"];
            dr[4] = row["StudentImagefile"];
            dr[5] = row["AdvisorName"];
            dt.Rows.Add(dr);
        }
        dv.Table = dt;
        return dv;
    }
    private SC.ICollection CreateDataSource(Int32 selectedIndex, String advisor) {
        SD.DataTable dt = new SD.DataTable("table");
        SD.DataRow   dr;
        dt.Columns.Add(new SD.DataColumn("StudentID", ST.GetType("System.Int32")));
        dt.Columns.Add(new SD.DataColumn("StudentLastName", ST.GetType("System.String")));
        dt.Columns.Add(new SD.DataColumn("StudentFirstName", ST.GetType("System.String")));
        dt.Columns.Add(new SD.DataColumn("StudentMI", ST.GetType("System.String")));
        dt.Columns.Add(new SD.DataColumn("StudentImagefile", ST.GetType("System.String")));
        dt.Columns.Add(new SD.DataColumn("AdvisorName", ST.GetType("System.String")));

        SD.DataTable dta = (SD.DataTable)Session["advisors"];
        Int32 advisorid = Convert.ToInt32(dta.Rows[selectedIndex - 2][0]);

        da.SelectCommand.CommandText =
            "SELECT StudentID, StudentLastName, StudentFirstName, StudentMI, StudentImagefile " +
            "FROM UniversityStudent " +
            "WHERE AdvisorID = " + advisorid.ToString() + " " +
            "ORDER BY StudentLastName, StudentFirstName";
        da.Fill(ds, "students");

        // just show last name for advisor
        advisor = advisor.Substring(0, advisor.IndexOf(","));
        foreach (SD.DataRow row in ds.Tables["students"].Rows) {
            dr = dt.NewRow();
            dr[0] = row["StudentID"];
            dr[1] = row["StudentLastName"];
            dr[2] = row["StudentFirstName"];
            dr[3] = row["StudentMI"];
            dr[4] = row["StudentImagefile"];
            dr[5] = advisor;
            dt.Rows.Add(dr);
        }
        dv.Table = dt;
        return dv;
    }
    protected void gvStudent_RowCommand(object sender, GridViewCommandEventArgs e) {
        displayImage(Convert.ToInt32(e.CommandArgument));
    }
    private void displayImage(int index) {
        try {
            SD.DataTable dt = ((SD.DataView)Session["gvStudentTable"]).Table; // saved this in the ddlAdvisor_SelectedIndexChanged event handler
            string filename = dt.Rows[index]["StudentImagefile"].ToString();
            string imagePath = AppDomain.CurrentDomain.BaseDirectory + "images/" + filename;
            imgStudent.ImageUrl = "../Images/" + filename;
            if (System.IO.File.Exists(imagePath)) {
                imgStudent.Visible = true;
                lblMsg.Text = dt.Rows[index]["StudentFirstName"].ToString() + " " + dt.Rows[index]["StudentLastName"].ToString() + "<br />";
                lblMsg.Visible = true;
            }
            else {
                imgStudent.Visible = false;
                lblMsg.Text = "No photo is available";
                lblMsg.Visible = true;
            }
        }
        catch {
            imgStudent.Visible = false;
            lblMsg.Text = "No photo is available";
            lblMsg.Visible = true;
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

        SD.DataTable dt = ((SD.DataView)Session["gvStudentTable"]).Table; // saved this in the ddlAdvisor_SelectedIndexChanged event handler
        dt.Rows[row.DataItemIndex]["StudentLastName"] = last.Text;
        dt.Rows[row.DataItemIndex]["StudentFirstName"] = first.Text;
        dt.Rows[row.DataItemIndex]["StudentMI"] = mi.Text;

        gvStudent.EditIndex = -1;
        gvStudent.DataSource = dt;
        gvStudent.DataBind();

        // now... do you want to push the update through to the database?
        updateStudent(last.Text, first.Text, mi.Text, Convert.ToInt32(dt.Rows[row.DataItemIndex]["StudentID"]));

        lblMsg.Text = first.Text + " " + last.Text + "<br />";
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
