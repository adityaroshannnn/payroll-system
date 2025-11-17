<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h2>Employee Dashboard</h2>

<div class="card">
  <h3>Quick Actions</h3>
  <a class="btn" href="${pageContext.request.contextPath}/employee/payslips">My Payslips</a>
</div>

<div class="card">
  <h3>Mark Today's Attendance</h3>
  <form method="post" action="${pageContext.request.contextPath}/employee/mark-attendance">
    <label><input type="radio" name="status" value="PRESENT" checked> Present</label>
    <label><input type="radio" name="status" value="ABSENT"> Absent</label>
    <button type="submit" class="btn" style="margin-top:10px;">Submit</button>
  </form>
</div>

<jsp:include page="footer.jsp"/>
