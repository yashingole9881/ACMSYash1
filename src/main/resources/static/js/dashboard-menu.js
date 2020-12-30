$(document).ready(function() {
	var path = window.location.href;
	var arr = path.split("/");
	
	if (arr[arr.length - 1] == "dashboard") {
		$("#dash").addClass("active");
	}
	if (arr[arr.length - 1] == "agencyform") {
		$("#agency1,#agency11").addClass("active");
		$("#collapseTwo").addClass("show");
	}
	if (arr[arr.length - 1] == "tranaddform") {
		$("#trans1,#trans11").addClass("active");
		$("#collapsePages").addClass("show");
	}
	if(arr[arr.length - 1] == "agencylist"){
		$("#agencylist").addClass("active");
	}	
	if(arr[arr.length - 1] == "policylist"){
		$("#policylist").addClass("active");
	}
});