<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="en">

<head>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/everythingelse.css" rel="stylesheet">
 <link href="${pageContext.request.contextPath}/css/listofpics.css" rel="stylesheet">
 <link href="https://fonts.googleapis.com/css?family=Noto+Serif+KR" rel="stylesheet">
</head>
<body style="background-color: #c2e9fb;">
  <%@ include file="components/header.jsp" %>

   <div class="everything">
     <!-- <form action="searchBlogs" method="GET">
    	<input type="text" name="searchedWords">
    	<Br/>
    	<Br/>
  		<input class="button" type="submit" value="Search" />
    </form> -->
    <Br/>
    <form action="/food/upload" method="post" enctype="multipart/form-data">
	<input type="text" name="picName" placeholder="Picture Name">
	<input type="text" name="picDescription" placeholder="Description">
	<input type="text" name="picBrand" placeholder="Brand">
   <input type="file" name="preuploadPicFile" multiple>
   <input type="submit" value="Upload Files"></input>
</form>

    <Br/>
    </div>

<div class="list_of_pics">

<c:forEach items="${pics}" var="pic">

<div class="picture">
   <a href="/picture/${pic.id}">
    <img sRC="https://s3.amazonaws.com/pics-for-project/${pic.pic_url}.${pic.extension}"> 
    </a>
</div>
</c:forEach>

</div>
<%@ include file="components/footer.jsp" %>
</body>


</html>