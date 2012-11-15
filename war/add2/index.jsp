<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>足し算アプリ</title>
<style>
ul.error {
  color: red;
}
input.error {
  border: solid 1px red;
  background-color: lavenderblush;
}
</style>

<form action="calc">
  <p>
    <input type="text" name="num1" value="${num1}" size="4" class="${f:errorClass('num1','error')}"> +
    <input type="text" name="num2" value="${num2}" size="4" class="${f:errorClass('num2','error')}"> =
  </p>
  <ul class="error">
<c:forEach var="e" items="${f:errors()}">
    <li>${f:h(e)}</li>
</c:forEach>
  </ul>
  <p>
    <input type="submit" value="計算">
  </p>
</form>
