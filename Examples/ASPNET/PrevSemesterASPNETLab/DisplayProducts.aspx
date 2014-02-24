<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="DisplayProducts.aspx.cs" Inherits="DisplayProducts" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Main" Runat="Server">
    <asp:GridView ID="gvProducts" runat="server" AutoGenerateColumns="False" 
        DataKeyNames="prod_id" DataSourceID="SqlDataSource1">
        <Columns>
            <asp:BoundField DataField="prod_desc" HeaderText="Description" 
                SortExpression="prod_desc">
                <HeaderStyle HorizontalAlign="Left" />
                <ItemStyle HorizontalAlign="Left" />
            </asp:BoundField>
            <asp:BoundField DataField="prod_cost" DataFormatString="{0:c}" 
                HeaderText="Our Cost" SortExpression="prod_cost">
                <HeaderStyle HorizontalAlign="Left" />
                <ItemStyle HorizontalAlign="Right" />
            </asp:BoundField>
            <asp:BoundField DataField="prod_price" DataFormatString="{0:c}" 
                HeaderText="Retail Price" SortExpression="prod_price">
                <HeaderStyle HorizontalAlign="Left" />
                <ItemStyle HorizontalAlign="Right" />
            </asp:BoundField>
        </Columns>
    </asp:GridView>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:CandyConnectionString %>" 
        SelectCommand="SELECT [prod_id], [prod_desc], [prod_cost], [prod_price] FROM [candy_product] ORDER BY [prod_desc]">
    </asp:SqlDataSource>
</asp:Content>

