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

public partial class EditCustomer : System.Web.UI.Page
{
    String custName;
    protected void Page_Load(object sender, EventArgs e)
    {
        CheckLogin.isLoggedIn(Session, Response);
        labelMsg.Text = "";
    }
    protected void btnFirst_Click(object sender, EventArgs e)
    {
        dvCustomer.PageIndex = 0;
    }
    protected void btnPrevious_Click(object sender, EventArgs e)
    {
        if (dvCustomer.PageIndex > 0) dvCustomer.PageIndex -= 1;
    }
    protected void btnNext_Click(object sender, EventArgs e)
    {
        if (dvCustomer.PageIndex < dvCustomer.PageCount - 1) dvCustomer.PageIndex += 1;
    }
    protected void btnLast_Click(object sender, EventArgs e)
    {
        dvCustomer.PageIndex = dvCustomer.PageCount - 1;
    }
    protected void dvCustomer_DataBound(object sender, EventArgs e)
    {
        lblPosition.Text = (dvCustomer.PageIndex + 1).ToString() + " of " + dvCustomer.PageCount.ToString();
    }

    protected void dvCustomer_ItemDeleting(object sender, DetailsViewDeleteEventArgs e)
    {
        Label lbl = ((Label)dvCustomer.FindControl("Label2"));
        custName = lbl.Text;
    }
    protected void dvCustomer_ItemInserting(object sender, DetailsViewInsertEventArgs e)
    {
        TextBox txt = ((TextBox)dvCustomer.FindControl("TextBox1"));
        custName = txt.Text;
    }
    protected void dvCustomer_ItemUpdating(object sender, DetailsViewUpdateEventArgs e)
    {
        TextBox txt = ((TextBox)dvCustomer.FindControl("TextBox1"));
        custName = txt.Text;
    }
    protected void dvCustomer_ItemInserted(object sender, DetailsViewInsertedEventArgs e)
    {
        if (e.Exception != null)
        {
            labelMsg.Text = "Unable to insert " + custName + " - make sure you entered valid data";
            e.ExceptionHandled = true;
        }
        else
        {
            labelMsg.Text = "Inserted " + custName;
        }
    }
    protected void dvCustomer_ItemUpdated(object sender, DetailsViewUpdatedEventArgs e)
    {
        if (e.Exception != null)
        {
            labelMsg.Text = "Unable to update " + custName + " - make sure you entered valid data";
            e.ExceptionHandled = true;
        }
        else
        {
            labelMsg.Text = "Updated " + custName;
        }
    }
    protected void dvCustomer_ItemDeleted(object sender, DetailsViewDeletedEventArgs e)
    {
        if (e.Exception != null)
        {
            labelMsg.Text = "Unable to delete " + custName + " - it is linked to other database entries";
            e.ExceptionHandled = true;
        }
        else
        {
            labelMsg.Text = "Deleted " + custName;
        }
    }
    protected void dvCustomer_PageIndexChanging(object sender, DetailsViewPageEventArgs e)
    {

    }
}
