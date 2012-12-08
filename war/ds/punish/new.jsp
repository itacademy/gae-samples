<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>懲戒処分登録</title>

<form action="create">
  <p>${f:h(employee.name)}（ID:${employee.key.id}）の処分内容</p>
  <p><input type="text" name="desc" value="${desc}"></p>
  <input type="hidden" name="employeeId" value="${employee.key.id}">
  <p><input type="submit" value="登録する"></p>
</form>
