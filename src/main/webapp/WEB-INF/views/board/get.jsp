<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Insert title here</title>
</head>
<body>

<u:navbar></u:navbar>

<%--
<h1>pageNum : ${cri.pageNum }</h1>
<h1>amount : ${cri.amount }</h1>
 --%>

<div class="container-sm">

  <div class="row">
   <div class="col-12 col-sm-6 offset-sm-3">
      <h1> 게시물 보기</h1>
    </div>
  </div>  
  <div class="row">
  <div class="col-12 col-sm-6 offset-sm-3">

	  <div class="form-group">
	    	<label for="input3">번호</label>
	        <input readonly value='<c:out value="${board.bno }"/>' type="text" class="form-control" id="input3">
	  </div>
	  
      <div class="form-group">
        <label for="input1">제목</label>
        <input readonly value='<c:out value="${board.title }"/>' type="text" class="form-control" id="input1">
      </div>

      <div class="form-group">
        <label for="textarea1">본문</label>
        <textarea readonly name="content" class="form-control" id="textarea1" rows="3"><c:out value="${board.content }"/></textarea>
      </div>

      <div class="form-group">
        <label for="input2">작성자</label>
        <input readonly type="text" value='<c:out value="${board.writer }"/>' class="form-control" id="input2" placeholder="이름을 입력하세요">
      </div>
	<c:url value="/board/modify" var="modifyLink">
		<c:param name="bno" value="${board.bno }"/>
		<c:param name="pageNum" value="${cri.pageNum }"/>
		<c:param name="amount" value="${cri.amount }"/>
	</c:url>
		<a href="${modifyLink }" class="btn btn-light">
			수정
		</a>
  </div>
  </div>

</div>
</body>
</html>