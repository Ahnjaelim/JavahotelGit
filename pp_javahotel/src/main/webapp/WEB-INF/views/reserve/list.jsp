<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>

<%
Calendar cal = Calendar.getInstance();
int curYear = cal.get(Calendar.YEAR);
int curMon = cal.get(Calendar.MONTH); // 0:1월 ~ 11:12월
int today = cal.get(Calendar.DATE);
String curMonStr=String.format("%02d", cal.get(Calendar.MONTH)+1);
String indextoday = String.valueOf(curYear)+curMonStr+String.format("%02d", today);

if(request.getParameter("curYear")!=null&&request.getParameter("curMon")!=null){
	curYear=Integer.parseInt(request.getParameter("curYear"));
	curMon=Integer.parseInt(request.getParameter("curMon"));
}

// 해당 월 1일 요일 구하기
Calendar first = Calendar.getInstance();
first.set(curYear, curMon , 1); // 해당월 -1
int wday = first.get(Calendar.DAY_OF_WEEK);

// 해당 월 마지막 날 
int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

// 이전 달, 다음 달 구하기
int prevYear = curYear;
int prevMon = curMon-1;
if(prevMon==-1){
	prevYear=prevYear-1;
	prevMon=11;
}
int nextYear = curYear;
int nextMon = curMon+1;
if(nextMon==12){
	nextYear=nextYear+1;
	nextMon=0;
}

// JSTL로 변수 전달
pageContext.setAttribute("curYear", curYear);
pageContext.setAttribute("curMon", curMon);
pageContext.setAttribute("indextoday", indextoday);
pageContext.setAttribute("wday", wday);
pageContext.setAttribute("last", last);
pageContext.setAttribute("prevYear", prevYear);
pageContext.setAttribute("prevMon", prevMon);
pageContext.setAttribute("nextYear", nextYear);
pageContext.setAttribute("nextMon", nextMon);

%>

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
	<h2>객실 예약</h2>
</div>
<div id="body_outer">
<div id="body">
<!-- ============================================================================= -->

<div class="calendar">

<h3>
	<a href="?curYear=${prevYear}&curMon=${prevMon}" class="btn btn-outline-secondary"><i class="fa-solid fa-angle-left"></i></a>
	<%=curYear%>년 ${curMon+1}월
	<a href="?curYear=${nextYear}&curMon=${nextMon}" class="btn btn-outline-secondary"><i class="fa-solid fa-angle-right"></i></a>
</h3>
<fmt:formatNumber var="curMon" minIntegerDigits="2" value="${curMon+1}" type="number"/>

<table class="table table-bordered">
<colgroup>
	<col width="14.28%" />
	<col width="14.28%" />
	<col width="14.28%" />
	<col width="14.28%" />
	<col width="14.28%" />
	<col width="14.28%" />
	<col width="14.28%" />
	<col width="14.32%" />
</colgroup>
<thead>
<tr class="table-secondary">
	<th class="table-danger">일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th class="table-primary">토</th>
</tr>
</thead>
<tbody>
<tr>
<c:forEach var="i" begin="1" end="${wday-1}" step="1">
	<td>&nbsp;</td>
</c:forEach>
<c:forEach var="i" begin="1" end="${last}" step="1">
	<fmt:formatNumber var="day" minIntegerDigits="2" value="${i}" type="number"/>
	<c:set var="keydate" value="${curYear}-${curMon}-${day}" />
	<c:set var="indexdate" value="${curYear}${curMon}${day}" />
	<td<c:if test="${indexdate == indextoday}"> class="today"</c:if>>
		<p>${i}</p>
		<c:if test="${indexdate < indextoday}">
		</c:if>
		<c:if test="${indexdate >= indextoday}">
			<c:forEach items="${roomlist}" var="room">
				<c:if test="${room.ro_use==1 }">
					<c:if test="${not empty maplist[room.ro_id][keydate].re_id}"><button type="button" class="btn btn-outline-secondary btn-sm" disabled>${room.ro_name} 예약 완료</button></c:if>
					<c:if test="${empty maplist[room.ro_id][keydate].re_id}"><button type="button" class="btn btn-secondary btn-sm" onclick="location.href='register?ro_id=${room.ro_id }&re_startday=${keydate}';">${room.ro_name} 예약 가능</button></c:if>
				</c:if>
			</c:forEach>
		</c:if>
	</td>
	<c:set var="wday" value="${wday+1}" />
	<c:if test="${wday>7}">
		<c:set var="wday" value="1" />
		</tr><tr>
	</c:if>
</c:forEach>
<c:forEach var="i" begin="${wday}" end="7" step="1">
	<td>&nbsp;</td>
</c:forEach>
</tr>
</tbody>
</table>

</div>

<!-- ============================================================================= -->
</div><!-- #body_contents end -->
</div><!-- #body end -->

<!-- #footer start -->
<%@ include file="/resources/inc/footer.jsp" %>
<!-- // #footer end -->

</body>
</html>