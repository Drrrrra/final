<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<h2>비밀번호 변경</h2>

<p class="lead">비밀번호 변경 페이지 입니다.</p>

<div class="col-md-8 order-md-1">
	<div class="mb-3">
		<label for="email">기존의 비밀번호 입력 </label>
		<input type="password" class="form-control" id="pass" placeholder="기존의 비밀번호 입력">
		<div class="invalid-feedback">기존에 사용했던 비밀 번호를 정확하게 입력해주세요</div>
	</div>

	<div class="mb-3">
		<label for="address">새로운 비밀번호 입력</label> 
		<input type="password" class="form-control" id="newPass" placeholder="새로 설정할 비밀번호 입력">
	</div>

	<div class="mb-3">
		<label for="address2">새로운 비밀번호 확인</label>
		<input type="password" class="form-control" id="checkPass" placeholder="새로 설정할 비밀번호 확인">
		<div class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
	</div>
</div>
