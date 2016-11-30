$(document).ready(function() {
//	upLoadFile();
	setCityOption();
	doOnchageCity();
	doSubmitOption();
	previewImg();
});

// 查詢下拉式選項
function setCityOption() {
	var json = {
		'getCity' : 'getCity'
	};
	$.ajax({
		url : "controllerServletImpl", //to controller path
		type : "POST", //doPost
		cache : false,
		data : JSON.stringify(json), //send data source
		async : false,
		// dataType : "'text json'",
		contentType : "application/json; charset=utf-8", //data type
		success : function(response) {
			var json = JSON.parse(response);
			doSetAttributeOption(json); //if method success, do this method.
		},
		error : function(xhr) {
			alert(xhr.status);
		},
	});
}

function doSubmitOption() {
	
	$('#submitBtn').on('click', function() {
//		alert($("#exampleInputFile").val().split('\\').pop());
//		alert("setSubmitOption coming")
		var name = $("#name").val();
		var age = $("#age").val();
		var addressCity = $("#addressCity").val();
		var addressTown = $("#addressTown").val();
		var email = $("#email").val();
		alert(email);
		var phone = $("#phone").val();
		var imgName = $("#exampleInputFile").val().split('\\').pop()
		var json = {
				'submit' : 'submit',
				'name' : name,
				'age' : age,
				'addressCity' : addressCity,
				'addressTown' : addressTown,
				'email' : email,
				'phone' : phone,
				'imgName' : imgName
		};
		$.ajax({
			url : "controllerServletImpl", //to controller path
			type : "POST", //doPost
			cache : false,
			data : JSON.stringify(json), //send data source
			async : false,
			// dataType : "'text json'",
			contentType : "application/json; charset=utf-8", //data type
			success : function(response) {
				var json = JSON.parse(response);
				handleSubmit(json); //if method success, do this method.
			},
			error : function(xhr) {
				alert(xhr.status);
			},
		});
	});
}

function handleSubmit(json) {
	
	//顯示驗證錯誤訊息
	var errmail = $("#errorEmail");
	var errphone = $("#errorPhone");
	errmail.html(json['email']);
	errphone.html(json['phone']);
	
	//顯示使用者已存在
	if(json['userExist'] != undefined) {
		alert(json['userExist']);
	}
	if(json['saveSuccess'] != undefined) {
		alert(json['saveSuccess']);
	}
}


// 將資料塞進Attribute下拉式選項中
function doSetAttributeOption(json) {
	var Attribute = document.getElementById("addressCity");
	var AttributeCount = Attribute.length;

	for (var i = 1; i < AttributeCount; i++) {
		Attribute.remove(1);
	}
	
	var option = document.createElement("option");
	option.value = "請選擇";
	option.text = "請選擇";
	Attribute.add(option);
	
	for (var i = 0; i < json.length; i++) {
		var option = document.createElement("option");
		option.value = json[i]["provinceNo"] + ',' + json[i]["cityNo"];
		option.text = json[i]["cityName"];

		Attribute.add(option);
	}

}
function setTownOption(json) {
	var Attribute = document.getElementById("addressTown");
	var AttributeCount = Attribute.length;
	
//	if(AttributeCount > 0) {
//		$('#addressTown option').remove();
//	}

//	for (var i = 1; i < AttributeCount; i++) {
//		Attribute.remove(0);
//	}
	
//	for (var i = AttributeCount -1 ; i >= 0 ; i--) {
//		Attribute.remove(1);
//	}

	for (var i = 0; i < AttributeCount; i++) {
		Attribute.remove(0);
	}

//	var option = document.createElement("option");
//	option.value = "請選擇";
//	option.text = "請選擇";
//	Attribute.add(option);
	
	for (var i = 0; i < json.length; i++) {
		var option = document.createElement("option");
		option.value = json[i]["provinceNo"] + ',' + json[i]["cityNo"] + ','
				+ json[i]["areaNo"];
		option.text = json[i]["areaName"];

		Attribute.add(option);
	}
}
function doOnchageCity() {

	$("#addressCity").on("change", function(event) {

		var msg = $('#addressCity').val();
		var json = {
			'getTown' : msg
		};

//		alert(msg);
		$.ajax({
			url : "controllerServletImpl",
			type : "POST",
			cache : false,
			data : JSON.stringify(json),
			async : false,
			// dataType : "'text json'",
			contentType : "application/json; charset=utf-8",
			success : function(response) {
				var json = JSON.parse(response);
				setTownOption(json);
			},
			error : function(xhr) {
				alert(xhr.status);
			},

		});

	});
}
function upLoadFile() {
	$('#submitBtn').on('click', function() {
		//		$.getScript("js/ajaxfileupload.js", function() {
		alert('upLoadFile coming');
		$.ajaxFileUpload({
			url : 'contorllerFielUpload',
			secureuri : false,
			fileElementId : 'exampleInputFile',
			dataType : 'json',
			success : function(data, status) {
				if (typeof (data.error) != 'undefined') {
					if (data.error != '') {
						alert(data.error);
					} else {
						alert(data.msg);
					}
				}
			},
			error : function(data, status, e) {
				alert(e);
			}
		})
	});
}

function previewImg() {
	$("#exampleInputFile").on('change', function() {
		  //Hide default preview
		  $("#defaultImg").hide();
	      //Get count of selected files
	      var countFiles = $(this)[0].files.length;
	      var imgPath = $(this)[0].value;
	      var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
	      var image_holder = $("#image-holder");
	      image_holder.empty();
	      if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
	        if (typeof(FileReader) != "undefined") {
	          //loop for each file selected for uploaded.
	          for (var i = 0; i < countFiles; i++) 
	          {
	            var reader = new FileReader();
	            reader.onload = function(e) {
	              $("<img />", {
	                "src": e.target.result,
	                "class": "thumb-image"
	              }).appendTo(image_holder);
	            }
	            image_holder.show();
	            reader.readAsDataURL($(this)[0].files[i]);
	          }
	        } else {
	          alert("This browser does not support FileReader.");
	        }
	      } else {
	        alert("Pls select only images");
	      }
	    });
}
// $(document).on("click", "#submit", function(event) {

// var msg = $('#msg').val();
// /* stop form from submitting normally */
// event.preventDefault();
// $.get("controllerServletImpl", {
// 'getCity' : 'getCity'
// }, function(responseText) {
// var json = JSON.parse(responseText);
// doSetAttributeOption(json);
//		});
//	});
