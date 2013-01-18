<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<!DOCTYPE html>

<meta charset="utf-8">
<title>ImagesService</title>

<div>
  <p class="caption">オリジナル画像</p>
  <p class="img"><img src="/images/resize?rate=100"></p>
</div>

<div>
  <p class="caption">縮小: 20%</p>
  <p class="img"><img src="/images/resize?rate=20"></p>
</div>

<div>
  <p class="caption">回転: 90度</p>
  <p class="img"><img src="/images/rotate?degree=90"></p>
</div>

<div>
  <p class="caption">左右反転</p>
  <p class="img"><img src="/images/horizontalFlip"></p>
</div>

<div>
  <p class="caption">上下反転</p>
  <p class="img"><img src="/images/verticalFlip"></p>
</div>

<div>
  <p class="caption">合成</p>
  <p class="img"><img src="/images/composite"></p>
</div>
