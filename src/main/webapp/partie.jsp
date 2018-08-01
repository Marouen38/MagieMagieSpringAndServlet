<%-- 
    Document   : partie
    Created on : 13 juil. 2018, 12:04:49
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="utf-8"/>
  <title>Cv de Chloe</title>
  <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Fugaz+One" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css"/>
  
</head>
<body>
    <div class="mainPartie">
        <section class="sectionJoueur">
            <div class="headerPartie">
                <h1 class="titleMagiePartie blocHeader">Magie Magie</h1>
                <h3 class="blocHeader">Partie Chloé 1</h3>
                <div class="joueurMain blocHeader">
                    <p>Joueur qui a la main</p>
                    <p id="nameJoueur">RiMA</p>
                </div>
            </div>
            <ul>
                <li class="joueur">
                    <i class="fas fa-user user userPartie"></i>
                    <p>Hédi</p>
                    <p>3 cartes</p>
                </li>
                <li class="joueur">
                    <i class="fas fa-user user userPartie"></i>
                    <p>Rima</p>
                    <p>6 cartes</p>
                </li>
                <li class="joueur">
                    <i class="fas fa-user user userPartie"></i>
                    <p>Marouen</p>
                    <p>9 cartes</p>
                </li>
                <li class="joueur">
                    <i class="fas fa-user user userPartie"></i>
                    <p>Khaled</p>
                    <p>5 cartes</p>
                </li>
            </ul>
         </section>
        <section class="sectionCarteIngre">
            <h2>MES CARTES</h2>
            <ul class="ulIngredients">
                <li class="ingredients">
                    <img class="cartesIngerdients" src="img/ailes.png" alt="ailes" title="ailes chauve souris" width="349" height="267">
                    <h3>Chauve souris</h3>
                    <button class="btnChoixIngre"><p>CHOISIR</p></button>
                </li>
                <li class="ingredients">
                    <img class="cartesIngerdients" src="img/licorne.png" alt="Licorne" title="Licorne" width="349" height="267">
                    <h3>Licorne</h3>
                    <button class="btnChoixIngre"><p>CHOISIR</p></button>
                </li>
                <li class="ingredients">
                    <img class="cartesIngerdients" src="img/lapi-lazuli.png" alt="Lapi-Lazuli" title="Lapi-Lazuli" width="349" height="267">
                    <h3>Lapi-Lazuli</h3>
                    <button class="btnChoixIngre"><p>CHOISIR</p></button>
                </li>
                <li class="ingredients">
                    <img class="cartesIngerdients" src="img/frog.png" alt="Bave-crapaud" title="Bave crapaud" width="349" height="267">
                    <h3>Bave crapaud</h3>
                    <button class="btnChoixIngre"><p>CHOISIR</p></button>
                </li>
                <li class="ingredients">
                    <img class="cartesIngerdients" src="img/madragore.png" alt="Mandragore" title="Mandragore" width="349" height="267">
                    <h3>Mandragore</h3>
                    <button class="btnChoixIngre"><p>CHOISIR</p></button>
                </li>
                <li class="ingredients">
                    <img class="cartesIngerdients" src="img/ailes.png" alt="ailes" title="ailes chauve souris" width="349" height="267">
                    <h3>Chauve souris</h3>
                    <button class="btnChoixIngre"><p>CHOISIR</p></button>
                </li>
                <li class="ingredients">
                    <img class="cartesIngerdients" src="img/ailes.png" alt="ailes" title="ailes chauve souris" width="349" height="267">
                    <h3>Chauve souris</h3>
                    <button class="btnChoixIngre"><p>CHOISIR</p></button>
                </li>
            </ul> 
            <div class="buttonPartie">
                <button>Jouer</button>
                <button>Passer tour</button>
            </div>
        </section>          
    </div>
</body>
 <c:forEach items="${listeJoueur}" var="JoueurAct">
            <a href=”rejoindre_partie?partie_id=${id.partie}”></a>
            ${partie.nom}<br>
            
        </c:forEach>
</html>