(function(){
	var workIp = angular.module("workInProgress");
	
	var BoardController = function($scope, $http, $routeParams, $rootScope) {

		$rootScope.showChangeProject = true;
		
		
		var onProjectComplete = function(response) {
			$scope.project = response.data;
		}
		
		var getRandomDegree = function() {
			return Math.round((Math.random() * 3 - 1.5) * 10) / 10;
		}
		
		$scope.getNoteStyle = function() {
			return {'transform': 'rotate(' + getRandomDegree() + 'deg)'};
		}
		
		var projectName = $routeParams.projectName;
		$rootScope.projectName = projectName;
		
		$http.get("/service/project.html/get", {
				params: {"name": projectName}
			}).then(onProjectComplete);
	};
	
	workIp.controller("BoardController", BoardController);
	
	
}());