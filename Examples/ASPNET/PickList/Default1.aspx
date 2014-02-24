<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default1.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>    
        <asp:DropDownList ID="DropDownList1" runat="server" 
            DataSourceID="SqlDataSource1" DataTextField="DepartmentName" 
            DataValueField="DepartmentID" AutoPostBack="true">
        </asp:DropDownList>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:SportMotorsConnectionString %>" 
            SelectCommand="SELECT [DepartmentID], [DepartmentName] FROM [SportDepartments] ORDER BY [DepartmentName]">
        </asp:SqlDataSource>
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
            DataSourceID="SqlDataSource2">
            <Columns>
                <asp:BoundField DataField="EmployeeLastName" HeaderText="EmployeeLastName" 
                    SortExpression="EmployeeLastName" />
                <asp:BoundField DataField="EmployeeFirstName" HeaderText="EmployeeFirstName" 
                    SortExpression="EmployeeFirstName" />
                <asp:BoundField DataField="EmployeeMI" HeaderText="EmployeeMI" 
                    SortExpression="EmployeeMI" />
                <asp:BoundField DataField="EmployeeDOB" HeaderText="EmployeeDOB" 
                    SortExpression="EmployeeDOB" />
            </Columns>
        </asp:GridView>
        <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
            ConnectionString="<%$ ConnectionStrings:SportMotorsConnectionString %>" 
            SelectCommand="SELECT [EmployeeLastName], [EmployeeFirstName], [EmployeeMI], [EmployeeDOB] FROM [SportEmployees] WHERE ([DepartmentID] = @DepartmentID) ORDER BY [EmployeeLastName]">
            <SelectParameters>
                <asp:ControlParameter ControlID="DropDownList1" Name="DepartmentID" 
                    PropertyName="SelectedValue" Type="Int64" />
            </SelectParameters>
        </asp:SqlDataSource>
    </div>
    </form>
</body>
</html>
