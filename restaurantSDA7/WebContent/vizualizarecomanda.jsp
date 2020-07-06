<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<%@ page import="ui.LinieTabelComanda" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
List valori = (List) request.getSession().getAttribute("liniiTabelComanda");
%>
<table align = "center" border= 1>
  		<tr>
    		<th>ID Produs</th> 
    		<th>Nume Produs</th>
    		<th>Descriere Produs</th>
    		<th>Pret Unitar</th>
    		<th>Cantitate</th>
    		<th>Pret Total</th>
    		<th>Sterge produs</th>
  		</tr>
  <% 
  for (int i = 0;i<valori.size();i++){ 
	  LinieTabelComanda ltc = (LinieTabelComanda)valori.get(i);
  %>
  <tr>
  	<td align="center"><%=ltc.getIdProdus()%></td>
    <td><%=ltc.getNumeProdus()%></td>
    <td><%=ltc.getDescriereProdus()%></td>
    <td align="right"><%=ltc.getPretUnitar()%></td>
    <td align="center"><%=ltc.getCantitate()%></td>
    <td align="right"><%=ltc.getPretTotal()%></td>
    <td></td>   
  </tr>
 <%}%>
</table>
<br></br>
 <p align="center" color="red"><b>Total de plata : <%=request.getSession().getAttribute("totalComanda")%> RON</b></p>
 <p align="left"><button><a href="meniu.jsp">Inapoi la meniu</a></button></p>
</body>
</html>