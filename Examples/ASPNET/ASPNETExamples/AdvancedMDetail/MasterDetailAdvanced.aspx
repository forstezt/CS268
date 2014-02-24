<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeFile="MasterDetailAdvanced.aspx.cs" Inherits="MasterDetailAdvanced" Title="Advanced Master Detail" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <table>
    <tr>
        <td align="right" valign="top"><asp:Label ID="Label1" runat="server" Text="Select an Advisor:"></asp:Label>&nbsp;</td>
        <td align="left" valign="top">
            <asp:DropDownList ID="ddlAdvisor" runat="server" AutoPostBack="True" 
                onselectedindexchanged="ddlAdvisor_SelectedIndexChanged">
            </asp:DropDownList>
        </td>
    </tr>
    <tr>
        <td valign="top" align="center">            
            <asp:Label ID="lblMsg" visible="False" 
                Text="No photo is available" runat="server" 
                ForeColor="#009900"></asp:Label>
            <asp:Image ID="imgStudent" runat="server" Height="119px" Visible="False" /><br />
        </td>
        <td valign="top">
            <asp:GridView ID="gvStudent" runat="server" AutoGenerateColumns="False" 
                DataKeyNames="StudentID" onrowcancelingedit="gvStudent_RowCancelingEdit" 
                onrowediting="gvStudent_RowEditing" 
                OnRowCommand="gvStudent_RowCommand" OnRowUpdating="gvStudent_RowUpdating"
                CellPadding="4" ForeColor="#333333" GridLines="Both" >
                <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                <RowStyle BackColor="#EFF3FB" />
                <Columns>
                    <asp:CommandField ShowEditButton="True" ShowSelectButton="True" />
                    <asp:TemplateField HeaderText="Student Last" >
                        <EditItemTemplate>
                            <asp:TextBox ID="txtLast" runat="server" Text='<%# DataBinder.Eval(Container.DataItem, "StudentLastName") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <%# DataBinder.Eval(Container.DataItem, "StudentLastName") %>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="First" >
                        <EditItemTemplate>
                            <asp:TextBox ID="txtFirst" runat="server" Text='<%# DataBinder.Eval(Container.DataItem, "StudentFirstName") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <%# DataBinder.Eval(Container.DataItem, "StudentFirstName") %>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="MI" >
                        <EditItemTemplate>
                            <asp:TextBox ID="txtMI" runat="server" Text='<%# DataBinder.Eval(Container.DataItem, "StudentMI") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <%# DataBinder.Eval(Container.DataItem, "StudentMI") %>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="" >
                        <EditItemTemplate>
                            &nbsp;
                        </EditItemTemplate>
                        <ItemTemplate>
                             &nbsp;
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Advisor" >
                        <EditItemTemplate>
                            <%# DataBinder.Eval(Container.DataItem, "AdvisorName") %>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <%# DataBinder.Eval(Container.DataItem, "AdvisorName") %>
                        </ItemTemplate>
                    </asp:TemplateField>                    
                </Columns>
                <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
                <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
                <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                <EditRowStyle BackColor="#2461BF" />
                <AlternatingRowStyle BackColor="White" />
            </asp:GridView>
        </td>
    </tr>
    </table>
</asp:Content>

