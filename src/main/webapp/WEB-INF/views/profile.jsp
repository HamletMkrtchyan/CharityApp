<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">

            <sec:authorize access="isAnonymous()">
                <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
                <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <li class="logged-user">
                    Witaj, <sec:authentication property="name"/>
                    <ul class="dropdown">
                        <li><a href="/profile">Profil</a></li>
                        <li><a href="#">Moje zbiórki</a></li>
                        <li><a href="/logout">Wyloguj</a></li>
                    </ul>
                </li>
            </sec:authorize>

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

<h1 class="profile-header">Strona profilu</h1>

<div class="admin-forms-container">


    <div class="profile-form-wrapper">

        <c:if test="${not empty successMsg}">
            <div class="alert alert-success">${successMsg}</div>
        </c:if>

        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">${errorMsg}</div>
        </c:if>


        <form action="/profile/updateProfile" method="post" class="profile-form">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.email}"/>
            </div>

            <input type="submit" value="Zaktualizuj profil" class="submit-button"/>
        </form>
    </div>

    <div class="password-change-wrapper">
        <h2 class="password-change-header">Zmiana hasła</h2>

        <c:if test="${not empty infoSuccessMsg}">
            <div class="alert alert-danger">${infoSuccessMsg}</div>
        </c:if>

        <form action="/profile/changePassword" method="post" class="password-change-form">
            <div class="form-group">
                <label for="currentPassword">Obecne hasło:</label>
                <c:if test="${not empty infoCurrentMsg}">
                    <div class="alert alert-danger">${infoCurrentMsg}</div>
                </c:if>
                <input type="password" id="currentPassword" name="currentPassword"/>
            </div>

            <div class="form-group">
                <label for="newPassword">Nowe hasło:</label>
                <c:if test="${not empty infoCurrentNewMsg}">
                    <div class="alert alert-danger">${infoCurrentNewMsg}</div>
                </c:if>
                <c:if test="${not empty passwordLength}">
                    <div class="alert alert-danger">${passwordLength}</div>
                </c:if>

                <input type="password" id="newPassword" name="newPassword"/>
            </div>

            <div class="form-group">
                <label for="repeatNewPassword">Powtórz nowe hasło:</label>

                <input type="password" id="repeatNewPassword" name="repeatNewPassword"/>
            </div>

            <input type="submit" value="Zmień hasło" class="submit-button"/>
        </form>
    </div>
</div>


<%@ include file="footer.jsp" %>
