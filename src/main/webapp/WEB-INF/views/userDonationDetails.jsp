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


    <h1 class="donation-title">Lista Twoich darów</h1>
    <c:if test="${not empty deleteMsg}">
    <div class="alert alert-success">${deleteMsg}</div>
    </c:if>

    <table class="donation-table">
        <thead>
        <tr>
            <th class="donation-id">Id</th>
            <th class="donation-status">Status</th>
            <th class="donation-quantity">Ilość</th>
            <th class="donation-categories">Kategorie</th>
            <th class="donation-institution">Instytucja</th>
            <th class="donation-street">Ulica</th>
            <th class="donation-city">Miasto</th>
            <th class="donation-zip">Kod pocztowy</th>
            <th class="donation-date">Data odbioru</th>
            <th class="donation-time">Godzina odbioru</th>
            <th class="donation-comment">Komentarz</th>
            <th class="donation-actions">Akcje</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="donation" items="${donationsList}">
            <tr class="donation-row">
                <td>${donation.id}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty donation.pickUpDate}">Odebrane</c:when>
                        <c:otherwise>Nieodebrane</c:otherwise>
                    </c:choose>
                </td>
                <td>${donation.quantity}</td>
                <td class="categories-list">
                    <c:forEach var="category" items="${donation.categories}">
                        <span class="category-item">${category.name}</span><br>
                    </c:forEach>
                </td>
                <td>${donation.institution.name}</td>
                <td>${donation.street}</td>
                <td>${donation.city}</td>
                <td>${donation.zipCode}</td>
                <td>${donation.pickUpDate}</td>
                <td>${donation.pickUpTime}</td>
                <td>${donation.pickUpComment}</td>
                <td>
                    <a href="/profile/editUserDonation/${donation.id}" class="btn btn-edit">Edytuj</a>
                    <a href="/profile/deleteUserDonation/${donation.id}" class="btn btn-delete" onclick="return confirm('Czy na pewno chcesz usunąć ten wpis?');">Usuń</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>




<%@ include file="footer.jsp" %>
