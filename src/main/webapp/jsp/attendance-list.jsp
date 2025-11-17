<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<h2>Employee Attendance</h2>

<form method="get" action="${pageContext.request.contextPath}/admin/attendance" class="card" style="max-width:400px;">
  <label>Select Employee</label>
  <select name="empId" required>
    <option value="">-- Choose Employee --</option>
    <c:forEach var="e" items="${empList}">
      <option value="${e.id}">${e.name} (${e.email})</option>
    </c:forEach>
  </select>
  <button type="submit" class="btn">View Attendance</button>
</form>

<c:if test="${not empty selectedEmployee}">
  <h3>${selectedEmployee.name}'s Attendance</h3>
  <table class="table">
    <tr><th>Date</th><th>Status</th></tr>
    <c:forEach var="a" items="${attendanceList}">
      <tr>
        <td>${a.attDate}</td>
        <td>
          <c:choose>
            <c:when test="${a.status == 'PRESENT'}">
              <span style="color:#00ff88;">Present</span>
            </c:when>
            <c:otherwise>
              <span style="color:#ff4b4b;">Absent</span>
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<jsp:include page="footer.jsp"/>
