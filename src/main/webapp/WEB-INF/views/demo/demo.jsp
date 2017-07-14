<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<% response.addHeader("x-frame-options","SAMEORIGIN"); %>
</head>
<body>
			
<div class="form-group">
	<label class="col-sm-2 control-label">Select Demo</label>
	<div class="col-sm-3">
		<select id="demoSelect">
			<option value="one">one</option>
			<option value="three">three</option>
			<option value="demo">demo</option>
		</select>
	</div>
</div>

<iframe src="demo/demo_for_iframe"></iframe>

</body>