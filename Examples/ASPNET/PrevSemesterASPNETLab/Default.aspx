<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Main" Runat="Server">
    <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
    DataKeyNames="cust_id" DataSourceID="SqlDataSource1">
        <Columns>
            <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" />
            <asp:BoundField DataField="cust_id" HeaderText="cust_id" InsertVisible="False" 
                ReadOnly="True" SortExpression="cust_id" />
            <asp:BoundField DataField="cust_name" HeaderText="cust_name" 
                SortExpression="cust_name" />
            <asp:BoundField DataField="cust_type" HeaderText="cust_type" 
                SortExpression="cust_type" />
            <asp:BoundField DataField="cust_addr" HeaderText="cust_addr" 
                SortExpression="cust_addr" />
            <asp:BoundField DataField="cust_zip" HeaderText="cust_zip" 
                SortExpression="cust_zip" />
            <asp:BoundField DataField="cust_phone" HeaderText="cust_phone" 
                SortExpression="cust_phone" />
            <asp:BoundField DataField="username" HeaderText="username" 
                SortExpression="username" />
            <asp:BoundField DataField="password" HeaderText="password" 
                SortExpression="password" />
        </Columns>
    </asp:GridView>
<asp:SqlDataSource ID="SqlDataSource1" runat="server" 
    ConnectionString="<%$ ConnectionStrings:CandyConnectionString2 %>" 
    DeleteCommand="DELETE FROM [candy_customer] WHERE [cust_id] = @cust_id" 
    InsertCommand="INSERT INTO [candy_customer] ([cust_name], [cust_type], [cust_addr], [cust_zip], [cust_phone], [username], [password]) VALUES (@cust_name, @cust_type, @cust_addr, @cust_zip, @cust_phone, @username, @password)" 
    SelectCommand="SELECT * FROM [candy_customer] ORDER BY [cust_name]" 
    UpdateCommand="UPDATE [candy_customer] SET [cust_name] = @cust_name, [cust_type] = @cust_type, [cust_addr] = @cust_addr, [cust_zip] = @cust_zip, [cust_phone] = @cust_phone, [username] = @username, [password] = @password WHERE [cust_id] = @cust_id">
    <DeleteParameters>
        <asp:Parameter Name="cust_id" Type="Int64" />
    </DeleteParameters>
    <InsertParameters>
        <asp:Parameter Name="cust_name" Type="String" />
        <asp:Parameter Name="cust_type" Type="String" />
        <asp:Parameter Name="cust_addr" Type="String" />
        <asp:Parameter Name="cust_zip" Type="String" />
        <asp:Parameter Name="cust_phone" Type="String" />
        <asp:Parameter Name="username" Type="String" />
        <asp:Parameter Name="password" Type="String" />
    </InsertParameters>
    <UpdateParameters>
        <asp:Parameter Name="cust_name" Type="String" />
        <asp:Parameter Name="cust_type" Type="String" />
        <asp:Parameter Name="cust_addr" Type="String" />
        <asp:Parameter Name="cust_zip" Type="String" />
        <asp:Parameter Name="cust_phone" Type="String" />
        <asp:Parameter Name="username" Type="String" />
        <asp:Parameter Name="password" Type="String" />
        <asp:Parameter Name="cust_id" Type="Int64" />
    </UpdateParameters>
</asp:SqlDataSource>
</asp:Content>

