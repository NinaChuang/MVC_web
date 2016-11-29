$(document).ready(function() {
	setCityOption();
	doOnchageCity();
});

// 查詢下拉式選項
function setCityOption() {
	var json = {
		'getCity' : 'getCity'
	};
	$.ajax({
		url : "controllerServletImpl", //to controller path
		type : "POST",                 //doPost
		cache : false,
		data : JSON.stringify(json),   //send data source
		async : false,
		// dataType : "'text json'",
		contentType : "application/json; charset=utf-8", //data type
		success : function(response) {
			var json = JSON.parse(response);
			doSetAttributeOption(json);      //if method success, do this method.
		},
		error : function(xhr) {
			alert(xhr.status);
		},
	});
}
// 將資料塞進下拉式選項中
function doSetAttributeOption(json) {
	var Attribute = document.getElementById("addressCity");
	var AttributeCount = Attribute.length;

	for (var i = 1; i < AttributeCount; i++) {
		Attribute.remove(1);
	}

	for (var i = 0; i < json.length; i++) {
		var option = document.createElement("option"); //建立option元素
		option.value = json[i]["provinceNo"] + ',' + json[i]["cityNo"];  //建立option value
		option.text = json[i]["cityName"];  //建立option name
		Attribute.add(option); //塞入下拉選單
	}  
}
function doOnchageCity() {

	$("#addressCity").on("change", function(event) {

		var msg = $('#addressCity').val();
		var json = {
			'getTown' : msg
		};
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
			},
			error : function(xhr) {
				alert(xhr.status);
			},
		});

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