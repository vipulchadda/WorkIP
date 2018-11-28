(function(){
	var workIp = angular.module("workInProgress");
	workIp.directive('clickLink', ['$location', function($location) {
    return {
        link: function(scope, element, attrs) {
            element.on('click', function() {
                scope.$apply(function() {
                	console.log(attrs.clickLink);
                    $location.path(attrs.clickLink);
                });
            });
        }
    }
	}]);
}());