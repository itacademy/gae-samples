<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>書籍貸出管理アプリ</title>
<style>
</style>

<p class="desc">貸し出す書籍と、借りるユーザを選択してください</p>

<form action="lend">

<section>
  <h2>書籍の選択</h2>
  <ul id="books">
  <c:forEach var="book" items="${books}">
    <li>
      <input id="book-${book.id}" type="checkbox" name="bookId" value="${book.id}">
      <label for="book-${book.id}">${f:h(book.title)}</label>
    </li>
  </c:forEach>
  </ul>
</section>

<section>
  <h2>ユーザの選択</h2>
  <ul id="users">
  <c:forEach var="user" items="${users}">
    <li>
      <input id="user-${user.id}" type="checkbox" name="userId" value="${user.id}">
      <label for="user-${user.id}">${f:h(user.name)}</label>
    </li>
  </c:forEach>
  </ul>
</section>

<p><input type="submit" value="貸出"></p>

</form>
