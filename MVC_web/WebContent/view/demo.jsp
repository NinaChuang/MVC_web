<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url">${req.requestURL}</c:set>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="js/view.js"></script>
<title>Insert title here</title>
</head>
<body>

	<center>
		<h2>
			歡迎進入Servlet範例
			<h2>
				<hr />
				<br />
				<form>
					輸入訊息
					 <br /> 
					 <input type="text" id="msg" /> 
					<br /> 
					<select name="address" id='addressCity'>
					</select>
					<br /> 
					<select name="address" id='addressTown'>
					</select>
					<button id="submit">按鈕</button>

				</form>
				<div id="testMsg"></div>
	</center>
</body>
</html>