var controllersModule = angular.module('myApp.controllers', []);

controllersModule.controller('NavCtrl',['$scope', '$http', function($scope, $http){

	$scope.clickMe = function(){

		$http.get("/api/test").success(function(res){
			console.log(res);
		});
	}

}]);