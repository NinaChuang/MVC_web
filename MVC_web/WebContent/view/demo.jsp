<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).on("click", "#submit", function(event) {

		var msg = $('#msg').val();
		/* stop form from submitting normally */
		event.preventDefault();
		$.get("controllerServletImpl", {
			msg : msg
		}, function(responseText) {
			console.log(responseText);
			$("#testMsg").text(responseText);
		});
	});
</script>
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
					輸入訊息<br /> <input type="text" id="msg" />
					<button id="submit">按鈕</button>

				</form>
				<div id="testMsg"></div>
	</center>
</body>
</html>