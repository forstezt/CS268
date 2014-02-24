<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeFile="RedirectVsTransfer.aspx.cs" Inherits="RedirectVsTransfer" Title="Untitled Page" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <asp:Button ID="Redirect" runat="server" Text="Redirect" 
        onclick="Redirect_Click" /> 
    <asp:Button ID="Transfer" runat="server" Text="Transfer" 
        onclick="Transfer_Click" />
</asp:Content>

