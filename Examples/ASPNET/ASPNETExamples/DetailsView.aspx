<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="false" CodeFile="DetailsView.aspx.cs" Inherits="DetailsView" Title="DetailsView Example" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <br />
    <asp:DetailsView ID="dvDepartment" runat="server" AllowPaging="True" 
        AutoGenerateRows="False" DataSourceID="sdsDepartment" Height="50px" DataKeyNames="DeptID" 
        Width="411px" BackColor="White" BorderColor="#CC9966" BorderStyle="None" 
        BorderWidth="1px" CellPadding="4" ondatabound="dvDepartment_DataBound">
        <FooterStyle BackColor="#FFFFCC" ForeColor="#330099" />
        <RowStyle BackColor="White" ForeColor="#330099" />
        <PagerStyle BackColor="#FFFFCC" ForeColor="#330099" HorizontalAlign="Center" />
        <Fields>
            <asp:BoundField DataField="DeptID" HeaderText="DeptID" 
                InsertVisible="False" ReadOnly="True" SortExpression="DeptID" />
            <asp:TemplateField HeaderText="DeptName" SortExpression="DeptName">
                <EditItemTemplate>
                    <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("DeptName") %>'></asp:TextBox>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                        ControlToValidate="TextBox1" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                </EditItemTemplate>
                <InsertItemTemplate>
                    <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("DeptName") %>'></asp:TextBox>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                        ControlToValidate="TextBox1" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                </InsertItemTemplate>
                <ItemTemplate>
                    <asp:Label ID="Label2" runat="server" Text='<%# Bind("DeptName") %>'></asp:Label>
                </ItemTemplate>
            </asp:TemplateField>
            <asp:BoundField DataField="DeptOffice" HeaderText="DeptOffice" 
                SortExpression="DeptOffice" />
            <asp:BoundField DataField="ChairID" HeaderText="ChairID" 
                SortExpression="ChairID" Visible="False" />
            <asp:TemplateField HeaderText="Department Chair" 
                SortExpression="Department Chair">
                <EditItemTemplate>
                    <asp:DropDownList ID="DropDownList1" runat="server" 
                        DataSourceID="sdsInstructors" DataTextField="DeptChair" 
                        DataValueField="InstructorID" SelectedValue='<%# Bind("ChairID") %>'>
                    </asp:DropDownList>
                </EditItemTemplate>
                <InsertItemTemplate>
                    <asp:DropDownList ID="DropDownList2" runat="server" 
                        DataSourceID="sdsInstructors" DataTextField="DeptChair" 
                        DataValueField="InstructorID" SelectedValue='<%# Bind("ChairID") %>'>
                    </asp:DropDownList>
                </InsertItemTemplate>
                <ItemTemplate>
                    <asp:Label ID="Label1" runat="server" Text='<%# Bind("DeptChair") %>'></asp:Label>
                </ItemTemplate>
            </asp:TemplateField>
            <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" 
                ShowInsertButton="True" />
        </Fields>
        <HeaderStyle BackColor="#990000" Font-Bold="True" ForeColor="#FFFFCC" />
        <EditRowStyle BackColor="#FFCC66" Font-Bold="True" ForeColor="#663399" />
    </asp:DetailsView>
    <table width="411">
        <tr><td align="center"> 
            <asp:Button ID="btnFirst" runat="server" Text="&lt;&lt;" onclick="btnFirst_Click" />&nbsp;&nbsp;
            <asp:Button ID="btnPrevious" runat="server" Text=" &lt; " onclick="btnPrevious_Click" />&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Label ID="lblPosition" runat="server" Width="136px"></asp:Label>&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Button ID="btnNext" runat="server" Text=" &gt; " onclick="btnNext_Click" />&nbsp;&nbsp;
            <asp:Button ID="btnLast" runat="server" Text="&gt;&gt;" onclick="btnLast_Click" />
            </td></tr>
    </table>
    <asp:SqlDataSource ID="sdsInstructors" runat="server" 
        ConnectionString="<%$ ConnectionStrings:studentConnectionString %>" 
        SelectCommand="SELECT InstructorID, InstructorLastName + ', ' + InstructorFirstName AS DeptChair FROM UniversityInstructor
                       ORDER BY InstructorLastName, InstructorFirstName">
    </asp:SqlDataSource>
    <asp:SqlDataSource ID="sdsDepartment" runat="server" 
        ConnectionString="<%$ ConnectionStrings:studentConnectionString %>" 
        DeleteCommand="DELETE FROM [UniversityDepartment] WHERE [DeptID] = @DeptID" 
        InsertCommand="INSERT INTO [UniversityDepartment] ([DeptName], [DeptOffice], [ChairID]) VALUES (@DeptName, @DeptOffice, @ChairID)" 
        SelectCommand="SELECT UniversityDepartment.DeptID, UniversityDepartment.DeptName, UniversityDepartment.DeptOffice, UniversityDepartment.ChairID, UniversityInstructor.InstructorLastName + ', ' + UniversityInstructor.InstructorFirstName AS [DeptChair] FROM UniversityDepartment INNER JOIN UniversityInstructor ON UniversityDepartment.ChairID = UniversityInstructor.InstructorID ORDER BY UniversityDepartment.DeptName" 
        UpdateCommand="UPDATE [UniversityDepartment] SET [DeptName] = @DeptName, [DeptOffice] = @DeptOffice, [ChairID] = @ChairID WHERE [DeptID] = @DeptID">
        <DeleteParameters>
            <asp:Parameter Name="DeptID" Type="Int64" />
        </DeleteParameters>
        <UpdateParameters>
            <asp:Parameter Name="DeptName" Type="String" />
            <asp:Parameter Name="DeptOffice" Type="String" />
            <asp:Parameter Name="ChairID" Type="Int64" />
            <asp:Parameter Name="DeptID" Type="Int64" />
        </UpdateParameters>
        <InsertParameters>
            <asp:Parameter Name="DeptName" Type="String" />
            <asp:Parameter Name="DeptOffice" Type="String" />
            <asp:Parameter Name="ChairID" Type="Int64" />
        </InsertParameters>
    </asp:SqlDataSource>

</asp:Content>

