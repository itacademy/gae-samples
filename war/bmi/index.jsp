<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>BMI計算アプリ</title>

<form action="calc">
  <p>身長: <input type="text" name="height" size="4"> cm</p>
  <p>体重: <input type="text" name="weight" size="4"> kg</p>
  <p><input type="submit" value="計算"></p>
</form>
