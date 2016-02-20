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
	$(".edit-note-form").submit(function(e){
		e.preventDefault();
		var data = getDataObject($(".edit-note-form").serializeArray());
		$.ajax({
		  type: "POST",
		  url: "/service/note.html/update",
		  data: data,
		  success: function(){
			  window.location.reload(true);
		  }
		});
	});
	$(".add-comment-form").submit(function(e){
		e.preventDefault();
		var data = getDataObject($(".add-comment-form").serializeArray());
		$.ajax({
			  type: "POST",
			  url: "/service/note.html/update",
			  data: data,
			  success: function(){
				  window.location.reload(true);
			  }
			});
	});
});