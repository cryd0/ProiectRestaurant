
<%
String idComanda = (String) request.getSession().getAttribute("idComanda");
%>
<% if (idComanda != null){ %>
<p align="left"><button><a href="VizualizareComanda">Vizualizare Comanda</a></button></p>
<%}%>