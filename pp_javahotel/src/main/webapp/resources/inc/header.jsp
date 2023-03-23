<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="header_outer">
<div id="header">
	<h1><a href="/pp_javahotel/">JAVA HOTEL</a></h1>
	<div id="navi">
	
		<div id="gnb">
			<c:if test="${sessionScope.mb_no==null}">
				<span><a href="#">LOGIN</a></span>
			</c:if>
			<c:if test="${sessionScope.mb_no!=null}">
				<span class="bold"><i class="fas fa-user"></i> ${sessionScope.mb_name}</span>
				<span><a href="#"><i class="fa-solid fa-arrow-right-from-bracket"></i> LOGOUT</a></span>
			</c:if>
			
		</div>
		<ul class="clearfix">
			<li><a href="../room/list">객실 정보</a></li>
			<li><a href="../reserve/list">예약 현황</a></li>
			<li><a href="../reserve/read">예약 확인</a></li>
			<li><a href="../reserve/admin">관리자</a></li>
		</ul>		
	</div>
</div>
</div>