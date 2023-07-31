<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
  <title>Document</title>
  <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">
  <nav class="container container--70">
    <ul class="nav--actions">
      <li class="logged-user">
        Witaj Agata
        <ul class="dropdown">
          <li><a href="#">Profil</a></li>
          <li><a href="#">Moje zbiórki</a></li>
          <li><a href="#">Wyloguj</a></li>
        </ul>
      </li>
    </ul>

    <ul>
      <li><a href="" class="btn btn--without-border active">Start</a></li>
      <li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
      <li><a href="#about-us" class="btn btn--without-border">O nas</a></li>
      <li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
      <li><a href="/giveDonationForm" class="btn btn--without-border">Przekaż dary</a></li>
      <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
  </nav>

  <div class="slogan container container--90">
    <div class="slogan--item">
      <h1>
        Zacznij pomagać!<br/>
        Oddaj niechciane rzeczy w zaufane ręce
      </h1>
    </div>
  </div>
</header>

<section id="stats" class="stats">
  <div class="container container--85">
    <div class="stats--item">
      <em>${totalBags}</em>
      <h3>Oddanych worków</h3>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
        tempora!</p>
    </div>

    <div class="stats--item">
      <em>${totalDonations}</em>
      <h3>Przekazanych darów</h3>
      <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
        quam.</p>
    </div>

  </div>
</section>

<section id="steps" class="steps">
  <h2>Wystarczą 4 proste kroki</h2>

  <div class="steps--container">
    <div class="steps--item">
      <span class="icon icon--hands"></span>
      <h3>Wybierz rzeczy</h3>
      <p>ubrania, zabawki, sprzęt i inne</p>
    </div>
    <div class="steps--item">
      <span class="icon icon--arrow"></span>
      <h3>Spakuj je</h3>
      <p>skorzystaj z worków na śmieci</p>
    </div>
    <div class="steps--item">
      <span class="icon icon--glasses"></span>
      <h3>Zdecyduj komu chcesz pomóc</h3>
      <p>wybierz zaufane miejsce</p>
    </div>
    <div class="steps--item">
      <span class="icon icon--courier"></span>
      <h3>Zamów kuriera</h3>
      <p>kurier przyjedzie w dogodnym terminie</p>
    </div>
  </div>

  <a href="/goToRegisterPage" class="btn btn--large">Załóż konto</a>
</section>

<section id="about-us" class="about-us">
  <div class="about-us--text">
    <h2>O nas</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas vitae animi rem pariatur incidunt libero
      optio esse quisquam illo omnis.</p>
    <img src="<c:url value="resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
  </div>
  <div class="about-us--image"><img src="<c:url value="resources/images/about-us.jpg" />" alt="People in circle"/></div>
</section>

<section id="help" class="help">
  <h2>Komu pomagamy?</h2>

  <!-- SLIDE 1 -->
  <div class="help--slides active" data-id="1">
    <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy.
      Możesz sprawdzić czym się zajmują.</p>

    <ul class="help--slides-items">
      <c:forEach var="institution" items="${institutions}" varStatus="loop">
        <c:if test="${loop.index % 2 == 0}">
          <li>
        </c:if>

        <div class="col">
          <div class="title">${institution.name}</div>
          <div class="subtitle">Cel i misja: ${institution.description}</div>
        </div>

        <c:if test="${loop.index % 2 != 0}">
          </li>
        </c:if>
      </c:forEach>
    </ul>


  </div>

</section>

<footer>
  <div id="contact" class="contact">
    <h2>Skontaktuj się z nami</h2>
    <h3>Formularz kontaktowy</h3>
    <form class="form--contact">
      <div class="form-group form-group--50"><input type="text" name="name" placeholder="Imię"/></div>
      <div class="form-group form-group--50"><input type="text" name="surname" placeholder="Nazwisko"/></div>

      <div class="form-group"><textarea name="message" placeholder="Wiadomość" rows="1"></textarea></div>

      <button class="btn" type="submit">Wyślij</button>
    </form>
  </div>
  <div class="bottom-line">
    <span class="bottom-line--copy">Copyright &copy; 2018</span>
    <div class="bottom-line--icons">
      <a href="#" class="btn btn--small"><img src="<c:url value='resources/images/icon-facebook.svg'/>"/></a>
      <a href="#" class="btn btn--small"><img src="<c:url value='resources/images/icon-instagram.svg'/>"/></a>
    </div>
  </div>
</footer>

<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
