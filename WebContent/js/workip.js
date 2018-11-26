(function(){
	var workIp = angular.module("workInProgress", ["ngRoute"]);
	
	workIp.config(function($routeProvider){
		$routeProvider
			.when("/selectProject",{
				templateUrl:"selectProject.html",
				controller:"SelectProjectController"
			})
			.when("/board/:projectName", {
				templateUrl: "board.html",
				controller: "BoardController"
			})
			.otherwise({redirectTo:"/selectProject"});
	});
	
	
}());