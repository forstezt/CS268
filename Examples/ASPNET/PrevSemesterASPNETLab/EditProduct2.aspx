<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="EditProduct2.aspx.cs" Inherits="EditProduct" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Main" Runat="Server">
    <asp:GridView ID="gvProduct" runat="server" AutoGenerateColumns="False" 
        DataKeyNames="prod_id" DataSourceID="SqlDataSource1" 
        onrowdeleted="gvProduct_RowDeleted" onrowupdated="gvProduct_RowUpdated" 
        onrowdeleting="gvProduct_RowDeleting" 
        onrowupdating="gvProduct_RowUpdating" 
        onrowdatabound="gvProduct_RowDataBound">
        <Columns>
            <asp:CommandField ShowEditButton="True" ShowDeleteButton="true" />
            <asp:BoundField DataField="prod_id" HeaderText="ID" InsertVisible="False" 
                ReadOnly="True" SortExpression="prod_id" >
                <ItemStyle HorizontalAlign="Left" />
            </asp:BoundField>
            <asp:TemplateField HeaderText="Description" SortExpression="prod_desc">
                <EditItemTemplate>
                    <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("prod_desc") %>'></asp:TextBox><br />
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator1" ControlToValidate="TextBox1" runat="server" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                </EditItemTemplate>
                <ItemTemplate>
                    <asp:Label ID="Label1" runat="server" Text='<%# Bind("prod_desc") %>'></asp:Label>
                </ItemTemplate>
                <ItemStyle HorizontalAlign="Left" />
            </asp:TemplateField>
            <asp:TemplateField HeaderText="Our Cost" SortExpression="prod_cost">
                <EditItemTemplate>
                    <asp:TextBox ID="TextBox2" runat="server" Text='<%# Bind("prod_cost") %>'></asp:TextBox><br />
                    <asp:RequiredFieldValidator ControlToValidate="TextBox2" ID="RequiredFieldValidator2" 
                                                runat="server" ErrorMessage="*Required" />
                    <asp:RangeValidator ControlToValidate="TextBox2" ID="RangeValidator1" runat="server" 
                                        Type="Double" MaximumValue="1000" MinimumValue=".01" 
                                        ErrorMessage="Range .01 to 1000" />
                </EditItemTemplate>
                <ItemTemplate>
                    <asp:Label ID="Label2" runat="server" Text='<%# Bind("prod_cost", "{0:c}") %>'></asp:Label>
                </ItemTemplate>
                <ItemStyle HorizontalAlign="Right" />
            </asp:TemplateField>
            <asp:TemplateField HeaderText="Retail Price" SortExpression="prod_price">
                <EditItemTemplate>
                    <asp:TextBox ID="TextBox3" runat="server" Text='<%# Bind("prod_price") %>'></asp:TextBox><br />
                    <asp:RequiredFieldValidator ControlToValidate="TextBox3" ID="RequiredFieldValidator3" runat="server" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                    <asp:RangeValidator ControlToValidate="TextBox3" ID="RangeValidator2" runat="server" Type="Double" MaximumValue="1000" MinimumValue=".01" ErrorMessage="Range .01 to 1000"></asp:RangeValidator>
                </EditItemTemplate>
                <ItemTemplate>
                    <asp:Label ID="Label3" runat="server" Text='<%# Bind("prod_price", "{0:c}") %>'></asp:Label>
                </ItemTemplate>
                <ItemStyle HorizontalAlign="Right" />
            </asp:TemplateField>
        </Columns>
    </asp:GridView>
    <asp:Label ID="labelMsg" runat="server"></asp:Label>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:CandyConnectionString %>" 
        DeleteCommand="DELETE FROM [candy_product] WHERE [prod_id] = @prod_id" 
        InsertCommand="INSERT INTO [candy_product] ([prod_desc], [prod_cost], [prod_price]) VALUES (@prod_desc, @prod_cost, @prod_price)" 
        SelectCommand="SELECT [prod_id], [prod_desc], [prod_cost], [prod_price] FROM [candy_product] ORDER BY [prod_desc]" 
        UpdateCommand="UPDATE [candy_product] SET [prod_desc] = @prod_desc, [prod_cost] = @prod_cost, [prod_price] = @prod_price WHERE [prod_id] = @prod_id">
        <DeleteParameters>
            <asp:Parameter Name="prod_id" Type="Int64" />
        </DeleteParameters>
        <UpdateParameters>
            <asp:Parameter Name="prod_desc" Type="String" />
            <asp:Parameter Name="prod_cost" Type="Double" />
            <asp:Parameter Name="prod_price" Type="Double" />
            <asp:Parameter Name="prod_id" Type="Int64" />
        </UpdateParameters>
        <InsertParameters>
            <asp:Parameter Name="prod_desc" Type="String" />
            <asp:Parameter Name="prod_cost" Type="Double" />
            <asp:Parameter Name="prod_price" Type="Double" />
        </InsertParameters>
    </asp:SqlDataSource>
</asp:Content>

