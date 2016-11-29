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
// 將資料塞進Attribute下拉式選項中
function doSetAttributeOption(json) {
	var Attribute = document.getElementById("addressCity");
	var AttributeCount = Attribute.length;

	for (var i = 1; i < AttributeCount; i++) {
		Attribute.remove(1);
	}

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

	for (var i = 0; i < AttributeCount; i++) {
		Attribute.remove(0);
	}
	 
	
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

		alert(msg);
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