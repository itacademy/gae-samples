<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>掲示板アプリ</title>
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
  padding: 10px;
  border: solid 1px #999;
}
section h2 {
  padding: 0 0 10px 0
}
.error {
  color: #dc143c;
}

form p {
  padding: 3px;
  margin: 3px 0;
}

form p.error {
  background-color: pink;
}
input[name="body"] {
  width: 500px;
}

ul {
  padding-left: 20px;
}
ul li.finished {
  color: #ccc;
}
ul li a {
  padding-right: 10px;
}

ul li span.date {
  color: #ccc;
  font-size: 0.75em;
}

</style>

<section>
  <c:if test="${not empty errors}">
    <p class="error">入力にエラーがあります</p>
  </c:if>
  <form action="create">
    <p class="${f:errorClass('body', 'error')}"><input type="text" name="body"></p>
    <p><input type="submit" value="追加する"></p>
  </form>
</section>

<section>
  <h2>完了待ちのタスク</h2>
  <c:if test="${empty unfinishedTodos}">
    <p>今すべきことは特にありません</p>
  </c:if>
  <c:if test="${not empty unfinishedTodos}">
  <ul id="todos">
  <c:forEach var="todo" items="${unfinishedTodos}">
    <li class="todo">
      <a href="finish?id=${todo.id}">完了！</a>
      <span class="body">${f:h(todo.body)}</span>
      <span class="date">created at <fmt:formatDate value="${todo.createDate}" pattern="yyyy/MM/dd HH:mm" /></span>
    </li>
  </c:forEach>
  </ul>
  </c:if>
</section>

<section>
  <h2>完了したタスク</h2>
  <c:if test="${empty finishedTodos}">
    <p>完了したタスクはありません</p>
  </c:if>
  <c:if test="${not empty finishedTodos}">
  <ul id="todos">
  <c:forEach var="todo" items="${finishedTodos}">
    <li class="todo finished">
      <span class="body">${f:h(todo.body)}</span>
      <span class="date">finished at <fmt:formatDate value="${todo.finishDate}" pattern="yyyy/MM/dd HH:mm" /></span>
    </li>
  </c:forEach>
  </ul>
  </c:if>
</section>


