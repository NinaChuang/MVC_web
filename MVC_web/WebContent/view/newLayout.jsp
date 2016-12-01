<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url">${req.requestURL}</c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NewLayout</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<base
	href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="js/ajaxfileupload.js"></script>
<script src="js/view.js"></script>
<style type="text/css">.thumb-image{width:200px;padding:5px;}</style>
</head>
<body>
<form>
	<div class="container" 　align="left" style="width: 550px;">
		<h1 align='center'>Member Profile</h1>
		<table class="table table-striped">
			<tbody>
				<tr>
					<td align='right'>
						<img src="/MVC_web/img/view.jpg" width="200" height="240" id="defaultImg">
						<div id="image-holder"></div>
					</td>
					<td align="button"><div class="form-group">

							<input type="file" class="form-control-file"
								id="exampleInputFile" aria-describedby="fileHelp"> <small
								id="fileHelp" class="form-text text-muted"></small>
						</div></td>
					<td></td>
				</tr>
				<tr>
					<td align='center'>姓名:</td>
					<td><input type="text" name="" value="" id="name"></td>
					<td></td>
				</tr>
				<tr>
					<td align='center'>年齡:</td>
					<td><input type="text" name="" value="" id="age"></td>
					<td></td>
				</tr>
				<tr>
					<td align='center'>性別:</td>
					<td>　　<input type="radio" name="sex" value="boy">男　　　<input
						type="radio" name="sex" value="girl">女</td>
					<td></td>
				</tr>
				<tr>
					<td align='center'>地址:</td>
					<td><select name="address" id="addressCity">

					</select></b>　　　<select name="address" id="addressTown">

					</select></td>
					<td></td>
				</tr>
				<tr>
					<td align='center'>E-MAIL:</td>
					<td><input type="text" name="" id="email"/></td>
					<td><font color="red" size="1" 　align='left' id="errorEmail"></font></td>
				</tr>
				<tr>
					<td align='center'>手機號碼:</td>
					<td><input type="text" name="" value="" id="phone"></td>
					<td><font color="red" size="1" id="errorPhone"></font></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><button type="button" class="btn btn-default" id="submitBtn">Submit</button></td>
					
					
				</tr>
			</tbody>
		</table>
	</div>
</form>
</body>
</html>