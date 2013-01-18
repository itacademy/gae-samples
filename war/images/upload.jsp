<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<!DOCTYPE html>

<meta charset="utf-8">
<title>Images Service</title>

<p>ファイル名: ${f:h(imageFile.fileName)}</p>
<p>リサイズ: ${f:h(resize)}%</p>
<p><img src="/images/get?id=${imageFile.key.id}"></p>
