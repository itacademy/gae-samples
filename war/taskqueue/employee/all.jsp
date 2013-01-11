<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<!DOCTYPE html>

<meta charset="utf-8">
<title>employee list</title>
<style>
table {
  border-collapse: collapse;
}
th,td {
  border: solid 1px #999;
  padding: 4px;
}
</style>

<table>
  <tr>
    <th>ID</th>
    <th>名前</th>
    <th>所属部署</th>
  </tr>
<c:forEach var="emp" items="${employees}">
  <tr>
    <td>${emp.key.id}</td>
    <td>${f:h(emp.name)}</td>
    <td>${f:h(emp.deptName)}</td>
  </tr>
</c:forEach>
</table>
