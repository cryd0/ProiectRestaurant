<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="date.ProdusManager" %>
<%@ page import="date.Produs" %>
<%@ page import="ui.LinieTabelCos" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vizualizare Cos</title>
</head>
<body>
<%
List valori = (List) request.getSession().getAttribute("liniiTabelCos");
%>

<form action="UpdateCos" >
<input type="hidden" id="productId" name="productId" >
<input type="hidden" id="action" name="action" >

	<table align = "center" coolspan="2" border= 1>
  		<tr>
    		<th>ID Produs</th>
    		<th>Nume Produs</th>
    		<th>Descriere Produs</th>
    		<th>Pret Unitar</th>
    		<th>Cantitate</th>
    		<th>Pret Total</th>
    		<th>Info Stoc</th>
    		<th>Sterge produs</th>
  		</tr>
  <% 
  
  ProdusManager pm = new ProdusManager();
  
  boolean stocProblem = false;
  for (int i = 0;i<valori.size();i++){ 
	  LinieTabelCos ltc = (LinieTabelCos)valori.get(i);
	  Produs produs = pm.getProdusById(ltc.getIdProdus());

  %>
  <tr>
  	<td align="center"><%=ltc.getIdProdus()%></td>
    <td><%=ltc.getNumeProdus()%></td>
    <td><%=ltc.getDescriereProdus()%></td>
    <td align="right"><%=ltc.getPretUnitar()%></td>
    <td align="center"><%=ltc.getCantitate()%></td>
    <td align="right"><%=ltc.getPretTotal()%></td>
    <td align="right"><%if(ltc.getCantitate() > produs.getStoc()){%>
  <div> style="color:red"  > Disponibil : <%=produs.getStoc()%> </div>
    <%stocProblem = true;%>
   <%}else {%>
   <div> style="color:green"  >OK</div>
    <%}%>
   </td>
    <td><button onclick='document.getElementById("productId").value="<%=ltc.getIdProdus() %>";document.getElementById("action").value="remove";form.submit();'>-</button></td>   
  </tr>
 <%}%>
  <%pm.exit();%>
</table>
<br></br>
 <p align="center" color="red"><b>Total de plata : <%=request.getSession().getAttribute("grandTotal")%> RON</b></p>
<br></br>
<%if(!stocProblem){%><p align="right"><b><button><a href="OperatieComanda">TRIMITE COMANDA</a></button></b></p><%}%> 

</form>
<p align="left"><button><a href="meniu.jsp">INAPOI LA MENIU</a></button></p>
</body>
</html>