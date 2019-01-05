<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="en">

<head>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/everythingelse.css" rel="stylesheet">
 <link href="${pageContext.request.contextPath}/css/listofpics.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/picPage.css" rel="stylesheet">
 <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR" rel="stylesheet">
</head>
<body style="background-color: #c2e9fb;">
  <%@ include file="../components/header.jsp" %>

    <Br/>
    <Br/>
    	
	<div class="mainPic">
		<img sRC="https://s3.amazonaws.com/pics-for-project/${picUrl}.${picExt}"> 
	</div>	
	<div class="commentSection">
<h1>Create Comment</h1>

<c:url var="save_comment_url"  value="/saveComment/${id}" />
<form action="${save_comment_url}" method="post">
       <textarea id="content" name="content" rows="5" cols="30"></textarea>
			 <br/>
			 <br/>
    <input class="button" type="submit" value="Publish Comment" />
</form>

	<div class="listOfComments">
	<h2>Comments</h2>
	 <c:forEach items="${comments}" var="comment">
	 <div class="comment">
	     <p><%-- <c:out value="${comment.id}"/> --%><c:out value="${comment.content}"/></p>
	     </div>
    </c:forEach>
	 </div>
	 </div>

<%@ include file="../components/footer.jsp" %>
</body>


</html>