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

.error {
  color: #dc143c;
}

form {
  
}

form p {
  padding: 3px;
  margin: 3px 0;
}

form p.error {
  background-color: pink;
}

#messages {
  margin: 0 0 30px 0;
  border-top: solid 1px #999;
}

#messages li.message {
  display: block;
  border-bottom: solid 1px #999;
  font-family: monospace;
}

#messages li.message .body {
  padding: 10px 10px;
}

#messages li.message .meta {
  padding: 5px 10px;
  background-color: #f5f5f5;
  border-top: dashed 1px #999;
  text-align: right;
}

#messages li.message .meta .poster {
  padding-right: 10px;
}

#messages li.message .meta .postDate {
  
}
</style>

<c:if test="${empty messages}">
  <p>まだ投稿がありません</p>
</c:if>

<c:if test="${not empty messages}">
<ul id="messages">
<c:forEach var="message" items="${messages}">
  <li class="message">
    <p class="body">${message.bodyHtml}</p>
    <p class="meta">
      <span class="poster">${f:h(message.poster)}</span> <span
        class="postDate">at <fmt:formatDate
          value="${message.postDate}" pattern="yyyy/MM/dd HH:mm:ss" /></span>
    </p>
  </li>
</c:forEach>
</ul>
</c:if>

<c:if test="${not empty errors}">
  <p class="error">入力にエラーがあります</p>
</c:if>

<form action="create">
  <p class="${f:errorClass('poster', 'error')}">
    名前: <input type="text" name="poster" value="${poster}">
  </p>
  <p class="${f:errorClass('body', 'error')}">
    <textarea name="body" cols="80" rows="5">${f:h(body)}</textarea>
  </p>
  <p>
    <input type="submit" value="投稿する">
  </p>
</form>

