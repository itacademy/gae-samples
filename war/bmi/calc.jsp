<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<meta charset="utf-8">
<title>BMI計算アプリ</title>

<p>身長 ${height}cm、体重 ${weight}kg のあなたのBMIは...</p>
<p><fmt:formatNumber value="${bmi}" pattern="0.00" /> です。</p>
