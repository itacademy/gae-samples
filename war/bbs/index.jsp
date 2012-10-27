<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>掲示板アプリ</title>
<style>
form {
  border: solid 1px #999;
  padding: 0 10px;
}

#books {
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

<c:if test="${empty messages}">
  <p>まだ投稿がありません</p>
</c:if>

<c:if test="${not empty messages}">
  <table id="messages">
    <tr>
      <th>ID</th>
      <th>タイトル</th>
      <th>著者名</th>
      <th>価格</th>
      <th>入荷日</th>
    </tr>
    <c:forEach var="book" items="${books}">
      <tr>
        <td class="number">${book.id}</td>
        <td>${f:h(book.title)}</td>
        <td>${f:h(book.author)}</td>
        <td class="number">${book.price}円</td>
        <td><fmt:formatDate value="${book.arrivalDate}" pattern="yyyy/MM/dd" /></td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<form action="create">
  <p><textarea name="body" cols="50" rows="5">${f:h(body)}</textarea></p>
  <p>名前: <input type="text" name="poster" value="${poster}"></p>
  <p><input type="submit" value="投稿する"></p>
</form>

