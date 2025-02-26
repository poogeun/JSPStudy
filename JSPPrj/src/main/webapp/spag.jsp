<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("result", "hello");
%>
<body>
	<%=request.getAttribute("result") %>입니다.
	${requestScope.result}
	${names[0]}
	${notice.title}
	${result}
	${param.n > 3}
	${param.n == null || param.n == ''}
	${empty param.n?'값이 비어 있습니다':param.n}
</body>
</html>