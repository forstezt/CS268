<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" 
         AutoEventWireup="true" CodeFile="AddProduct.aspx.cs" Inherits="AddProduct" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Main" Runat="Server">
<table>
<tr><th colspan="2">Add Product</th></tr>
<tr><td align="left">Description</td>
    <td align="left"><asp:TextBox ID="description" runat="server" />
        <asp:RequiredFieldValidator ID="rf1"
                                    runat="server" ErrorMessage="*Required" 
                                    ControlToValidate="description" />
    </td></tr>
<tr><td align="left">Our Cost</td>
    <td align="left"><asp:TextBox ID="cost" runat="server" />
        <asp:RequiredFieldValidator ID="rf2"
                                    runat="server" ErrorMessage="*Required" 
                                    ControlToValidate="cost" />
        <asp:RangeValidator ID="rv1" runat="server" ControlToValidate="cost" 
                            ErrorMessage="*Number .01 to 1000" MaximumValue="1000" MinimumValue="1" 
                            Type="Integer" />
    </td></tr>
<tr><td align="left">Retail Price</td>
    <td align="left"><asp:TextBox ID="price" runat="server" />
        <asp:RequiredFieldValidator ID="rf3"
                                    runat="server" ErrorMessage="*Required" 
                                    ControlToValidate="price" />
        <asp:RangeValidator ID="rv2" runat="server" ControlToValidate="price" 
                            ErrorMessage="*Number .01 to 1000" MaximumValue="1000" MinimumValue=".01" 
                            Type="Double" />
    </td></tr>
<tr><td>&nbsp;</td><td align="left"><asp:Button ID="Button1" runat="server" 
        Text="Submit" onclick="Button1_Click" /></td></tr>
<tr><td align="left" colspan="2"><asp:Label ID="labelMsg" runat="server" Text=""></asp:Label></td></tr>
</table>
</asp:Content>
