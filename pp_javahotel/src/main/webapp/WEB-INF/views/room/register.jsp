<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
String action = "register";
if(request.getParameter("ro_id")!=null){
	action = "modify";
}
// JSTL로 전달
pageContext.setAttribute("action", action);
%>

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

<div class="board_write">

	<form action="${action }" method="post">
	<input type="hidden" name="ro_id" value="${room.ro_id }" />
	<div class="input-group">
		<span class="input-group-text">객실 이름 (호 수)</span>
		<input type="text" name="ro_name" value="${room.ro_name }" class="form-control" required />
	</div>
	<div class="input-group">
		<span class="input-group-text">객실 유형</span>
		<input type="text" name="ro_type" value="${room.ro_type }" class="form-control" required />
	</div>
	<div class="input-group">
		<span class="input-group-text">수용 인원</span>
		<input type="text" name="ro_capa" value="${room.ro_capa }" class="form-control" required />
	</div>
	<div class="input-group">
		<span class="input-group-text">이용 가격 (1박 기준)</span>
		<input type="text" name="ro_price" value="${room.ro_price }" class="form-control" required />
	</div>
	<div class="input-group">
		<span class="input-group-text">사용 여부</span>
		<p class="form-control" style="margin:0;">
			<input type="radio" name="ro_use" value="1" <c:if test="${room.ro_use eq 1}">checked</c:if> /> 사용 가능　
			<input type="radio" name="ro_use" value="0" <c:if test="${room.ro_use eq 0}">checked</c:if> /> 사용 불가
		</p>
		
	</div>	
	
	<div class="input-group">
		<span class="input-group-text">객실 상태</span>
		<select name="ro_state" class="form-control">
			<option value="0" <c:if test="${room.ro_use eq 0}">selected</c:if>>공실</option>
			<option value="1" <c:if test="${room.ro_use eq 1}">selected</c:if>>사용 중</option>
			<option value="2" <c:if test="${room.ro_use eq 2}">selected</c:if>>청소 중</option>
			<option value="3" <c:if test="${room.ro_use eq 3}">selected</c:if>>공사 중</option>
		</select>
	</div>
	<button type="submit" class="btn btn-primary">확인</button>
	</form>
					
</div>
<!-- ============================================================================= -->
</div><!-- #body_contents end -->
</div><!-- #body end -->

<!-- #footer start -->
<%@ include file="/resources/inc/footer.jsp" %>
<!-- // #footer end -->

</body>
</html>