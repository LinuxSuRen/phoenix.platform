<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Oauth Approval</title>
</head>
<body><h1>OAuth Approval</h1>

<p>Do you authorize '${authorizationRequest.clientId}' to access your protected resources?</p>

<form id='confirmationForm' name='confirmationForm' action='<%=basePath %>/oauth/authorize.su'
      method='post'>
    <input name='user_oauth_approval' value='true' type='hidden'/>
    <label> <input name='authorize' value='Authorize' type='submit' class="btn btn-success"/></label>
</form>
<form id='denialForm' name='denialForm' action='<%=basePath %>/oauth/authorize.su' method='post'>
    <input name='user_oauth_approval' value='false' type='hidden'/>
    <label><input name='deny' value='Deny' type='submit' class="btn btn-warning"/></label>
</form>
</body>
</html>