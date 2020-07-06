<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="date.Produs" %>
<%@ page import="date.Categorie" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>~Restaurant SDA7 Meniu~</title>
<%
String contextPath = request.getContextPath();//punem mana pe url web context
%>
<script>
    function myFunction(arg){
        //id ="cant" + arg;
        alert("s-a apasat butonul " + arg + "Iar cantitatea este: " + document.getElementById("" + id).value);
        document.getElementById("productId").value=""+arg;
        form.submit();
    }
</script>
</head>
<body>
<h1 align = "center">MENIU</h1>
<%
List produse = (List) request.getServletContext().getAttribute("produse");
%>
<form action="UpdateCos" >
<input type = "hidden" id="productId" name = "productId">
 
<a href="AfisareCos">COS</a> Produse in Cos:
<% 
  if(request.getSession().getAttribute("numarObiecteCos") != null){
%> 
<%=request.getSession().getAttribute("numarObiecteCos") %>
<% } %>
<%@ include file = "header.jsp" %>
<table align = "center" coolspan="2" border= 1>
  <tr>
    <th>Categorie</th>
    <th>Nume Produs</th>
    <th align="left">Descriere Produs</th>
    <th align="center">Pret Unitar</th>
    <th>Imagine</th>
    <th>Cantitate</th>
    <th>Adauga</th>
  </tr>
  <% 
  for (int i = 0;i<produse.size();i++){ 
  Produs produs = (Produs)produse.get(i);
  Categorie categorie = produs.getCategorie();
  %>
  <tr>
    <td><%=categorie.getNumeCategorieProdus()%></td>
    <td><%=produs.getNumeProdus()%></td>
    <td><%=produs.getDescriereProdus()%></td>
    <td><%=produs.getPretUnitar()%></td>
    <td></td>
    <td><input type ="number" id="cant<%=produs.getIdProdus() %>" name="cant<%=produs.getIdProdus() %>" min="1" max="<%=produs.getStoc()%>"></td>
    <td>
    <%if(produs.getStoc()<1){ %>
    Produs indisponibil!
     <%}else{ %>
     <button onclick = 'this.disabled=true;document.getElementById("productId").value="<%=produs.getIdProdus()%>";form.submit();'>Adauga</button>
    <%if(produs.getStoc()<=produs.getNivelAlerta()){ %>
    <br/>
    Low Stock!
     <%} %>
    <%} %>
    </td>
  </tr>
 <%}%>
</table>
</form>       
</body>
</html>