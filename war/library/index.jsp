<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>書籍貸出管理アプリ</title>
<style>
html {
  font-family: "メイリオ";
}

h2 {
  font-size: 18px;
  font-weight: bold;
}
#books {
  border-collapse: collapse;
}

tr.rented {
  color: #999;
}

th,td {
  padding: 5px;
  border: solid 1px #999;
}

.number {
  text-align: right;
}
</style>

<p class="desc">貸し出す書籍と、借りるユーザを選択してください</p>

<form action="lend">

<section>
  <h2>ユーザの選択</h2>
  <select name="userId">
<c:forEach var="user" items="${users}">
    <option value="${user.id}">${f:h(user.name)}</option>
</c:forEach>
  </select>
</section>

<section>
  <h2>書籍の選択</h2>
  <table id="books">
    <tr>
      <th></th>
      <th>ID</th>
      <th>タイトル</th>
      <th>著者名</th>
      <th>価格</th>
      <th>入荷日</th>
    </tr>
    <c:forEach var="book" items="${books}">
      <tr <c:if test="${book.rented}">class="rented"</c:if>>
        <td>
          <c:if test="${book.rented}">貸出中</c:if>
          <c:if test="${!book.rented}"><input type="radio" name="bookId" value="${book.id}"></c:if>
        </td>
        <td class="number">${book.id}</td>
        <td>${f:h(book.title)}</td>
        <td>${f:h(book.author)}</td>
        <td class="number">${book.price}円</td>
        <td><fmt:formatDate value="${book.arrivalDate}" pattern="yyyy/MM/dd" /></td>
      </tr>
    </c:forEach>
  </table>
</section>

<p><input type="submit" value="貸出"></p>

</form>
