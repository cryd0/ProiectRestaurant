<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="date.Produs" %>
<%@ page import="ui.admin.LinieTabelComandaOspatar" %>
<%@ page import="ui.admin.ComandaUI" %>
<%
List listaComenziUI = (List) request.getSession().getAttribute("listaComenziUI");
%>	
<!DOCTYPE html>
<html>	
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<form action="SchimbaOspatar" method="POST">
  Ospatar :<br>
  <input type="text" name="ospatar">
  <br><br>
  <input type="submit" value="Submit">



<% 
  if(listaComenziUI != null){
%> 
<p style="color: red;" align = "center">Comenzile ospatarului <%=request.getSession().getAttribute("numeOspatar") %> : <%=listaComenziUI.size() %></p>

<input type = "hidden" id="comandItemId" name = "comandItemId">
<input type = "hidden" id="action" name = "action">
<input type = "hidden" id="idComanda" name = "idComanda">

<table align = "center" border= 1>
  		
  <% 
  for (int i = 0;i<listaComenziUI.size();i++){ 
	  ComandaUI ui = (ComandaUI)listaComenziUI.get(i);
	  List liniiTabelComandaOspatar = ui.getLiniiTabelComandaOspatar();
  %>
  <tr style="background: #aaa;" font-weight:bold><% if(ui.getDataLivrare() != null) {%> color:green; <%} %>
  	<td align = "center" rowspan ="<%=liniiTabelComandaOspatar.size()+1%>">Masa </br><%=ui.getNrMasa()%></td>
    <td colspan="2"><%=ui.getDataCreare()%>Data Creare: </br></td>
    <td colspan="2"><%=ui.getDataLivrare()%>Data Livrare: </br></td>
    <td align = "justify" colspan="2"> 
    
    Total <%=ui.getPretTotal()%>
    <%if (ui.getDataLivrare() != null ){ %>
    		<button onclick = 'this.disabled=true;document.getElementById("action").value="plata";
    		 document.getElementById("idComanda").value="<%=ui.getIdComanda() %>";form.submit();'>Platit</button>
    		   <%}%>
    </td>
  
  </tr>
  <%
  for (int j = 0;j<liniiTabelComandaOspatar.size();j++){ 
  		LinieTabelComandaOspatar ltco = (LinieTabelComandaOspatar) liniiTabelComandaOspatar.get(j);
  %>
  
  		<tr> <% if(ltco.getDataLivrare() != null) {%> style="color:green;" <%} else {%> style="color:red;" <%} %>
  			<td><%=ltco.getIdProdus()%></td>
    		<td><%=ltco.getNumeProdus()%></td>
    		<td><%=ltco.getPretUnitar()%></td>
    		<td><%=ltco.getCantitate()%></td>
    		<td><%=ltco.getPretTotal()%></td>
    		<%if (ltco.getDataLivrare() != null ){ %>
    		<td><%=ltco.getDataLivrare()%></td>
    		  <%} else {%>
    		 <td><button onclick = 'this.disabled=true;document.getElementById("action").value="livrare";document.getElementById("comandItemId").value="<%=ltco.getIdComandItem()%>";form.submit();'>Livrat</button></td>
    		   <%}%>
  		</tr>	
    <%}%>
  <%}%>
</table>
</form>
<%}%>

</body>
</html>