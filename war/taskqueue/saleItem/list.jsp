<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<!DOCTYPE html>

<meta charset="utf-8">
<title>アイテム一覧</title>
<style>
table {
  border-collapse: collapse;
  border: solid 1px #999;
}
th,td {
  padding: 8px;
}
</style>

<p>なにをかうかね？</p>
<table>
<c:forEach var="si" items="${saleItems}">
  <tr>
    <td>${f:h(si.itemName)}</td>
    <td>${si.itemPrice} G</td>
  </tr>
</c:forEach>
</table>
