<%@ include file="Include/CheckLogin.jsp" %>
<%@ include file="Include/PreventCaching.jsp" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
<html>
<head>
  <link href="CSS/classStyles.css" rel="stylesheet" type="text/css" />
  <title>Edit Product</title>
</head>
<body>
<div id="bodycenter">
	<%@ include file="Include/NavBar.htm" %>
    <div id="maincontent" class="datatable">
         <h:form>
            <h:dataTable value="#{productutils.productlist}" 
                         var="row"
                         columnClasses="colLeft, colLeft, colRight, colRight">
               <h:column>
                  <f:facet name="header">
                     <h:outputText value="Edit" style="font-weight: bold"/>
                  </f:facet>
                  <h:selectBooleanCheckbox value="#{row.editable}" onclick="submit()"/>
               </h:column>
               
               <h:column>
                  <f:facet name="header">
                     <h:outputText value="Description" style="font-weight: bold"/>
                  </f:facet>
                  <h:inputText value="#{row.prod_desc}" rendered="#{row.editable}" size="30"/>
                  <h:outputText value="#{row.prod_desc}" rendered="#{not row.editable}"/>
               </h:column>
               
               <h:column>
                  <f:facet name="header">
                     <h:outputText value="Our Cost" style="font-weight: bold"/>
                  </f:facet>
                  <h:inputText value="#{row.prod_cost}" rendered="#{row.editable}" size="10"/>
                  <h:outputText value="#{row.formattedCost}" rendered="#{not row.editable}"/>
               </h:column>
               
               <h:column>
                  <f:facet name="header">
                     <h:outputText value="Retail Price" style="font-weight: bold"/>
                  </f:facet>
                  <h:inputText value="#{row.prod_price}" rendered="#{row.editable}" size="10"/>
                  <h:outputText value="#{row.formattedPrice}" rendered="#{not row.editable}"/>
               </h:column>
                          
            </h:dataTable>
            <h:commandButton value="Save Changes" action="#{productutils.update}" />
         </h:form>
         <h:outputLabel value="#{productutils.msg}" />
        </div>
      </div>
      </body>
</html>
</f:view>

