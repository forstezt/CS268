<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <table><tr><td align="center">
    <asp:Label ID="Label1" runat="server" Font-Bold="True" Font-Size="XX-Large" 
        Text="Ironwood University"></asp:Label><br />
        <asp:Image ID="Image1" runat="server" ImageUrl="Images/Ironwood.jpg" />
    </td></tr>
    </table>
</asp:Content>

