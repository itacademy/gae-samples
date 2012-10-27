<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>アドレス帳アプリ</title>
<style>
form {
  border: solid 1px #999;
  padding: 0 10px;
}

#contacts {
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
  <p>新しい連絡先を入力してください。</p>
  <p>名前: <input type="text" name="name" value="${name}"></p>
  <p>Eメール: <input type="text" name="email" value="${email}"></p>
  <p>電話: <input type="text" name="tel" value="${tel}"></p>
  <p><input type="submit" value="登録する"></p>
</form>

<c:if test="${empty contacts}">
  <p>連絡先が一冊も登録されていません</p>
</c:if>

<c:if test="${not empty contacts}">
  <table id="contacts">
    <tr>
      <th>ID</th>
      <th>名前</th>
      <th>Eメール</th>
      <th>電話</th>
    </tr>
    <c:forEach var="contact" items="${contacts}">
      <tr>
        <td class="number">${contact.id}</td>
        <td>${f:h(contact.name)}</td>
        <td>${f:h(contact.email)}</td>
        <td>${f:h(contact.tel)}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>

