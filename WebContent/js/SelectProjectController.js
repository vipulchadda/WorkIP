(function(){
	var workIp = angular.module("workInProgress");
	
	var SelectProjectController = function($scope, $http, $location, $rootScope) {

		$rootScope.showChangeProject = false;
		$rootScope.projectName = "";
		
		var onProjectComplete = function(response) {
			$scope.projects = response.data;
			if($scope.projects && $scope.projects.length) {
				$scope.projectId = $scope.projects[0].name;
			}
		}
		
		$scope.openProject = function() {
			
			$location.path("/board/" + $scope.projectId);
		}

		$http.get("/service/getprojects.html")
			.then(onProjectComplete);
	};
	
	workIp.controller("SelectProjectController", SelectProjectController);
	
	
}());