angular.module('rsa',['ngRoute'])
.config(function($routeProvider){
	
	$routeProvider.when('/chapters', {
		templateUrl: 'views/chapters.html',
		controller: 'chapterController as chCtl',
	});
	
	$routeProvider.when('/themes', {
		templateUrl: 'views/themes.html',
		controller: 'themeController as thCtl',
	});
	
	$routeProvider.when('/rules', {
		templateUrl: 'views/rules.html',
		controller: 'ruleController as rlCtl',
	});
	
	$routeProvider.when('/imps', {
		templateUrl: 'views/implementations.html',
		controller: 'impController as impCtl',
	});
	
//	$routeProvider.otherwise('/');
})
.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');
}]);
