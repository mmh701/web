$(document).ready(function(){
	$("#btnSearch").click(function(){
		$("#show").empty();
				
		let bname = $("#name").val();

		$.ajax({
			type:"get",
			url:"js42.jsp",
			data:{"name":bname},	
			dataType:"json",
			success:function(datas){


				let str = "<table border='1'>";
				str += "<tr><th>사번</th><th>직원명</th><th>직급</th><th>연봉</th></tr>"
				
				$.each(datas.jikwons, function(idx, data){
					str += "<tr>";
					str += "<td>" + data["jikwonno"] + "</td>";
					str += "<td>" + data["jikwonname"] + "</td>";
					
					str += "<td>" + data["jikwonjik"] + "</td>";
					str += "<td>" + data["jikwonpay"] + "</td>";
					str += "</tr>";

				});
				
				str += "</table>";
				$("#show").append(str);

				
			},
			error:function(){
				$("#show").append("직원 읽기 에러");
			}
		});
	});
});


