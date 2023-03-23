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
<style>
</style>
</head>

<body>

<!-- #header start -->
<%@ include file="/resources/inc/header.jsp" %>
<!-- // #header end -->

<div id="body_head">
	<h2>객실 정보</h2>
</div>
<div id="body_outer">
<div id="body">
<!-- ============================================================================= -->

<div class="room_list">
<table class="table table-bordered align-middle">
<thead>
<tr class="table-secondary">
	<th>#</th>
	<th>객실 이름</th>
	<th>객실 유형</th>
	<th>수용 인원</th>
	<th>가격</th>
	<th>사용 가능 여부</th>
	<th>현재 객실 상태</th>
	<th>수정</th>
</tr>
</thead>
<tbody>
<c:forEach items="${roomlist}" var="room">
<tr <c:if test="${room.ro_use eq 0}">class="table-danger"</c:if>>
	<th>${room.ro_id}</th>
	<td>${room.ro_name}</td>
	<td>${room.ro_type}</td>
	<td>${room.ro_capa}</td>
	<td>${room.ro_price}</td>
	<td>
		<c:choose>
			<c:when test="${room.ro_use eq 0}"><p class="text-danger fw-bold">사용 불가</p></c:when>
			<c:when test="${room.ro_use eq 1}">사용 가능</c:when>
		</c:choose>
	</td>
	<td>
		<c:if test="${not empty todaylist[room.ro_id]}"><a href="read?ro_id=${room.ro_id}" class="btn btn-success" role="button">객실 사용 중</a></c:if>
		<c:if test="${empty todaylist[room.ro_id]}"><a class="btn btn-outline-secondary disabled" role="button" aria-disabled="true">공실</a></c:if>
	</td>
	<td class="td_btn"><a class="btn btn-primary" href="register?ro_id=${room.ro_id }" role="button">수정</a></td>
</tr>
</c:forEach>
</tbody>
</table>

<a class="btn btn-primary" href="register" role="button">객실 추가</a>

</div>

<!-- ============================================================================= -->
</div><!-- #body_contents end -->
</div><!-- #body end -->

<!-- #footer start -->
<%@ include file="/resources/inc/footer.jsp" %>
<!-- // #footer end -->

</body>
</html>