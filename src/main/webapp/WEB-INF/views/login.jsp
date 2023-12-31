<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: hamlet
  Date: 27/07/2023
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <title>Document</title>
  <link rel="stylesheet" href="<c:url value='/resources/static/css/style.css'/>"/>
</head>
<body>
<header>
  <nav class="container container--70">
    <ul class="nav--actions">
      <li><a href="/login">Zaloguj</a></li>
      <li class="highlighted"><a href="/register">Załóż konto</a></li>
    </ul>

    <ul>
      <li><a href="/" class="btn btn--without-border active">Start</a></li>
      <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
      <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
      <li><a href="/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
      <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
  </nav>
</header>

<section class="login-page">
  <h2>Zaloguj się</h2>

  <p>${message}</p>

  <c:if test="${param.error != null}">
    <div class="error">Nieprawidłowy email lub hasło</div>
  </c:if>

  <c:if test="${not empty infoMsg}">
    <div class="alert alert-info">
        ${infoMsg}
    </div>
  </c:if>


  <c:if test="${not empty infoSuccessMsg}">
    <div class="alert alert-danger">${infoSuccessMsg}</div>
  </c:if>




  <form action="/login" method="post">
    <div class="form-group">
      <input type="email" name="email" placeholder="Email"/>
    </div>
    <div class="form-group">
      <input type="password" name="password" placeholder="Hasło"/>
      <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
    </div>

    <div class="form-group form-group--buttons">
      <a href="/register" class="btn btn--without-border">Załóż konto</a>
      <button class="btn" type="submit">Zaloguj się</button>
    </div>
  </form>



</section>

<footer>
  <div class="contact">
    <h2>Skontaktuj się z nami</h2>
    <h3>Formularz kontaktowy</h3>
    <form>
      <div class="form-group form-group--50">
        <input type="text" name="email" placeholder="Imię" />
      </div>
      <div class="form-group form-group--50">
        <input type="text" name="surname" placeholder="Nazwisko" />
      </div>

      <div class="form-group">
        <textarea name="message" placeholder="Wiadomość" rows="1"></textarea>
      </div>

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

