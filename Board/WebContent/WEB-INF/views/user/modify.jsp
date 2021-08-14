<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value='${pageContext.request.contextPath }/'/><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<script>
/* 다음 주소 연동 */
function execution_daum_address(){
 
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            
              // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수
            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
				addr += extraAddr;                
            } else {
            	addr += ' ';
            }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $(".userAdd1").val(data.zonecode);
            $(".userAdd2").val(addr);
            // 커서를 상세주소 필드로 이동한다.
            $(".userAdd3").attr("readonly",false);
            $(".userAdd3").focus();
 
        }
    }).open();    
 
}
	$(document).ready(function(){
		$(document).on("keyup", "#userNum", function() { 
		    $(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^[0-9]{3})([0-9]{4})([0-9]{4})$/,"$1-$2-$3").replace("--", "-","-") );
		    
		 });
	});
	
	function modifyUserInfo() {
		if (document.modifyInfoForm.userPw.value == "") {
			document.modifyInfoForm.userPw.focus();
		}else if (document.modifyInfoForm.userPw2.value == "" ) {
			document.modifyInfoForm.userPw2.focus();
		}else if (document.modifyInfoForm.userNum.value == "" ){
			document.modifyInfoForm.userNum.focus();
		}else if (document.modifyInfoForm.userAdd1.value == "" ){
			document.modifyInfoForm.userAdd1.focus();
		}else if (document.modifyInfoForm.userAdd2.value == "" ){
			document.modifyInfoForm.userAdd2.focus();
		}else if (document.modifyInfoForm.userAdd3.value == "" ){
			document.modifyInfoForm.userAdd3.focus();
		}else {
			document.modifyInfoForm.action = "modify_pro";
			document.modifyInfoForm.submit();
			alert("정보가 변경되었습니다.");			
		}
	}
</script>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp"/>


<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
				<form:form action="${root }user/modify_pro" method='post' modelAttribute="modifyUserBean" name="modifyInfoForm">
					<form:hidden path="userIdx"/>
					<div class="form-group">
						<form:label path="userName">이름</form:label>
						<form:input path="userName" class='form-control' readonly='true'/>
					</div>
					<div class="form-group">
						<form:label path="userId">아이디</form:label>
						<form:input path="userId" class='form-control' readonly='true' />
					</div>

					<div class="form-group">
						<form:label path="userPw">비밀번호</form:label>
						<form:password path="userPw" class='form-control' minlength="4"/>
						<form:errors path="userPw" style='color:red;'/>
					</div>

					<div class="form-group">
						<form:label path="userPw2">비밀번호 확인</form:label>
						<form:password path="userPw2" class='form-control' minlength="4"/>
						<form:errors path="userPw" style='color:red;'/>
					</div>
					
					<div class="form-group">
						<form:label path="userNum">전화번호</form:label>
						<form:input path="userNum" id="userNum"  maxlength="11" class='userNum form-control' />
						<form:errors path="userNum" style='color:red;'/>
					</div>
				
					<div class="form-group">
						<form:label path="userAdd1">주소</form:label> <br>
						<div class="input-group">
								<form:input path="userAdd1"  class="userAdd1 form-control" /><br/><br>							
								<form:errors path="userAdd1" style='color:red;'/>
							<div class="input-group-append">
								<button type="button" class= "btn btn-primary " onclick="execution_daum_address()">주소찾기</button>
							</div>
						</div>
						
						<form:input path="userAdd2"  class="userAdd2 form-control" /><br/>
						<form:errors path="userAdd2" style='color:red;'/>
						
						<form:label path="userAdd3" >상세 주소</form:label>
						<form:input path="userAdd3"  class="userAdd3 form-control" />
						<form:errors path="userAdd3" style='color:red;'/>
					</div>
						
					<div class="form-group">
						<div class="text-right">
							<form:button class='btn btn-primary' onclick="modifyUserInfo()">정보수정</form:button>
						</div>
					</div>
					
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>