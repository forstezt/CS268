<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeFile="GridView.aspx.cs" Inherits="GridView" Title="GridView Example" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <p>
        <asp:GridView ID="gvInstructor" runat="server" AllowPaging="True" 
            AllowSorting="True" AutoGenerateColumns="False" 
            BackColor="LightGoldenrodYellow" BorderColor="Tan" BorderWidth="1px" 
            CellPadding="2" DataKeyNames="InstructorID" DataSourceID="sdsInstructor" 
            ForeColor="Black" GridLines="None" Width="796px" EnableViewState="False">
            <FooterStyle BackColor="Tan" />
            <Columns>
                <asp:CommandField ShowEditButton="True" />
                <asp:BoundField DataField="InstructorID" HeaderText="InstructorID" 
                    InsertVisible="False" ReadOnly="True" SortExpression="InstructorID" 
                    Visible="False" />
                <asp:TemplateField HeaderText="Last Name" SortExpression="InstructorLastName">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("InstructorLastName") %>'></asp:TextBox><br />
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                            ControlToValidate="TextBox1" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label1" runat="server" Text='<%# Bind("InstructorLastName") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="First" SortExpression="InstructorFirstName">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox2" runat="server" Text='<%# Bind("InstructorFirstName") %>'></asp:TextBox><br />
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                            ControlToValidate="TextBox2" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label2" runat="server" Text='<%# Bind("InstructorFirstName") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="MI" SortExpression="InstructorMI">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox4" runat="server" Text='<%# Bind("InstructorMI") %>'></asp:TextBox><br />&nbsp;
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label4" runat="server" Text='<%# Bind("InstructorMI") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Phone" SortExpression="InstructorPhoneNumber">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox3" runat="server" Text='<%# Bind("InstructorPhoneNumber") %>'></asp:TextBox><br />
                        <asp:RangeValidator ID="RangeValidator1" runat="server" 
                            ControlToValidate="TextBox3" ErrorMessage="*Numbers" MaximumValue="9999999999" 
                            MinimumValue="1000000000" Type="Double"></asp:RangeValidator>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label3" runat="server" 
                            Text='<%# Bind("InstructorPhoneNumber") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="User ID" SortExpression="InstructorUserID">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox5" runat="server" Text='<%# Bind("InstructorUserID") %>'></asp:TextBox><br />
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" 
                            ControlToValidate="TextBox5" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label5" runat="server" Text='<%# Bind("InstructorUserID") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="PIN" SortExpression="InstructorPIN">
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox6" runat="server" Text='<%# Bind("InstructorPIN") %>'></asp:TextBox><br />
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" 
                            ControlToValidate="TextBox6" ErrorMessage="*Required"></asp:RequiredFieldValidator>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label6" runat="server" Text='<%# Bind("InstructorPIN") %>'></asp:Label>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:BoundField DataField="DeptID" 
                    HeaderText="DeptID" SortExpression="DeptID" Visible="False" />
			<asp:TemplateField HeaderText="Department" SortExpression="DeptName">
				<ItemTemplate>
					<asp:Label ID="Label7" runat="server" Text='<%# Bind("DeptName") %>'></asp:Label>
				</ItemTemplate>
				<EditItemTemplate>
					<asp:DropDownList ID="DropDownList2" runat="server" DataSourceID="sdsDepartment" 
					                  DataTextField="DeptName" DataValueField="DeptID" 
					                  SelectedValue='<%# Bind("DeptID") %>'
						Style="z-index: 102; left: 0px; position: relative; top: 0px" Width="230px">
					</asp:DropDownList><br />&nbsp;
					<asp:SqlDataSource ID="sdsDepartment" runat="server" ConnectionString="<%$ ConnectionStrings:studentConnectionString %>"
						SelectCommand="SELECT DeptID, DeptName FROM UniversityDepartment ORDER BY DeptName">
					</asp:SqlDataSource>
				</EditItemTemplate>
			</asp:TemplateField>  
            <asp:BoundField DataField="DeptID" HeaderText="DeptID" SortExpression="DeptID" Visible="False" />
            </Columns>
            <PagerStyle BackColor="PaleGoldenrod" ForeColor="DarkSlateBlue" 
                HorizontalAlign="Center" />
            <SelectedRowStyle BackColor="DarkSlateBlue" ForeColor="GhostWhite" />
            <HeaderStyle BackColor="Tan" Font-Bold="True" HorizontalAlign="Left" />
            <AlternatingRowStyle BackColor="PaleGoldenrod" />
        </asp:GridView>
    </p>
    <asp:SqlDataSource ID="sdsInstructor" runat="server" 
        ConnectionString="<%$ ConnectionStrings:studentConnectionString %>" 
        DeleteCommand="DELETE FROM [UniversityInstructor] WHERE [InstructorID] = @InstructorID" 
        InsertCommand="INSERT INTO [UniversityInstructor] ([InstructorFirstName], [InstructorMI], [InstructorLastName], [InstructorPhoneNumber], [InstructorUserID], [InstructorPIN], [DeptID]) VALUES (@InstructorFirstName, @InstructorMI, @InstructorLastName, @InstructorPhoneNumber, @InstructorUserID, @InstructorPIN, @DeptID)" 
        SelectCommand="SELECT UniversityInstructor.InstructorID, UniversityInstructor.InstructorFirstName, UniversityInstructor.InstructorMI, UniversityInstructor.InstructorLastName, UniversityInstructor.InstructorPhoneNumber, UniversityInstructor.InstructorUserID, UniversityInstructor.InstructorPIN, UniversityInstructor.DeptID, UniversityDepartment.DeptName FROM UniversityInstructor INNER JOIN UniversityDepartment ON UniversityInstructor.DeptID = UniversityDepartment.DeptID ORDER BY UniversityInstructor.InstructorLastName, UniversityInstructor.InstructorFirstName" 
        UpdateCommand="UPDATE [UniversityInstructor] SET [InstructorFirstName] = @InstructorFirstName, [InstructorMI] = @InstructorMI, [InstructorLastName] = @InstructorLastName, [InstructorPhoneNumber] = @InstructorPhoneNumber, [InstructorUserID] = @InstructorUserID, [InstructorPIN] = @InstructorPIN, [DeptID] = @DeptID WHERE [InstructorID] = @InstructorID">
        <DeleteParameters>
            <asp:Parameter Name="InstructorID" Type="Int64" />
        </DeleteParameters>
        <UpdateParameters>
            <asp:Parameter Name="InstructorFirstName" Type="String" />
            <asp:Parameter Name="InstructorMI" Type="String" />
            <asp:Parameter Name="InstructorLastName" Type="String" />
            <asp:Parameter Name="InstructorPhoneNumber" Type="String" />
            <asp:Parameter Name="InstructorUserID" Type="String" />
            <asp:Parameter Name="InstructorPIN" Type="String" />
            <asp:Parameter Name="DeptID" Type="Int64" />
            <asp:Parameter Name="InstructorID" Type="Int64" />
        </UpdateParameters>
        <InsertParameters>
            <asp:Parameter Name="InstructorFirstName" Type="String" />
            <asp:Parameter Name="InstructorMI" Type="String" />
            <asp:Parameter Name="InstructorLastName" Type="String" />
            <asp:Parameter Name="InstructorPhoneNumber" Type="String" />
            <asp:Parameter Name="InstructorUserID" Type="String" />
            <asp:Parameter Name="InstructorPIN" Type="String" />
            <asp:Parameter Name="DeptID" Type="Int64" />
        </InsertParameters>
    </asp:SqlDataSource>
</asp:Content>

