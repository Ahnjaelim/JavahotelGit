<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
Calendar cal = Calendar.getInstance();
int curYear = cal.get(Calendar.YEAR);
int curMon = cal.get(Calendar.MONTH); // 0:1월 ~ 11:12월
int today = cal.get(Calendar.DATE);
String curMonStr=String.format("%02d", cal.get(Calendar.MONTH)+1);
String indextoday = String.valueOf(curYear)+"-"+curMonStr+"-"+String.format("%02d", today);
pageContext.setAttribute("indextoday", indextoday);

// 달력 한계 설정
int lastYear = curYear;
int lastMon = curMon+1;
if(lastMon>11){
	lastYear = lastYear+1;
	lastMon=0;
}
Calendar tempcal = Calendar.getInstance();
tempcal.set(lastYear, lastMon, 1);
int lastday = tempcal.getActualMaximum(Calendar.DAY_OF_MONTH);
Calendar lastcal = Calendar.getInstance();
lastcal.set(lastYear, lastMon, lastday);
SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
String limitdate = sdf.format(lastcal.getTime());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JAVA HOTEL</title>
<link href="${contextPath}/resources/css/sub_all.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- 데이트 피커 -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
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

<div class="board_write">

	<form action="register" method="post">
	<input type="hidden" name="bo_table" />

	<div class="input-group">
		<span class="input-group-text">객실 번호</span>
		<select class="form-select" name="ro_id" onchange="if(this.value) location.href='register?ro_id='+(this.value)+'&re_startday=${param.re_startday}';">
		<c:forEach items="${roomlist}" var="room">
			<option value="${room.ro_id }"<c:if test="${param.ro_id==room.ro_id}"> selected</c:if>>${room.ro_name }</option>
		</c:forEach>
		</select>		
	</div>
	<div class="input-group">
		<span class="input-group-text">예약자 이름</span>
		<input type="text" name="re_name" value="" class="form-control" required />
	</div>
	<div class="input-group">
		<span class="input-group-text">비밀번호</span>
		<input type="text" name="re_password" value="" class="form-control" required />
	</div>
	<div class="input-group">
		<span class="input-group-text">핸드폰 번호</span>
		<input type="text" name="re_phone" value="" class="form-control" required />
	</div>
	<div class="input-group">
		<span class="input-group-text">인원수</span>
		<select class="form-select" name="re_capa">
			<option value="1">1명</option>
			<option value="2">2명</option>
			<option value="3">3명</option>
			<option value="4">4명</option>
		</select>			
	</div>	
	<div class="input-group">
		<span class="input-group-text">숙박 시작일</span>
		<!--  <input type="date" name="re_startday" value="${param.re_startday }" class="form-control" min="${indextoday }" />
		<span class="input-group-text">숙박 종료일</span>
		<input type="date" name="re_endday" value="" class="form-control" min="${indextoday }" />-->
		<input type="text" name="datefilter" value="" class="form-control" autocomplete="off" required />
		<input type="hidden" name="re_startday" value="" />
		<input type="hidden" name="re_endday" value="" />
		<input type="text" name="diffdays" class="form-control" value="0박 0일" />
	</div>

	<div class="input-group">
		<span class="input-group-text">결제 금액</span>
		<input type="text" name="re_price" value="${room.ro_price}" class="form-control" readonly />
	</div>
	<div class="input-group">
		<span class="input-group-text">결제</span>
		<p class="form-control" style="margin:0;">
			<input type="radio" name="re_payment" value="0" checked /> 인터넷 결제　
			<input type="radio" name="re_payment" value="1" /> 현장 결제
		</p>
		
	</div>
	<button type="submit" class="btn btn-primary">예약하기</button>
	</form>
		
</div>

<script type="text/javascript">
$(function() {

  $('input[name="datefilter"]').daterangepicker({
	    "locale": {
	        "format": "YYYY/MM/DD", // 처음 포멧 설정한대로 다른 날짜도 설정
	        "separator": " - ",
	        "applyLabel": "Apply",
	        "cancelLabel": "Cancel",
	        "fromLabel": "From",
	        "toLabel": "To",
	        "customRangeLabel": "Custom",
	        "weekLabel": "W",
	        "daysOfWeek": [
	            "일",
	            "월",
	            "화",
	            "수",
	            "목",
	            "금",
	            "토"
	        ],
	        "monthNames": [
	            "1월",
	            "2월",
	            "3월",
	            "4월",
	            "5월",
	            "6월",
	            "7월",
	            "8월",
	            "9월",
	            "10월",
	            "11월",
	            "12월"
	        ],
	        "firstDay": 0
	    },
	    "minDate": moment().format("YYYY-MM-DD"),
	    "maxDate": "<%=limitdate%>",
	    // "startDate": "${param.re_startday}",
	    // "endDate" :  "${param.re_startday}",
	    autoUpdateInput: false,
	    isInvalidDate: function(date) { //return date.day() != 1;
	    	// return date.format("YYYY-MM-DD")=="2023-01-30" || date.format("YYYY-MM-DD")=="2023-01-29";
	    	return <c:forEach items="${map}" var="map">date.format("YYYY-MM-DD")=="${map.key}" || </c:forEach>null
	    }
  });

  $('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
      $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
      $('input[name="re_startday"]').val(picker.startDate.format('YYYY-MM-DD'));
      $('input[name="re_endday"]').val(picker.endDate.format('YYYY-MM-DD'));
      // 숙박일 계산
      var diffdays = picker.endDate.diff(picker.startDate, 'days');
      $('input[name="diffdays"]').val(diffdays+"박 "+(diffdays+1)+"일");
      // 가격 계산
      $('input[name="re_price"]').val('${room.ro_price}');
      var price = parseInt($('input[name="re_price"]').val())*diffdays;
      $('input[name="re_price"]').val(price);

  });

  $('input[name="datefilter"]').on('cancel.daterangepicker', function(ev, picker) {
      $(this).val('');
      $('input[name="re_startday"]').val('');
      $('input[name="re_endday"]').val('');
      $('input[name="diffdays"]').val("0박 0일");
      $('input[name="re_price"]').val('${room.ro_price}');
      
  });

});
</script>

<!-- ============================================================================= -->
</div><!-- #body_contents end -->
</div><!-- #body end -->

<!-- #footer start -->
<%@ include file="/resources/inc/footer.jsp" %>
<!-- // #footer end -->

</body>
</html>