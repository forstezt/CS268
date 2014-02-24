<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeFile="MasterDetail.aspx.cs" Inherits="MasterDetail" Title="Untitled Page" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <table>
    <tr>
        <td align="right"><asp:Label ID="Label1" runat="server" Text="Select a Department:"></asp:Label>&nbsp;</td>
        <td align="left">
            <asp:DropDownList ID="ddlDepartment" runat="server" AutoPostBack="True" 
                DataSourceID="sdsDepartment" DataTextField="DeptName" 
                DataValueField="DeptID">
            </asp:DropDownList>
            <asp:SqlDataSource ID="sdsDepartment" runat="server" 
                ConnectionString="<%$ ConnectionStrings:studentConnectionString %>" 
                SelectCommand="SELECT DeptID, DeptName FROM UniversityDepartment ORDER BY DeptName">
            </asp:SqlDataSource>
        </td>
        <td>&nbsp;&nbsp;<asp:Label ID="lblMsg" runat="server" Text="Label"></asp:Label></td>
    </tr>
    <tr>
        <td align="right"><asp:Button ID="btnAddCourse" runat="server" Text="Add a Course" 
                onclick="btnAddCourse_Click" />&nbsp;</td>
        <td align="left" colspan="2">
            <asp:GridView ID="gvCourse" runat="server" AllowPaging="True" 
                AutoGenerateColumns="False" BackColor="LightGoldenrodYellow" BorderColor="Tan" 
                BorderWidth="1px" CellPadding="2" DataKeyNames="CourseID" 
                DataSourceID="sdsCourse" ForeColor="Black" GridLines="None" Height="20px" 
                onrowdeleting="gvCourse_RowDeleting" Width="648px" AllowSorting="True" 
                EnableViewState="False">
                <FooterStyle BackColor="Tan" />
                <Columns>
                    <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" />
                    <asp:BoundField DataField="CourseID" HeaderText="CourseID" 
                        InsertVisible="False" ReadOnly="True" SortExpression="CourseID" 
                        Visible="False" />
                    <asp:TemplateField HeaderText="Name" SortExpression="CourseName">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("CourseName") %>'></asp:TextBox>
                            <br />
                            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                                ControlToValidate="TextBox1" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label1" runat="server" Text='<%# Bind("CourseName") %>'></asp:Label>
                        </ItemTemplate>
                        <HeaderStyle Font-Underline="True" HorizontalAlign="Left" />
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Title" SortExpression="CourseTitle">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox2" runat="server" Text='<%# Bind("CourseTitle") %>'></asp:TextBox>
                            <br />
                            <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                                ControlToValidate="TextBox2" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label2" runat="server" Text='<%# Bind("CourseTitle") %>'></asp:Label>
                        </ItemTemplate>
                        <HeaderStyle Font-Underline="True" HorizontalAlign="Left" />
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Credits" SortExpression="CourseCredits">
                        <EditItemTemplate>
                            <asp:TextBox ID="TextBox3" runat="server" Text='<%# Bind("CourseCredits") %>'></asp:TextBox>
                            <br />
                            <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" 
                                ControlToValidate="TextBox3" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                            <asp:RangeValidator ID="RangeValidator1" runat="server" 
                                ControlToValidate="TextBox3" ErrorMessage="Numbers 0-9" MaximumValue="9" 
                                MinimumValue="0"></asp:RangeValidator>
                        </EditItemTemplate>
                        <ItemTemplate>
                            <asp:Label ID="Label3" runat="server" Text='<%# Bind("CourseCredits") %>'></asp:Label>
                        </ItemTemplate>
                        <HeaderStyle Font-Underline="True" HorizontalAlign="Left" />
                    </asp:TemplateField>
                    <asp:BoundField DataField="DeptID" HeaderText="DeptID" 
                        SortExpression="DeptID" Visible="False" />
                </Columns>
                <PagerStyle BackColor="PaleGoldenrod" ForeColor="DarkSlateBlue" 
                    HorizontalAlign="Center" />
                <SelectedRowStyle BackColor="DarkSlateBlue" ForeColor="GhostWhite" />
                <HeaderStyle BackColor="Tan" Font-Bold="True" />
                <AlternatingRowStyle BackColor="PaleGoldenrod" />
            </asp:GridView>
            <asp:SqlDataSource ID="sdsCourse" runat="server" 
                ConnectionString="<%$ ConnectionStrings:studentConnectionString %>" 
                DeleteCommand="DELETE FROM [UniversityCourse] WHERE [CourseID] = @CourseID" 
                
                InsertCommand="INSERT INTO [UniversityCourse] ([CourseName], [CourseTitle], [CourseCredits], [DeptID]) VALUES (@CourseName, @CourseTitle, @CourseCredits, @DeptID)" SelectCommand="SELECT [CourseID], [CourseName], [CourseTitle], [CourseCredits], [DeptID] 
FROM [UniversityCourse] 
WHERE DeptID = @DeptID
ORDER BY [CourseName], [CourseTitle]" 
                
                UpdateCommand="UPDATE [UniversityCourse] SET [CourseName] = @CourseName, [CourseTitle] = @CourseTitle, [CourseCredits] = @CourseCredits WHERE [CourseID] = @CourseID">
                <SelectParameters>
                    <asp:ControlParameter ControlID="ddlDepartment" Name="DeptID" PropertyName="SelectedValue" />
                </SelectParameters>
                <DeleteParameters>
                    <asp:Parameter Name="CourseID" Type="Int64" />
                </DeleteParameters>
                <UpdateParameters>
                    <asp:Parameter Name="CourseName" Type="String" />
                    <asp:Parameter Name="CourseTitle" Type="String" />
                    <asp:Parameter Name="CourseCredits" Type="Byte" />
                    <asp:Parameter Name="CourseID" Type="Int64" />
                </UpdateParameters>
                <InsertParameters>
                    <asp:Parameter Name="CourseName" Type="String" />
                    <asp:Parameter Name="CourseTitle" Type="String" />
                    <asp:Parameter Name="CourseCredits" Type="Byte" />
                    <asp:Parameter Name="DeptID" Type="Int64" />
                </InsertParameters>
            </asp:SqlDataSource>
        </td>
    </tr>
    </table>
</asp:Content>

