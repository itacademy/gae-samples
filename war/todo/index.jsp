<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>TODOサイト - トップページ</title>
<style>
* {
  margin: 0;
  padding: 0;
}

body {
  width: 600px;
  margin: 0 auto;
  padding: 10px;
}
section {
  margin: 20px 0;
}

.error {
  color: #dc143c;
}

#form {
  margin: 20px;
  padding: 10px;
  border: solid 1px #999;
}

#form p {
  padding: 3px;
  margin: 3px 0;
}

#form p.error {
  background-color: pink;
}

#form input[name="body"] {
  width: 500px;
}

#list ul {
  padding-left: 50px;
}

#list ul li {
  padding: 4px 0;
}

#list ul li a {
  padding-right: 10px;
}

#list ul li span.date {
  color: #ccc;
  font-size: 0.75em;
}

#actions p {
  text-align: right;
}

</style>

<form id="form" action="create">
  <c:if test="${not empty errors}">
    <p class="error">入力にエラーがあります</p>
  </c:if>
    <p class="${f:errorClass('body', 'error')}"><input type="text" name="body"></p>
    <p><input type="submit" value="登録する"></p>
</form>

<section id="list">
  <c:if test="${empty todos}">
    <p>今すべきことは特にありません</p>
  </c:if>
  <c:if test="${not empty todos}">
  <ul id="todos">
  <c:forEach var="todo" items="${todos}">
    <li class="todo">
      <a href="finish?id=${todo.id}">完了！</a>
      <span class="body">${f:h(todo.body)}</span>
      <span class="date">created at <fmt:formatDate value="${todo.createDate}" pattern="yyyy/MM/dd HH:mm" /></span>
    </li>
  </c:forEach>
  </ul>
  </c:if>
</section>

<section id="actions">
  <p><a href="finishedList">完了したTODO一覧</a></p>
</section>
