$(document).ready(function(){
	$("#id_err").hide();
	$("#age_err1").hide();
	$("#age_err2").hide();
	$("#pwd_err1").hide();
	$("#pwd_err2").hide();
	
	$("#btnSend").click(function(){
		// 입력 자료 오류 검사 후 자료를 서버로 전송
		
		// id 검사
		let id = $("#userid").val(); // document.querySelector("#userid").value
		//alert(id);
		if(id.length < 2 || isNaN(id) === false){
			$("#id_err").show();
			return false;
		}else{
			$("#id_err").hide();
		}
		
		// age 검사 1
		let age = $("#age").val();

		if(age.length < 1){
			$("#age_err1").show();
			return false;
		}else{
			$("#age_err1").hide();
		}
		
		// age 검사 2 : 양의 정수만 허용
/*		
		for(let i=0; i < age.length; i++){
			// 1글자씩 출력 후 Ascii 코드 값 얻기
			let data = age.charAt(i).charCodeAt(0);
			//alert(data);
			if (data < 48 || data > 57) {
				$("#age_err2").show();
				return false;
			}else{
				$("#age_err2").hide();
			}
		}
*/
		// 사용자 정의 함수 호출
		if(!isPositiveIntegerMyFunc(age) || age < 15 || age > 99){
			$("#age_err2").show();
			return false;
		}else{
			$("#age_err2").hide();
		}
		// 또는 정규표현식 등의 다양한 방법이 있다
		
		
		// 비밀번호 검사 : 두 개의 비밀번호가 일치해야 한다
		let pwd1 = $("#pwd1").val();
		if(pwd1.length < 1){
			//$("#pwd_err1").show();
			$("#pwd1").next().show(); // DOM 관련 메소드 - next() : next sibling을 의미. prev()
			return false;
		}else{
			$("#pwd_err1").hide();
		}
		
		let pwd2 = $("#pwd2").val();
		if(pwd1 !== pwd2){
			$("#pwd_err2").show();
			return false;
		}else{
			$("#pwd_err2").hide();
		}
		
		// 오류 검사 통과 후 자료를 서버로 전송
		$("#signFrm").attr("action", "js39.jsp").attr("method","get").submit();
	});
});

// 사용자 정의 함수 작성
function isPositiveIntegerMyFunc(para){
	let num = Number(para);
	return Number.isInteger(num) && num > 0;
}

