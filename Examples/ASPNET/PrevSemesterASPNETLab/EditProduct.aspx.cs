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

public partial class EditProduct : System.Web.UI.Page
{
    int rowIndex;
    protected void Page_Load(object sender, EventArgs e)
    {
        labelMsg.Text = "";
        CheckLogin.isLoggedIn(Session, Response);
    }
    protected void gvProduct_RowDeleted(object sender, GridViewDeletedEventArgs e)
    {
        GridViewRow row = gvProduct.Rows[rowIndex];
        Label desc = (Label)row.FindControl("Label1");
        if (e.Exception != null)
        {
            labelMsg.Text = "Unable to delete " + desc.Text + " - it is linked to other database entries";
            e.ExceptionHandled = true;
        }
        else
        {
            labelMsg.Text = "Deleted " + desc.Text;
        }
    }
    protected void gvProduct_RowDeleting(object sender, GridViewDeleteEventArgs e)
    {
        rowIndex = e.RowIndex;
    }    
    protected void gvProduct_RowUpdated(object sender, GridViewUpdatedEventArgs e)
    {
        GridViewRow row = gvProduct.Rows[rowIndex];
        TextBox desc = (TextBox)row.FindControl("TextBox1");
        if (e.Exception != null)
        {
            labelMsg.Text = "Unable to update " + desc.Text + " - make sure you entered valid data";
            e.ExceptionHandled = true;
        }
        else
        {
            labelMsg.Text = "Updated " + desc.Text;
        }
    }
    protected void gvProduct_RowUpdating(object sender, GridViewUpdateEventArgs e)
    {
        rowIndex = e.RowIndex;
    }
    //protected void gvProduct_RowDataBound(object sender, GridViewRowEventArgs e)
    //{
    //    // I'm now doing this by adding an OnClientClick to the <asp:LinkButton elements in the aspx file (must convert to a template to do this)
    //    // however the following approach doesn't require conversion to a template and has the additional advantage of displaying the name of what
    //    // is updated or deleted
    //    if (e.Row.RowType == DataControlRowType.DataRow)
    //    {
    //        for (int i = 0; i < e.Row.Cells[0].Controls.Count; i++)
    //        {
    //            if(e.Row.Cells[0].Controls[i] is LinkButton) {
    //                LinkButton btn = (LinkButton)e.Row.Cells[0].Controls[i];
    //                if (btn.Text == "Update")
    //                {
    //                    System.Data.DataRowView row = (System.Data.DataRowView)e.Row.DataItem;
    //                    btn.OnClientClick = "return confirm('Are you sure you want to update " + row[1].ToString() + "?')";
    //                }
    //                else if (btn.Text == "Delete")
    //                {
    //                    System.Data.DataRowView row = (System.Data.DataRowView)e.Row.DataItem;
    //                    btn.OnClientClick = "return confirm('Are you sure you want to delete " + row[1].ToString() + "?')";
    //                }
    //            }
    //        }
    //    }
    //}
}
