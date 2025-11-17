<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h2>Admin Dashboard</h2>

<div style="margin-bottom:20px;">
  <a class="btn" href="${pageContext.request.contextPath}/admin/add-employee">Add Employee</a>
  <a class="btn" href="${pageContext.request.contextPath}/admin/create-payslip">Create Payslip</a>
  <a class="btn" href="${pageContext.request.contextPath}/admin/attendance">View Attendance</a>
</div>

<c:if test="${empty employees}">
  <p>No employees found.</p>
</c:if>

<table class="table">
  <tr>
    <th>ID</th><th>Name</th><th>Email</th><th>Salary</th><th>Joined</th><th>User</th><th>Action</th>
  </tr>
  <c:forEach var="e" items="${employees}">
    <tr>
      <td>${e.id}</td><td>${e.name}</td><td>${e.email}</td>
      <td>${e.salary}</td><td>${e.joinDate}</td><td>${e.userId}</td>
      <td>
        <form method="post" action="${pageContext.request.contextPath}/admin/remove-employee"
              onsubmit="return confirm('Remove this employee?');">
          <input type="hidden" name="id" value="${e.id}">
          <button type="submit" class="btn btn-danger">Remove</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<jsp:include page="footer.jsp"/>
