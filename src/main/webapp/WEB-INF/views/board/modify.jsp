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
<script>
$(document).ready(function() {
	$("#remove-btn").click(function(e) {
		//바로 submit 되어버리는  것
		e.preventDefault();
		
		//modify-form의 action attr의 값을 remove로 바꾸어야 함
		$("#modify-form").attr("action", "/board/remove");
		
		//그런 담에 submit!
		$("#modify-form").submit();
	});
});
</script>
<title>Insert title here</title>
</head>
<body>

<u:navbar></u:navbar>
<div class="container-sm">

  <div class="row">
   <div class="col-12 col-lg-6 offset-sm-3">
      <h1> 게시물 수정</h1>
    </div>
  </div>  
  <div class="row">
  <div class="col-12 col-lg-6 offset-sm-3">

    <form id="modify-form" method="POST" action="${root }/board/modify">
    
    
	  <div class="form-group">
	    	<label for="input3">번호</label>
	        <input name="bno" readonly value='<c:out value="${board.bno }"/>' type="text" class="form-control" id="input3">
	  </div>
	  
      <div class="form-group">
        <label for="input1">제목</label>
        <input value='<c:out value="${board.title }"/>' name="title" type="text" class="form-control" id="input1" placeholder="제목을 입력하세요.">
      </div>

      <div class="form-group">
        <label for="textarea1">본문</label>
        <textarea name="content" class="form-control" id="textarea1" rows="3"><c:out value="${board.content }"/></textarea>
      </div>

      <div class="form-group">
        <label for="input2">작성자</label>
        <input value='<c:out value="${board.writer }"/>' name="writer" type="text" class="form-control" id="input2" placeholder="이름을 입력하세요">
      </div>
      
      <input type="hidden" value="${cri.pageNum }" name="pageNum"/>
      <input type="hidden" value="${cri.amount }" name="amount"/>
      <input type="hidden" value="${cri.type }" name="type"/>
      <input type="hidden" value="${cri.keyword }" name="keyword"/>
      
      <button type="submit" class="btn btn-primary">수정완료</button>
      <button id="remove-btn" type="submit" class="btn btn-danger">삭제</button>


    </form>

  </div>
  </div>

</div>
</body>
</html>