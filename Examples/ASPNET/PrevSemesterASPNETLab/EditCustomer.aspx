<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="EditCustomer.aspx.cs" Inherits="EditCustomer" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Main" Runat="Server">
    <asp:DetailsView ID="dvCustomer" runat="server" Height="50px" Width="125px" 
        AllowPaging="True" AutoGenerateRows="False" DataKeyNames="cust_id" 
        DataSourceID="SqlDataSource1" OnDataBound="dvCustomer_DataBound" 
        onitemdeleted="dvCustomer_ItemDeleted" onitemdeleting="dvCustomer_ItemDeleting" 
        oniteminserted="dvCustomer_ItemInserted" 
        oniteminserting="dvCustomer_ItemInserting" 
        onitemupdated="dvCustomer_ItemUpdated" 
        onitemupdating="dvCustomer_ItemUpdating" 
        onpageindexchanging="dvCustomer_PageIndexChanging">
        <Fields>
            <asp:TemplateField HeaderText="ID" InsertVisible="False" 
                SortExpression="cust_id">
                <EditItemTemplate>
                    <asp:Label ID="Label1" runat="server" Text='<%# Eval("cust_id") %>'></asp:Label>
                </EditItemTemplate>
                <ItemTemplate>
                    <asp:Label ID="Label1" runat="server" Text='<%# Bind("cust_id") %>'></asp:Label>
                </ItemTemplate>
                <HeaderStyle HorizontalAlign="Left" />
                <ItemStyle HorizontalAlign="Left" />
            </asp:TemplateField>
            <asp:TemplateField HeaderText="Name" SortExpression="cust_name">
                <EditItemTemplate>
                    <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("cust_name") %>'></asp:TextBox>
                </EditItemTemplate>
                <InsertItemTemplate>
                    <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("cust_name") %>'></asp:TextBox>
                </InsertItemTemplate>
                <ItemTemplate>
                    <asp:Label ID="Label2" runat="server" Text='<%# Bind("cust_name") %>'></asp:Label>
                </ItemTemplate>
                <HeaderStyle HorizontalAlign="Left" />
                <ItemStyle HorizontalAlign="Left" />
            </asp:TemplateField>
            <asp:TemplateField HeaderText="Type" SortExpression="cust_type">
                <EditItemTemplate>
                    <asp:DropDownList ID="DropDownList1" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="cust_type_desc" 
                        DataValueField="cust_type" SelectedValue='<%# Bind("cust_type") %>'>
                    </asp:DropDownList>
                    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
                        ConnectionString="<%$ ConnectionStrings:CandyConnectionString %>" 
                        SelectCommand="SELECT [cust_type], [cust_type_desc] FROM [candy_cust_type] ORDER BY [cust_type_desc]">
                    </asp:SqlDataSource>
                </EditItemTemplate>
                <InsertItemTemplate>
                    <asp:DropDownList ID="DropDownList2" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="cust_type_desc" 
                        DataValueField="cust_type" SelectedValue='<%# Bind("cust_type") %>'>
                    </asp:DropDownList>
                    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
                        ConnectionString="<%$ ConnectionStrings:CandyConnectionString %>" 
                        SelectCommand="SELECT [cust_type], [cust_type_desc] FROM [candy_cust_type]">
                    </asp:SqlDataSource>
                </InsertItemTemplate>
                <ItemTemplate>
                    <asp:Label ID="Label3" runat="server" Text='<%# Bind("cust_type_desc") %>'></asp:Label>
                </ItemTemplate>
                <HeaderStyle HorizontalAlign="Left" />
                <ItemStyle HorizontalAlign="Left" />
            </asp:TemplateField>
            <asp:TemplateField ShowHeader="False">
                <EditItemTemplate>
                    <asp:LinkButton ID="LinkButton1" runat="server" CausesValidation="True" CommandName="Update" Text="Update" OnClientClick="return confirm('Confirm update');"></asp:LinkButton>
                    &nbsp;<asp:LinkButton ID="LinkButton2" runat="server" CausesValidation="False" CommandName="Cancel" Text="Cancel"></asp:LinkButton>
                </EditItemTemplate>
                <InsertItemTemplate>
                    <asp:LinkButton ID="LinkButton1" runat="server" CausesValidation="True" CommandName="Insert" Text="Insert" OnClientClick="return confirm('Confirm insert');"></asp:LinkButton>
                    &nbsp;<asp:LinkButton ID="LinkButton2" runat="server" CausesValidation="False" CommandName="Cancel" Text="Cancel"></asp:LinkButton>
                </InsertItemTemplate>
                <ItemTemplate>
                    <asp:LinkButton ID="LinkButton1" runat="server" CausesValidation="False" CommandName="Edit" Text="Edit"></asp:LinkButton>
                    &nbsp;<asp:LinkButton ID="LinkButton2" runat="server" CausesValidation="False" CommandName="New" Text="New"></asp:LinkButton>
                    &nbsp;<asp:LinkButton ID="LinkButton3" runat="server" CausesValidation="False" CommandName="Delete" Text="Delete" OnClientClick="return confirm('Confirm delete');"></asp:LinkButton>
                </ItemTemplate>
            </asp:TemplateField>
        </Fields>
    </asp:DetailsView>
    <asp:Label ID="lableMsg" runat="server" />
    <asp:Button ID="btnFirst" runat="server" onclick="btnFirst_Click" Text="&lt;&lt;" />&nbsp;&nbsp;
    <asp:Button ID="btnPrevious" runat="server" onclick="btnPrevious_Click" Text=" &lt; " />&nbsp;&nbsp;
    <asp:Label ID="lblPosition" runat="server" Width="136px"></asp:Label>&nbsp;&nbsp;
    <asp:Button ID="btnNext" runat="server" onclick="btnNext_Click" Text=" &gt; " />&nbsp;&nbsp;
    <asp:Button ID="btnLast" runat="server" onclick="btnLast_Click" Text="&gt;&gt;" /><br />
    <asp:Label ID="labelMsg" runat="server" />
    
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:CandyConnectionString %>" 
        DeleteCommand="DELETE FROM [candy_customer] WHERE [cust_id] = @cust_id" 
        InsertCommand="INSERT INTO [candy_customer] ([cust_name], [cust_type], [cust_addr], [cust_zip], [cust_phone], [username], [password]) VALUES (@cust_name, @cust_type, @cust_addr, @cust_zip, @cust_phone, @username, @password)" 
        SelectCommand="SELECT candy_customer.cust_id, candy_customer.cust_name, candy_customer.cust_type, candy_customer.cust_addr, candy_customer.cust_zip, candy_customer.cust_phone, candy_customer.username, candy_customer.password, candy_cust_type.cust_type_desc FROM candy_customer INNER JOIN candy_cust_type ON candy_customer.cust_type = candy_cust_type.cust_type ORDER BY candy_customer.cust_name" 
        
        UpdateCommand="UPDATE [candy_customer] 
                       SET [cust_name] = @cust_name, 
                           [cust_type] = @cust_type, 
                           [cust_addr] = @cust_addr, 
                           [cust_zip] = @cust_zip, 
                           [cust_phone] = @cust_phone, 
                           [username] = @username, 
                           [password] = @password 
                       WHERE [cust_id] = @cust_id">
        <DeleteParameters>
            <asp:Parameter Name="cust_id" Type="Int64" />
        </DeleteParameters>
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
        <InsertParameters>
            <asp:Parameter Name="cust_name" Type="String" />
            <asp:Parameter Name="cust_type" Type="String" />
            <asp:Parameter Name="cust_addr" Type="String" />
            <asp:Parameter Name="cust_zip" Type="String" />
            <asp:Parameter Name="cust_phone" Type="String" />
            <asp:Parameter Name="username" Type="String" />
            <asp:Parameter Name="password" Type="String" />
        </InsertParameters>
    </asp:SqlDataSource>
</asp:Content>

