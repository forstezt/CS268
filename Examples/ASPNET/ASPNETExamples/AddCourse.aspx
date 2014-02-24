<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeFile="AddCourse.aspx.cs" Inherits="AddCourse" Title="Untitled Page" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
	<asp:Label ID="Label1" runat="server" Style="z-index: 100; left: 255px; position: absolute;
		top: 72px" Text="Course Name:"></asp:Label>
	<asp:Label ID="Label2" runat="server" Style="z-index: 101; left: 266px; position: absolute;
		top: 106px" Text="Course Title:"></asp:Label>
	<asp:Label ID="Label3" runat="server" Style="z-index: 102; left: 296px; position: absolute;
		top: 140px" Text="Credits:"></asp:Label>
	<asp:TextBox ID="txtName" runat="server" MaxLength="10" Style="z-index: 103; left: 364px;
		position: absolute; top: 72px" Width="112px"></asp:TextBox>
	<asp:TextBox ID="txtTitle" runat="server" MaxLength="200" Style="z-index: 104; left: 364px;
		position: absolute; top: 105px" TabIndex="1" Width="296px"></asp:TextBox>
	<asp:TextBox ID="txtCredit" runat="server" MaxLength="1" Style="z-index: 105; left: 364px;
		position: absolute; top: 138px" TabIndex="2" Width="20px"></asp:TextBox>
	<asp:Button ID="btnSubmit" runat="server" Style="z-index: 106; left: 364px; position: absolute;
		top: 172px; width: 173px;" TabIndex="3" Text="Submit New Course" 
        onclick="btnSubmit_Click" />
	<asp:Label ID="lblMsg" runat="server" ForeColor="Green" Style="z-index: 107; left: 364px;
		position: absolute; top: 208px" Width="428px"></asp:Label>
	<asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="txtTitle"
		ErrorMessage="*Required" SetFocusOnError="True" Style="z-index: 108; left: 676px;
		position: absolute; top: 108px"></asp:RequiredFieldValidator>
	<asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ControlToValidate="txtCredit"
		ErrorMessage="*Required" SetFocusOnError="True" Style="z-index: 109; left: 404px;
		position: absolute; top: 140px"></asp:RequiredFieldValidator>
	<asp:RangeValidator ID="RangeValidator1" runat="server" ControlToValidate="txtCredit"
		ErrorMessage="Credits must be a number between 0 and 9" MaximumValue="9" MinimumValue="0"
		SetFocusOnError="True" Style="z-index: 110; left: 484px; position: absolute;
		top: 140px" Type="Integer"></asp:RangeValidator>
	<asp:Label ID="lblDepartment" runat="server" Font-Bold="True" Font-Size="XX-Large"
		Height="32px" Style="z-index: 111; left: 184px; position: absolute; top: 16px"
		Width="564px"></asp:Label>
	<asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="txtName"
		ErrorMessage="*Required" SetFocusOnError="True" Style="z-index: 113; left: 496px;
		position: absolute; top: 72px"></asp:RequiredFieldValidator>
</asp:Content>

