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

public partial class MasterDetailwPhoto : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e) {

    }

    protected void gvStudent_RowCommand(object sender, GridViewCommandEventArgs e) {
        displayImage(Convert.ToInt32(e.CommandArgument));
    }

    private void displayImage(int index) {
        try {
            string filename = gvStudent.DataKeys[index].Values[1].ToString();
            string imagePath = AppDomain.CurrentDomain.BaseDirectory + "images/" + filename;
            imgStudent.ImageUrl = "../Images/" + filename;
            if (System.IO.File.Exists(imagePath)) {
                imgStudent.Visible = true;
                lblMsg.Visible = false;
            } else {
                imgStudent.Visible = false;
                lblMsg.Visible = true;
            }
        } catch {
            imgStudent.Visible = false;
            lblMsg.Visible = true;
        }
    }

    protected void ddlAdvisor_SelectedIndexChanged(object sender, EventArgs e) {
        imgStudent.Visible = false;
        lblMsg.Visible = false;
        gvStudent.EditIndex = -1;
    }
}
