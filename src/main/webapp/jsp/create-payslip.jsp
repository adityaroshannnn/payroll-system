<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="header.jsp"/>
<h2>Create Payslip</h2>
<form method="post" action="${pageContext.request.contextPath}/admin/create-payslip" class="card">
  <label>Employee ID</label>
  <input type="number" name="employee_id" required>
  <label>Month (1-12)</label>
  <input type="number" name="month" min="1" max="12" required>
  <label>Year</label>
  <input type="number" name="year" required value="2025">
  <label>Basic</label>
  <input type="number" step="0.01" name="basic" required>
  <label>HRA</label>
  <input type="number" step="0.01" name="hra" required>
  <label>Deductions</label>
  <input type="number" step="0.01" name="deductions" required>
  <button type="submit">Generate</button>
</form>
<jsp:include page="footer.jsp"/>
