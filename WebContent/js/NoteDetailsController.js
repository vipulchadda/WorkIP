(function(){
	var workIp = angular.module("workInProgress");
	
	var NoteDetailsController = function($scope, $http, $routeParams, $rootScope) {

		$rootScope.showChangeProject = true;
		
		var onNoteComplete = function(response) {
			$scope.note = response.data;
			$rootScope.projectName = $scope.note.project.name;
		}
		
		var noteId = $routeParams.noteId;
		
		$http.get("/service/note.html/get", {
				params: {"id": noteId}
			}).then(onNoteComplete);
	};
	
	workIp.controller("NoteDetailsController", NoteDetailsController);
	
	
}());