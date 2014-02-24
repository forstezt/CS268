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

public partial class AddCourse : System.Web.UI.Page
{
    private int intDepartmentID = 0;
    protected void Page_Load(object sender, EventArgs e)
    {
        try {
            lblDepartment.Text = Request.Cookies["DeptName"].Value;
            intDepartmentID = Convert.ToInt32(Request.Cookies["DeptID"].Value);
        }
        catch {}
    }
    protected void btnSubmit_Click(object sender, EventArgs e)
    {
        if (intDepartmentID == 0) {
            lblMsg.Text = "AddCourse can't be directly displayed.  It must be called from MasterDetail.";
        }
        else {
            SD.SqlClient.SqlConnection cn = new SD.SqlClient.SqlConnection();
            SD.SqlClient.SqlCommand sqlCommand = new SD.SqlClient.SqlCommand();

            cn.ConnectionString = SCM.ConnectionStrings["studentConnectionString"].ConnectionString;

            sqlCommand.Connection = cn;
            try {
                sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("CourseName", txtName.Text));
                sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("CourseTitle", txtTitle.Text));
                sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("CourseCredits", txtCredit.Text));
                sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("DeptID", intDepartmentID));
                sqlCommand.CommandText = "INSERT INTO UniversityCourse (CourseName, CourseTitle, CourseCredits, DeptID) " +
                                         "VALUES(@CourseName, @CourseTitle, @CourseCredits, @DeptID)";
                cn.Open();
                sqlCommand.ExecuteNonQuery();
                lblMsg.Text = "Added " + txtName.Text;
                txtName.Text = "";
                txtTitle.Text = "";
                txtCredit.Text = "";
                txtName.Focus();
            }
            catch (Exception ex) {
                lblMsg.Text = ex.Message;
            }
            finally {
                cn.Close();
            }
        }
    }
}
