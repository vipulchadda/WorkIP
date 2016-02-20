$(function() {
	var getDataObject = function(serializedArray){
		var object = {};
		for ( var item in serializedArray) {
			if (serializedArray.hasOwnProperty(item)) {
				object[serializedArray[item].name] = serializedArray[item].value;
			}
		}
		return object;
	};
	$("#open-project").click(function(){
		var project = $("#project").val();
		if (project) {
			window.location.href = "/wall/board.jsp?project=" + project;
		}
	});
	$(".new-project-form").submit(function(e){
		e.preventDefault();
		var data = getDataObject($(".new-project-form").serializeArray());
		$.ajax({
		  type: "POST",
		  url: "/service/project.html/add",
		  data: data,
		  success: function(){
			  window.location.reload(true);
		  }
		});
	});
});