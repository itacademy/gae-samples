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
}
th,td {
  border: solid 1px #999;
  padding: 4px;
}
</style>

<form action="update" method="post">
  <table>
    <tr>
      <th>名前</th>
      <td><input type="text" name="name" value="${name}"></td>
    </tr>
    <tr>
      <th>価格</th>
      <td><input type="text" name="price" value="${price}"></td>
    </tr>
  </table>
  <input type="hidden" name="id" value="${id}">
  <input type="submit" value="変更する">
</form>
