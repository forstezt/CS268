<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeFile="NoWizardGridView.aspx.cs" Inherits="NoWizardGridView" Title="Untitled Page" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <asp:GridView ID="gvStudent" runat="server" AutoGenerateColumns="False" 
        DataKeyNames="StudentID" onrowcancelingedit="gvStudent_RowCancelingEdit" 
        onrowediting="gvStudent_RowEditing" onrowupdating="gvStudent_RowUpdating" 
        CellPadding="4" ForeColor="#333333" GridLines="Both" >
        <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
        <RowStyle BackColor="#EFF3FB" />
        <Columns>
            <asp:CommandField ShowEditButton="True" />
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
        </Columns>
        <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
        <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
        <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
        <EditRowStyle BackColor="#2461BF" />
        <AlternatingRowStyle BackColor="White" />
    </asp:GridView>
</asp:Content>

