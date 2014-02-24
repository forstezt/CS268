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

public partial class AddProduct : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        CheckLogin.isLoggedIn(Session, Response);
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        SD.SqlClient.SqlConnection cn = new SD.SqlClient.SqlConnection();
        SD.SqlClient.SqlCommand sqlCommand = new SD.SqlClient.SqlCommand();
        cn.ConnectionString = SCM.ConnectionStrings["CandyConnectionString"].ConnectionString;
        sqlCommand.Connection = cn;
        sqlCommand.CommandText = "INSERT INTO candy_product VALUES (@prod_desc, @prod_cost, @prod_price)";
        sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("prod_desc", description.Text));
        sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("prod_cost", cost.Text));
        sqlCommand.Parameters.Add(new SD.SqlClient.SqlParameter("prod_price", price.Text));
        try
        {
            cn.Open();
            sqlCommand.ExecuteNonQuery();
            labelMsg.Text = "Added " + description.Text;
        }
        catch (Exception ex)
        {
            labelMsg.Text = ex.Message;
        }
        finally
        {
            cn.Close();
        }
    }
}
