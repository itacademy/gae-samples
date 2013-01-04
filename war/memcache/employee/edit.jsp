<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>従業員情報 変更フォーム</title>
<style>
table {
  border-collapse: collapse;
}

th,td {
  padding: 5px 20px 5px 10px;
  border: solid 1px #999;
  text-align: left;
}
</style>

<form action="update" method="post">
  <table>
    <tr>
      <th>ID</th>
      <td>${employee.key.id}</td>
    </tr>
    <tr>
      <th>Name</th>
      <td><input type="text" name="name" value="${f:h(employee.name)}"></td>
    </tr>
    <tr>
      <th>Dept</th>
      <td>
        <select name="deptId">
<c:forEach var="dept" items="${depts}">
          <option value="${dept.key.id}"
            <c:if test="${employee.deptKey == dept.key}">selected</c:if>>${f:h(dept.name)}</option>
</c:forEach>
        </select>
      </td>
    </tr>
  </table>
  <input type="hidden" name="id" value="${employee.key.id}">
  <input type="submit" value="更新">
</form>
