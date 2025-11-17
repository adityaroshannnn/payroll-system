<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Payroll System</title>
  <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>">
</head>
<body>
<nav class="topnav">
  <span>Payroll System</span>
  <div>
    <c:if test="${not empty sessionScope.user}">
      <span style="margin-right:15px;">Hello, ${sessionScope.user.username} (${sessionScope.user.role})</span>
      <a href="<c:url value='/logout'/>">Logout</a>
    </c:if>
  </div>
</nav>
<div class="container">
