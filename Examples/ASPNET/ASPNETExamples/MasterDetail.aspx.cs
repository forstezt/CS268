using System;
using System.Collections;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;
using SD = System.Data;
using SCM = System.Configuration.ConfigurationManager;

public partial class MasterDetail : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void btnAddCourse_Click(object sender, EventArgs e)
    {
        Response.Cookies["DeptID"].Value = ddlDepartment.Text;
		Response.Cookies["DeptName"].Value = ddlDepartment.SelectedItem.Text;
		Response.Cookies["SelectedIndex"].Value = ddlDepartment.SelectedIndex.ToString();
        Server.Transfer("AddCourse.aspx");
    }

    protected void gvCourse_RowDeleting(object sender, GridViewDeleteEventArgs e)
    {
        e.Cancel = true;
        SD.SqlClient.SqlConnection cn = new SD.SqlClient.SqlConnection();
        SD.SqlClient.SqlCommand sqlCommand = new SD.SqlClient.SqlCommand();

        cn.ConnectionString = SCM.ConnectionStrings["studentConnectionString"].ConnectionString;

        sqlCommand.Connection = cn;
        try {
            sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("CourseID", e.Keys["CourseID"]));
            sqlCommand.CommandText = "DELETE FROM UniversityCourse WHERE CourseID = @CourseID";
            cn.Open();
            sqlCommand.ExecuteNonQuery();
        } catch {
            lblMsg.Text = "Delete failed because " + e.Values["CourseName"].ToString() + " has sections assigned to it";
            lblMsg.Visible = true;
        } finally {
            cn.Close();
        }
        gvCourse.DataBind();
    }
}
