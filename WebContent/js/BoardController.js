(function(){
	var workIp = angular.module("workInProgress");
	
	var BoardController = function($scope, $routeParams) {

		$scope.projectName = $routeParams.projectName;
	};
	
	workIp.controller("BoardController", BoardController);
	
	
}());