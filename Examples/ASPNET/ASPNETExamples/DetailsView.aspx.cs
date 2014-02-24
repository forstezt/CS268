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

public partial class DetailsView : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void btnFirst_Click(object sender, EventArgs e)
    {
        dvDepartment.PageIndex = 0;
    }
    protected void btnPrevious_Click(object sender, EventArgs e)
    {
        if (dvDepartment.PageIndex > 0) dvDepartment.PageIndex -= 1;
    }
    protected void btnNext_Click(object sender, EventArgs e)
    {
        if (dvDepartment.PageIndex < dvDepartment.PageCount - 1) dvDepartment.PageIndex += 1;
    }
    protected void btnLast_Click(object sender, EventArgs e)
    {
        dvDepartment.PageIndex = dvDepartment.PageCount - 1;
    }
    protected void dvDepartment_DataBound(object sender, EventArgs e)
    {
        lblPosition.Text = (dvDepartment.PageIndex + 1).ToString() + " of " + dvDepartment.PageCount.ToString();
    }
}
