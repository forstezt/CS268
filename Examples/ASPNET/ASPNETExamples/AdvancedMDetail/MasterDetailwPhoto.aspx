<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeFile="MasterDetailwPhoto.aspx.cs" Inherits="MasterDetailwPhoto" Title="Advisor's Students" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <table>
    <tr>
        <td align="right" valign="top"><asp:Label ID="Label1" runat="server" Text="Select an Advisor:"></asp:Label>&nbsp;</td>
        <td align="left" valign="top">
            <asp:DropDownList ID="ddlAdvisor" runat="server" AutoPostBack="True" 
                DataSourceID="sdsAdvisor" DataTextField="AdvisorName" 
                DataValueField="InstructorID" 
                onselectedindexchanged="ddlAdvisor_SelectedIndexChanged">
            </asp:DropDownList>
        </td>
    </tr>
    <tr>
        <td valign="top">            
            <asp:Label ID="lblMsg" visible="False" 
                Text="No photo is available" runat="server" 
                ForeColor="#009900"></asp:Label>
            <asp:Image ID="imgStudent" runat="server" Height="119px" Visible="False" /><br />
        </td>
        <td valign="top">
            <asp:GridView ID="gvStudent" runat="server" AutoGenerateColumns="False" 
                DataSourceID="sdsStudent" Width="442px" AllowSorting="True" 
                CellPadding="4" 
                ForeColor="#333333" 
                OnRowCommand="gvStudent_RowCommand"
                DataKeyNames="StudentID,StudentImagefile" CaptionAlign="Top" 
                EnableViewState="False">
                <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
                <RowStyle BackColor="#EFF3FB" />
                <Columns>
                    <asp:CommandField ShowEditButton="True" ShowSelectButton="True" />
                    <asp:BoundField DataField="StudentID" HeaderText="StudentID" 
                        InsertVisible="False" ReadOnly="True" SortExpression="StudentID" 
                        Visible="False" />
                    <asp:TemplateField HeaderText="Last Name" SortExpression="StudentLastName">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox3" runat="server" Text='<%# Bind("StudentLastName") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label3" runat="server" Text='<%# Bind("StudentLastName") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="First" SortExpression="StudentFirstName">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox2" runat="server" 
                                Text='<%# Bind("StudentFirstName") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label2" runat="server" Text='<%# Bind("StudentFirstName") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="MI" SortExpression="StudentMI">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("StudentMI") %>'></asp:TextBox>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label1" runat="server" Text='<%# Bind("StudentMI") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField DataField="AdvisorID" HeaderText="AdvisorID" 
                        SortExpression="AdvisorID" Visible="False" />
                    <asp:BoundField DataField="StudentImagefile" HeaderText="StudentImagefile" 
                        SortExpression="StudentImagefile" Visible="False" />
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
    <asp:SqlDataSource ID="sdsAdvisor" runat="server" 
        ConnectionString="<%$ ConnectionStrings:studentConnectionString %>" 
        
        SelectCommand="SELECT InstructorID, InstructorLastName + ', ' + InstructorFirstName AS AdvisorName FROM UniversityInstructor WHERE (InstructorID IN (SELECT DISTINCT AdvisorID FROM UniversityStudent)) ORDER BY AdvisorName">
    </asp:SqlDataSource>
    <asp:SqlDataSource ID="sdsStudent" runat="server" 
        ConnectionString="<%$ ConnectionStrings:studentConnectionString %>" 
        
        SelectCommand="SELECT StudentID, StudentLastName, StudentFirstName, StudentMI, AdvisorID, StudentImagefile FROM UniversityStudent WHERE (AdvisorID = @advisorid) ORDER BY StudentLastName, StudentFirstName" 
        DeleteCommand="DELETE FROM [UniversityStudent] WHERE [StudentID] = @StudentID" 
        UpdateCommand="UPDATE [UniversityStudent] SET [StudentLastName] = @StudentLastName, [StudentFirstName] = @StudentFirstName, [StudentMI] = @StudentMI  WHERE [StudentID] = @StudentID">
        <SelectParameters>
            <asp:ControlParameter ControlID="ddlAdvisor" Name="advisorid" 
                PropertyName="SelectedValue" />
        </SelectParameters>
        <DeleteParameters>
            <asp:Parameter Name="StudentID" Type="Int64" />
        </DeleteParameters>
        <UpdateParameters>
            <asp:Parameter Name="StudentID" Type="Int64" />
            <asp:Parameter Name="StudentLastName" Type="String" />
            <asp:Parameter Name="StudentFirstName" Type="String" />
            <asp:Parameter Name="StudentMI" Type="String" />
        </UpdateParameters>
    </asp:SqlDataSource>
</asp:Content>

