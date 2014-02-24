using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;

/// <summary>
/// Summary description for CheckLogin
/// </summary>
public class CheckLogin
{
	public CheckLogin()
	{
	}
    public static void isLoggedIn(System.Web.SessionState.HttpSessionState session,
                              System.Web.HttpResponse response)
    {
        if (session["cust_id"] == null || (long)session["cust_id"] <= 0)
        {
            response.Redirect("Login.aspx");
        }
    }

}
