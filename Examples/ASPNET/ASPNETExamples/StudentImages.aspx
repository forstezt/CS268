<%@ Page Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true" CodeFile="StudentImages.aspx.cs" Inherits="StudentImages" Title="Untitled Page" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <table>
    <tr><td align="center">
    <asp:DetailsView ID="dvStudent" runat="server" AllowPaging="True" 
        AutoGenerateRows="False" BackColor="White" BorderColor="#3366CC" 
        BorderStyle="None" BorderWidth="1px" CellPadding="4" DataKeyNames="StudentImagefile" 
        DataSourceID="sdsStudent" Height="50px" Width="267px" 
            ondatabound="dvStudent_DataBound">
        <FooterStyle BackColor="#99CCCC" ForeColor="#003399" />
        <RowStyle BackColor="White" ForeColor="#003399" />
        <PagerStyle BackColor="#99CCCC" ForeColor="#003399" HorizontalAlign="Left" />
        <Fields>
            <asp:BoundField DataField="StudentLastName" HeaderText="Last Name" 
                SortExpression="StudentLastName">
                <HeaderStyle HorizontalAlign="Left" />
                <ItemStyle HorizontalAlign="Left" />
            </asp:BoundField>
            <asp:BoundField DataField="StudentFirstName" HeaderText="First" 
                SortExpression="StudentFirstName">
                <HeaderStyle HorizontalAlign="Left" />
                <ItemStyle HorizontalAlign="Left" />
            </asp:BoundField>
            <asp:BoundField DataField="StudentMI" HeaderText="MI" 
                SortExpression="StudentMI">
                <HeaderStyle HorizontalAlign="Left" />
                <ItemStyle HorizontalAlign="Left" />
            </asp:BoundField>
        </Fields>
        <HeaderStyle BackColor="#003399" Font-Bold="True" ForeColor="#CCCCFF" />
        <EditRowStyle BackColor="#009999" Font-Bold="True" ForeColor="#CCFF99" />
    </asp:DetailsView>
    </td>
    <td rowspan="2" valign="top" >
        <asp:Image ID="imgStudent" runat="server" Height="147px" />
    </td></tr>
    <tr><td align="center">
        <asp:Button ID="btnFirst" runat="server" Text=" << " onclick="btnFirst_Click" />&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="btnPrevious" runat="server" Text="  <  " onclick="btnPrevious_Click" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Label ID="lblPosition" runat="server" Text=""></asp:Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="btnNext" runat="server" Text="  >  " onclick="btnNext_Click" />&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="btnLast" runat="server" Text=" >> " onclick="btnLast_Click" />
    </td><td></td></tr>    
    </table>
    <asp:SqlDataSource ID="sdsStudent" runat="server" 
        ConnectionString="<%$ ConnectionStrings:studentConnectionString %>" 
        
        SelectCommand="SELECT [StudentLastName], [StudentFirstName], [StudentMI], [StudentImagefile] FROM [UniversityStudent] ORDER BY [StudentLastName], [StudentFirstName]">
    </asp:SqlDataSource>
</asp:Content>

