<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>ユーザ管理アプリ</title>
<style>
form {
  border: solid 1px #999;
  padding: 0 10px;
}
</style>

<form action="create">
  <p>新しいユーザの名前とメアドを入力してください。</p>
  <p>名前: <input type="text" name="name"></p>
  <p>メアド: <input type="text" name="email"></p>
  <p><input type="submit" value="登録する"></p>
</form>

<a href="list">ユーザ一覧を見る</a>
