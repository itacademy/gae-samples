<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>書籍管理アプリ</title>
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

<form action="create">
  <p>タイトル: <input type="text" name="title" value="${title}"></p>
  <p>著者名: <input type="text" name="author" value="${author}"></p>
  <p>価格: <input type="text" name="price" value="${price}"></p>
  <p><input type="submit" value="登録する"></p>
</form>

<c:if test="${empty books}">
  <p>書籍が一冊も登録されていません</p>
</c:if>

<c:if test="${not empty books}">
  <table id="books">
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

