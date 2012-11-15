<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>あいさつアプリ</title>
<style>
ul.error {
  color: red;
}
input.error {
  border: solid 1px red;
  background-color: lavenderblush;
}
</style>

<form action="reply">
  <p>あなたの名前: 
    <input type="text" name="name" value="${name}" class="${f:errorClass('name','error')}">
  </p>
  <ul class="error">
<c:forEach var="e" items="${f:errors()}">
    <li>${f:h(e)}</li>
</c:forEach>
  </ul>
  <p><input type="submit" value="あいさつする"></p>
</form>
