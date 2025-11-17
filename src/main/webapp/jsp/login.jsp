<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <!-- FIXED CSS PATH -->
    <link rel="stylesheet" 
          href="<c:url value='/assets/css/style.css'/>">
</head>
<body>
<div class="login-container">
  <div class="login-box">
    <h2>Welcome Back</h2>

    <c:if test="${not empty error}">
        <div class="alert">${error}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/login">
      <input type="text" name="username" placeholder="Username" required>
      <input type="password" name="password" placeholder="Password" required>
      <button type="submit" class="btn" style="width:100%;margin-top:10px;">Login</button>
    </form>
  </div>
</div>

</body>
</html>
