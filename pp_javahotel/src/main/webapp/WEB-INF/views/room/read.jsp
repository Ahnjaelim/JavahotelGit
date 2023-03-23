<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JAVA HOTEL</title>
<link href="${contextPath}/resources/css/sub_all.css" rel="stylesheet" />
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/5efc80fbcc.js" crossorigin="anonymous"></script>
</head>

<body>

<!-- #header start -->
<%@ include file="/resources/inc/header.jsp" %>
<!-- // #header end -->

<div id="body_head">
	<h2>페이지 제목</h2>
</div>
<div id="body_outer">
<div id="body">
<!-- ============================================================================= -->

<table class="table table-bordered">
<colgroup>
</colgroup>
<thead>
<tr class="table-secondary">
	<th>#</th>
	<th>객실 번호</th>
	<th>예약자</th>
	<th>비밀번호</th>
	<th>연락처</th>
	<th>숙박 시작일</th>
	<th>숙박 종료일</th>
	<th>인원수</th>
	<th>결제 금액</th>
	<th>결제 여부</th>
	<th>예약일</th>
</tr>
</thead>
<tbody>
<tr>
	<td>${dto.re_id }</td>
	<td>${dto.ro_id }</td>
	<td>${dto.re_name }</td>
	<td>${dto.re_password }</td>
	<td>${dto.re_phone }</td>
	<td>${dto.re_startday }</td>
	<td>${dto.re_endday }</td>
	<td>${dto.re_capa }</td>
	<td>${dto.re_price }</td>
	<td>${dto.re_payment }</td>
	<td>${dto.re_datetime }</td>
</tbody>
</table>

<!-- ============================================================================= -->
</div><!-- #body_contents end -->
</div><!-- #body end -->

<!-- #footer start -->
<%@ include file="/resources/inc/footer.jsp" %>
<!-- // #footer end -->

</body>
</html>