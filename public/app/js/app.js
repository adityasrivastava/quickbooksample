var app = angular.module('myApp', [
		'myApp.controllers',
		'myApp.services'
	])


app.run(['InitializeSvc', function(InitializeSvc){
	InitializeSvc.initialize();
}]);

