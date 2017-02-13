<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><sitemesh:write property='title'/></title>
<su:link href="/static/bootstrap/css/bootstrap.min.css"></su:link>
<su:link href="/static/intro/introjs.css"></su:link>
<su:script src="/static/jquery/jquery.min.js"></su:script>
<su:script src="/static/bootstrap/js/bootstrap.min.js"></su:script>
<su:script src="/static/intro/intro.js"></su:script>
<sitemesh:write property='head' />
</head>
<body>
	<%@ include file="header.jsp"%>
	<sitemesh:write property='body' />
	<%@ include file="footer.jsp"%>
</body>