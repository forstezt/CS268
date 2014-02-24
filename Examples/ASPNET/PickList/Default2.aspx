<%@ Page Language="C#" AutoEventWireup="true"  CodeFile="Default2.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:DropDownList ID="ddlDepartments" runat="server" AutoPostBack="True" 
            DataSourceID="dsDepartments" DataTextField="DepartmentName" 
            DataValueField="DepartmentID">
        </asp:DropDownList>
        <asp:GridView ID="gvEmployees" runat="server" AutoGenerateColumns="False" 
            DataKeyNames="EmployeeID" DataSourceID="dsEmployees" >
            <Columns>
                <asp:BoundField DataField="EmployeeID" HeaderText="EmployeeID" SortExpression="EmployeeID" Visible="False" />
                <asp:BoundField DataField="DepartmentID" HeaderText="DepartmentID" SortExpression="DepartmentID" Visible="false" />
                <asp:BoundField DataField="EmployeeLastName" HeaderText="LastName" SortExpression="EmployeeLastName" />
                <asp:BoundField DataField="EmployeeFirstName" HeaderText="FirstName" SortExpression="EmployeeFirstName" />
                <asp:BoundField DataField="EmployeeMI" HeaderText="MI" SortExpression="EmployeeMI" />
                <asp:BoundField DataField="EmployeeDOB" HeaderText="DOB" SortExpression="EmployeeDOB" HeaderStyle-HorizontalAlign="Left" DataFormatString="{0:d}" />
            </Columns>
        </asp:GridView>
        <asp:SqlDataSource ID="dsEmployees" runat="server" 
            ConnectionString="<%$ ConnectionStrings:SportMotorsConnectionString %>" 
            SelectCommand="SELECT [EmployeeID], [DepartmentID], [EmployeeLastName], [EmployeeFirstName], [EmployeeMI], [EmployeeDOB] 
                           FROM [SportEmployees] WHERE ([DepartmentID] = @DepartmentID) ORDER BY [EmployeeLastName]">
            <SelectParameters>
                <asp:ControlParameter ControlID="ddlDepartments" Name="DepartmentID" PropertyName="SelectedValue" Type="Int64" />
            </SelectParameters>
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="dsDepartments" runat="server" 
            ConnectionString="<%$ ConnectionStrings:SportMotorsConnectionString %>" 
            SelectCommand="SELECT [DepartmentID], [DepartmentName] FROM [SportDepartments] ORDER BY [DepartmentName]">
        </asp:SqlDataSource>
    
    </div>
    </form>
</body>
</html>
