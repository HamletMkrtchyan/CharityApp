<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
  <title>Profil Użytkownika</title>
</head>
<body>
<h2>Profil Użytkownika</h2>
<form:form action="/updateEmail" method="post" modelAttribute="user">
  <label>Email: </label>
  <form:input path="email" />
  <br/>
  <input type="submit" value="Zaktualizuj dane"/>
</form:form>

<h3>Zmień hasło</h3>
<form:form modelAttribute="passwordChange">
  <label>Stare hasło: </label>
  <form:password path="oldPassword" />
  <br/>

  <label>Nowe hasło: </label>
  <form:password path="newPassword" />
  <br/>

  <label>Potwierdź nowe hasło: </label>
  <form:password path="confirmPassword" />
  <br/>

  <input type="submit" value="Zmień hasło"/>
</form:form>

<a href="/">Powrót do strony głównej</a>

</body>
</html>
