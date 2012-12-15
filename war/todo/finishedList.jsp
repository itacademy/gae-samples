<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>TODOサイト - 完了したTODO</title>
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

</style>

<section id="list">
  <c:if test="${empty todos}">
    <p>完了したTODOはありません</p>
  </c:if>
  <c:if test="${not empty todos}">
  <ul id="todos">
  <c:forEach var="todo" items="${todos}">
    <li class="todo">
      <span class="body">${f:h(todo.body)}</span>
      <span class="date">finished at <fmt:formatDate value="${todo.finishDate}" pattern="yyyy/MM/dd HH:mm" /></span>
    </li>
  </c:forEach>
  </ul>
  </c:if>
</section>
