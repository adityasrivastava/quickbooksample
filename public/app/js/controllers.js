var controllersModule = angular.module('myApp', []);

console.log("CHeck");

controllersModule.controller('NavCtrl',['$scope', '$http', function($scope, $http){

	console.log("Hello");


	$scope.clickMe = function(){

		$http.get("/api/test").success(function(res){
			console.log(res);
		});
	}

}]);