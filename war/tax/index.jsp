<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>税抜価格計算アプリ</title>

<form action="calc">
  <p>税込価格 <input type="text" name="price" size="8"> 円</p>
  <p><input type="submit" value="計算"></p>
</form>
