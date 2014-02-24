<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Login.aspx.cs" Inherits="Login" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="Main" Runat="Server">
<table>
    <tr><th colspan="2">Login</th></tr>
    <tr><td>Username</td>
        <td><asp:TextBox ID="username" runat="server"></asp:TextBox></td></tr>
    <tr><td>Password</td>
        <td><asp:TextBox ID="password" runat="server"></asp:TextBox></td></tr>
    <tr><td>&nbsp;</td>
        <td><asp:Button ID="submit" runat="server" text="Login" onclick="submit_Click" /></td></tr>  
    <tr>
        <td colspan="2"><asp:Label ID="loginMsg" runat="server" Text="" /></td>
    </tr>
    <tr><td><b>username</b></td><td><b>password</b></td></tr>
    <tr><td>swedburg</td><td>2353</td></tr>
    <tr><td>pickpick</td><td>5333</td></tr>
    <tr><td>kidcandy</td><td>2351</td></tr>
    <tr><td>wateral</td><td>8900</td></tr>
    <tr><td>bobbybon</td><td>3011</td></tr>
</table>
</asp:Content>

