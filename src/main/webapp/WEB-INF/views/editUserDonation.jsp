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
  <title>Edycja Darowizny</title>
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
            <li><a href="/profile">Moje zbiórki</a></li>
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

  <h2 class="donation-edit-title">Edytuj Darowiznę</h2>

  <form action="/updateDonation" method="post" class="donation-edit-form">
    <input type="hidden" name="id" value="${donation.id}" />

    <label>Ilość:
      <input type="number" name="quantity" value="${donation.quantity}" required />
    </label>

    <label>Kategorie:
      <select multiple name="categories">
        <c:forEach var="category" items="${allCategories}">
          <option value="${category.id}" <c:if test="${donation.categories.contains(category)}">selected</c:if>>${category.name}</option>
        </c:forEach>
      </select>
    </label>

    <label>Instytucja:
      <select name="institution">
        <c:forEach var="institution" items="${allInstitutions}">
          <option value="${institution.id}" <c:if test="${donation.institution.id == institution.id}">selected</c:if>>${institution.name}</option>
        </c:forEach>
      </select>
    </label>

    <label>Ulica:
      <input type="text" name="street" value="${donation.street}" required />
    </label>

    <label>Miasto:
      <input type="text" name="city" value="${donation.city}" required />
    </label>

    <label>Kod pocztowy:
      <input type="text" name="zipCode" value="${donation.zipCode}" required />
    </label>

    <label>Data odbioru:
      <input type="date" name="pickUpDate" value="${donation.pickUpDate}" required />
    </label>

    <label>Godzina odbioru:
      <input type="time" name="pickUpTime" value="${donation.pickUpTime}" required />
    </label>

    <label>Komentarz:
      <textarea name="pickUpComment">${donation.pickUpComment}</textarea>
    </label>

    <label>Status:
      <select name="received">
        <option value="true" <c:if test="${donation.received}">selected</c:if>>Odebrane</option>
        <option value="false" <c:if test="${!donation.received}">selected</c:if>>Nieodebrane</option>
      </select>
    </label>


    <input type="submit" value="Aktualizuj" class="btn btn-edit-submit"/>
  </form>

  <%@ include file="footer.jsp" %>

</body>
</html>

