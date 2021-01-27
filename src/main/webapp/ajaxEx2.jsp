<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
$(document).ready(function(){
	$("#btn-1").click(function(){
		$.ajax({
			type: "post",
			url: "/replies/new",
			contentType: "application/json",
			data: '{"bno":375, "reply":"새댓글이다", "replyer":"김쿼카"}',
			complete: function(jqXHR, status) {
				console.log("complete");
				console.log(status);
			}
		});
	});
	
	$("#btn-2").click(function(){
		$.ajax({
			type: "post",
			url: "/replies/new",
			contentType: "application/json",
			data: '{"reply":"새댓글이다", "replyer":"김쿼카"}',
			complete: function(jqXHR, status) {
				console.log("complete");
				console.log(status);
			}
		});
	});
	
	$("#btn-3").click(function(){
		$.ajax({
			type: "post",
			url: "/replies/new",
			contentType: "application/json",
			data: '{"bno":375, "reply":"새댓글이다", "replyer":"김쿼카"}',
			complete: function(jqXHR, status) {
				if (status === "success") {
					console.log(jqXHR.responseText);
					//jqXHR.responseText는 Controller에 내가 작성한 메세지 
				} else if (status === "error") {
					console.log("등록실패")
				}
			}		
		});
	});
	
	$("#btn-4").click(function(){
		$.ajax({
			url: "/replies/pages/375/1",
			contentType: "application/json",
			type: "get",
			complete: function(jqXHR, status) {
				if (status === "success") {
					console.log(jqXHR.responseText);
				} else if (status === "error") {
					console.log("조회 오류 발생")
				}			
			}
		});
	});
	
	$("#btn-5").click(function(){
		$.ajax({
			url: "/replies/37",
			type: "delete",
			contentType: "application/json",
			complete: function(jqXHR, status) {
				if (status === "success") {
					console.log(jqXHR.responseText);
				} else if (status === "error") {
					console.log("삭제 오류 발생")
				}
			}
		});
	});
	
	$("#btn-6").click(function(){
		$.ajax({
			url: "/replies/41",
			type:"put",
			contentType: "application/json",
			data: '{"bno":375, "reply":"수정수정수수수정", "replyer":"수정입니다."}',
			complete: function(jqXHR, status) {
				if (status === "success") {
					console.log(jqXHR.responseText);
				} else if (status === "error") {
					console.log("수정 오류 발생")
				}
			}
		})
	})
	
});
</script>

<title>Insert title here</title>
</head>
<body>
<h1>AJAX ex2</h1>
<div>
<button id="btn-1">댓글 등록</button>
<button id="btn-2">댓글 등록실패</button>
<button id="btn-3">댓글 등록 또는 실패</button>
</div>
<div>

<button id="btn-4">댓글 목록</button>
<button id="btn-5">댓글 삭제</button>
<button id="btn-6">댓글 수정</button>
</div>
</body>
</html>