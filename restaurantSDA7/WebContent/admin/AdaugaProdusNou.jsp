<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="date.Categorie" %>
<% 
List categorii = (List) request.getServletContext().getAttribute("categorii");
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adauga produs nou</title>
</head>
<body>


<% 
  if(categorii != null){ System.out.println("Categorii in jsp> "+categorii.size() + " obiecte"); 
%> 


<!-- <nav> - era cu denumirile lui Octavian

    <button class="baraOptiuni" onclick="location.href='/restaurant5/index.html'">Home</button>
    <button class="baraOptiuni" onclick="location.href='/restaurant5/adaugaProdusNou.jsp'">Administrator</button>
    <button class="baraOptiuni" onclick="location.href='/restaurant5/admin/comenziospatar.jsp'">Ospatar</button>
    <button class="baraOptiuni" onclick="location.href='/restaurant5/AfiseazaMeniu'">Client</button>
    <button class="baraOptiuni" onclick="location.href='/restaurant5/AfiseazaMeniu'">Meniu</button>
    <button class="baraOptiuni" onclick="location.href='/restaurant5/AfisareCos'">Comanda curenta</button>
</nav> -->
    <h1 align="center"><b><i>RESTAURANTUL VEDETELOR</i></b></h1>
    <h2 align="center"><b><i>Adauga produs nou</i></b></h2>
    
    <form action="IntroducereProdus" method="get">
    <div align="center">
        <table>
            <tr>
                <th align="right">
                    <label>Categorie produs</label>
                </th>
                <td>
                    <select id="Denumire categorie" name="idCategorie" required>
                    
                      <%
  for (int j = 0;j<categorii.size();j++){ 
  		Categorie categorie = (Categorie) categorii.get(j);
  %>
                    
                        <option value="<%=categorie.getIdCategorieProdus()%>"><%=categorie.getNumeCategorieProdus()%></option>
                   
                        
              <%} %>              
                        
                    </select>
                </td>
            </tr>
            <tr>
                <th align="right" valign="top">
                    <label>Denumire produs</label>
                </th>
                <td>
                    <textarea id="numeProdus" name="numeProdus" rows = "1" cols = "65" name = "description" maxlength="60" required></textarea>
                    <!-- <input type="text" id="numeProdus" name="numeProdus" maxlength="60" > -->
                </td>
            </tr>
            <tr>
                <th align="right" valign="top">
                    <label>Descriere produs</label>
                </th>
                <td>
                    <textarea id="descriereProdus" name="descriereProdus" rows = "5" cols = "65" name = "description" maxlength="250"></textarea>
                    <!-- <input type="text" id="descriereProdus" name="descriereProdus" maxlength="250"> -->
                </td>
            </tr>
            <tr>
                <th align="right">
                    <label>Pret unitar</label>
                </th>
                <td>
                    <input type="number" step="0.01" id="pretUnitar" name="pretUnitar" required>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <label>Nivel existent</label>
                </th>
                <td>
                    <input type="number" id="nivelExistent" name="nivelExistent" required>
                </td>
            </tr>
            <tr>
                <th align="right">
                    <label>Nivel alerta</label>
                </th>
                <td>
                    <input type="number" id="nivelAlerta" name="nivelAlerta" required>
                </td>
            </tr> 
            <tr align="center"> <td colspan="2">---------------------------------------------</td>
            </tr>
            <tr> </tr>  
            <tr align="center">
                <th colspan="2">
                    <input type="submit" onclick="submitForm()" value="Adauga produs">
                </th>
            </tr>
                
            
        </table>
    </div>
    
    </form>
    
    <%} else {%>
    NU am primit categoriile!!
    <%} %>
</body>
</html>