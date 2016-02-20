var getDataObject = function(serializedArray){
	var object = {};
	for ( var item in serializedArray) {
		if (serializedArray.hasOwnProperty(item)) {
			object[serializedArray[item].name] = serializedArray[item].value;
		}
	}
	return object;
};
$(function() {
	var min_height = null;
	$(".notes-list").each(function(){
		if(!min_height || $(this).height() > min_height) {
			min_height = $(this).height();
		} 
	});
	$(".notes-list").css("min-height", min_height);
    $( ".to-do-notes, .wip-notes, .done-notes" ).sortable({
      connectWith: ".notes-list",
      cursor: "pointer",
      dropOnEmpty: true,
      receive: function(event, ui){
    	  var noteState = $(event.target).attr("data-note-state");
    	  var noteId = $(event.toElement).closest(".note").attr("data-note-id");
    	  $.ajax({
    		  type: "POST",
    		  url: "/service/note.html/update",
    		  data: {"currentState":noteState,
    			  "id":noteId},
    		  success: function(){}
    		});
      }
    }).disableSelection();
    
    
    $("#newNoteModal").on("show.bs.modal", function(event){
    	var button = $(event.relatedTarget);
    	var currentState = button.data('note-type');
    	$("#newNoteModal #currentState").val(currentState);
    })
	$(".new-note-form").submit(function(e){
		e.preventDefault();
		var data = getDataObject($(".new-note-form").serializeArray());
		$.ajax({
		  type: "POST",
		  url: "/service/note.html/add",
		  data: data,
		  success: function(){
			  window.location.reload(true);
		  }
		});
	});
    $(".note .close").click(function(){
    	var noteId = $(this).parent().attr("data-note-id");
    	$.ajax({
  		  type: "POST",
  		  url: "/service/note.html/delete",
  		  data: {"noteId": noteId},
  		  success: function(){
  			  window.location.reload(true);
  		  }
  		});
    });
    
  });