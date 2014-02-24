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

public partial class StudentImages : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void btnFirst_Click(object sender, EventArgs e) {
        dvStudent.PageIndex = 0;
    }
    protected void btnPrevious_Click(object sender, EventArgs e) {
        if (dvStudent.PageIndex > 0) dvStudent.PageIndex -= 1;
    }
    protected void btnNext_Click(object sender, EventArgs e) {
        if (dvStudent.PageIndex < dvStudent.PageCount - 1) dvStudent.PageIndex += 1;
    }
    protected void btnLast_Click(object sender, EventArgs e) {
        dvStudent.PageIndex = dvStudent.PageCount - 1;
    }

    protected void dvStudent_DataBound(object sender, EventArgs e) {
        lblPosition.Text = (dvStudent.PageIndex + 1).ToString() + " of " + dvStudent.PageCount.ToString();
        imgStudent.ImageUrl = "images/" + dvStudent.DataKey.Values[0].ToString();
    }
}
