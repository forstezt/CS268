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

public partial class RedirectVsTransfer : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void Redirect_Click(object sender, EventArgs e) {
        // Sends message to client's browser asking it to in turn send a request back
        // for the desired page
        Response.Redirect("DetailsView.aspx");
    }
    protected void Transfer_Click(object sender, EventArgs e) {
        // Sends the content of the desired page to the client's browser without the
        // extra overhead of asking the browser to request the page (must be to an aspx page
        // on the same server - use Redirect if to a non aspx page or to a different server)

        // Notice in the brower address bar it thinks it is displaying RedirectVsTransfer
        // when in reality it is showing DetailsView.aspx

        // Also notice TreeView navigation control thinks the page is still RedirectVsTransfer (not good)

        // open the MasterDetail.aspx page and look at the code in the Add a Course button 
        // Server.Transfer is used - why is it okay to use in Add a Course??
        Server.Transfer("DetailsView.aspx");
    }
}
