<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="en">

<head>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/everythingelse.css" rel="stylesheet">

</head>
<%@ include file="components/header.jsp" %>  
<body>
    <div>
<h1>Something went wrong</h1>
<p>Sorry about that. Please <a href="mailto:duchenjacob@gmail.com">tell the developer </a> what went wrong, <c:url value="/home" var="homeLink"></c:url><a href="${homeLink}">try login, or just try again</a>.</p>
<p><a href="mailto:duchenjacob@gmail.com">Feedback</a> which is greatly appreciated can be given to us <a href="mailto:duchenjacob@gmail.com">here</a>.</p>

    </div>

</body>
        <br />
         <br />
<div>
<h1>BlogCity</h1>
<h4>Created by Jacob Duchen</h4>
<p><a href="mailto:duchenjacob@gmail.com">Feedback</a> <c:url value="/home" var="homeLink"></c:url>
<a href="${homeLink}">Home</a></p>
</div>
