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
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/resources/static/css/style.css'/>"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/loginForm">Zaloguj</a></li>
            <li class="highlighted"><a href="/registerForm">Załóż konto</a></li>
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
    <h2>Załóż konto</h2>

    <form:form action="/register" method="post" modelAttribute="user">
        <div class="form-group">
            <form:input type="email" path="email" placeholder="Email"/>
            <c:if test="${emailError ne null}">
                <p style="color: red;">${emailError}</p>
            </c:if>
        </div>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło"/>
            <c:if test="${lengthError ne null}">
                <p style="color: red;">${lengthError}</p>
            </c:if>
        </div>
        <div class="form-group">
            <form:password path="password2" placeholder="Powtórz hasło"/>
            <c:if test="${passwordError ne null}">
                <p style="color: red;">${passwordError}</p>
            </c:if>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>

</section>

<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form>
            <div class="form-group form-group--50">
                <input type="text" name="name" placeholder="Imię"/>
            </div>
            <div class="form-group form-group--50">
                <input type="text" name="surname" placeholder="Nazwisko"/>
            </div>

            <div class="form-group">
            <textarea
                    name="message"
                    placeholder="Wiadomość"
                    rows="1"
            ></textarea>
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

