<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>血液型占いアプリ</title>

<form action="result">
  <p>あなたの血液型を選択してください</p>
  <select name="bloodType">
    <option value="A">A型</option>
    <option value="B">B型</option>
    <option value="O">O型</option>
    <option value="AB">AB型</option>
  </select>
  <p><input type="submit" value="占う"></p>
</form>
