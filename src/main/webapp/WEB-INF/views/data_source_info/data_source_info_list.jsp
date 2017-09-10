<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>数据源列表</title>
<su:link href="/static/bootstrap-table/bootstrap-table.css"></su:link>
<su:script src="/static/bootstrap-table/bootstrap-table.min.js"></su:script>
</head>
<body>

<su:table title="数据源" columns="name:name;type:type" listUri=""
          buttons="create:添加;return:返回" api="/api/projects"></su:table>

</body>