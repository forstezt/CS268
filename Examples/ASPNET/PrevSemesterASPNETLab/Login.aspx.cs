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

public partial class Login : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        Session["cust_id"] = null;
    }
    protected void submit_Click(object sender, EventArgs e)
    {
        SD.SqlClient.SqlConnection cn = new SD.SqlClient.SqlConnection();
        SD.SqlClient.SqlCommand sqlCommand = new SD.SqlClient.SqlCommand();
        cn.ConnectionString =
        System.Configuration.ConfigurationManager.ConnectionStrings["CandyConnectionString"].ConnectionString;
        sqlCommand.Connection = cn;
        sqlCommand.CommandText = "SELECT cust_id FROM candy_customer WHERE  username=@username AND password=@password";
        sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("username", username.Text));
        sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("password", password.Text));
        try
        {
            cn.Open();
            long cust_id = System.Convert.ToInt64(sqlCommand.ExecuteScalar());
            if (cust_id > 0)
            {
                Session["cust_id"] = cust_id;
                Response.Redirect("DisplayProducts.aspx");
                loginMsg.Text = "";
            }
            else
            {
                 loginMsg.Text = "Invalid Login";
            }
        }
        catch (Exception ex)
        {
            loginMsg.Text = ex.Message;
        }
        finally
        {
            cn.Close();
        }
    }
}
