<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>ユーザ管理アプリ</title>
<style>
form {
  border: solid 1px #999;
  padding: 0 10px;
}

#users {
  border-collapse: collapse;
}

th,td {
  padding: 5px;
  border: solid 1px #999;
}
.number {
  text-align: right;
}
</style>

<form action="create">
  <p>新しいユーザの名前を入力してください。</p>
  <p>名前: <input type="text" name="name"></p>
  <p><input type="submit" value="登録する"></p>
</form>

<c:if test="${empty users}">
  <p>ユーザがひとりも登録されていません</p>
</c:if>

<c:if test="${not empty users}">
  <table id="users">
    <tr>
      <th>ID</th>
      <th>名前</th>
      <th>登録日時</th>
    </tr>
    <c:forEach var="user" items="${users}">
      <tr>
        <td class="number">${user.id}</td>
        <td>${f:h(user.name)}</td>
        <td><fmt:formatDate value="${user.createDate}" pattern="yyyy/MM/dd" /></td>
      </tr>
    </c:forEach>
  </table>
</c:if>

