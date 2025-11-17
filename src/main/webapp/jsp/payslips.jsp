<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="header.jsp"/>
<h2>My Payslips</h2>
<table class="table">
  <tr><th>Month</th><th>Year</th><th>Basic</th><th>HRA</th><th>Deductions</th><th>Net</th></tr>
  <c:forEach var="p" items="${payslips}">
    <tr>
      <td>${p.month}</td>
      <td>${p.year}</td>
      <td>${p.basic}</td>
      <td>${p.hra}</td>
      <td>${p.deductions}</td>
      <td>${p.net}</td>
    </tr>
  </c:forEach>
</table>
<jsp:include page="footer.jsp"/>
