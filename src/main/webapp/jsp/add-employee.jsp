<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="header.jsp"/>
<h2>Add Employee</h2>
<form method="post" action="${pageContext.request.contextPath}/admin/add-employee" class="card">
  <label>Name</label>
  <input type="text" name="name" required>
  <label>Email</label>
  <input type="email" name="email" required>
  <label>Salary</label>
  <input type="number" name="salary" step="0.01" required>
  <label>Join Date</label>
  <input type="date" name="join_date" required>
  <fieldset>
    <legend>Create Login (optional)</legend>
    <label>Username</label>
    <input type="text" name="username">
    <label>Password</label>
    <input type="password" name="password">
  </fieldset>
  <button type="submit">Save</button>
</form>
<jsp:include page="footer.jsp"/>
