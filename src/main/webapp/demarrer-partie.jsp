<%-- 
    Document   : rejoindre-Partie
    Created on : 13 juil. 2018, 11:59:46
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="fr">
<head>
  <meta charset="utf-8"/>
  <title>DEMARRER PARTIE</title>
  <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Fugaz+One" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css"/>
  <link rel="stylesheet" href="css/rejoindre-partie-style.css"/>
  
  
</head>
<body>
    <header>
        <div>
            <i class="fas fa-user user"></i>
            <h2 class="nameUser">Chloe Gadaleta</h2>
            <a href="magie.html"><h1><i class="fas fa-magic magicL"></i> Magie Magie <i class="fas fa-magic magicR"></i></h1></a>
            <h2 class="titleListPartie">Partie Chloé</h2>
        </div>
    </header>
    <div class="main">
        <a href="<c:url value="/partie"></c:url>"><button class="btnCreeP">Démarrer la partie <i class="far fa-arrow-alt-circle-right fleche"></i></button></a>
        <ul class="listePartie">
            <c:forEach items="${liste}" var="joueur">
            <li class="joueur">
                <h3>Chloé</h3>
                <p>cloclo</p>
                <i class="fas fa-user user"></i>
            </li>
            </c:forEach>
            <li class="joueur">
                <h3>Rima</h3>
                <p>riri</p>
                <i class="fas fa-user user"></i>
            </li>
            <li class="joueur">
                <h3>Hedi</h3>
                <p>didi</p>
                <i class="fas fa-user user"></i>
            </li>
            <li class="joueur">
                <h3>Marouen</h3>
                <p>mama</p>
                <i class="fas fa-user user"></i>
            </li>
            <li class="joueur">
                <h3>Khaled</h3>
                <p>khakha</p>
                <i class="fas fa-user user"></i>
            </li>
        </ul>
    </div>
</body>
</html>