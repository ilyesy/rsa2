angular.module('rsa',['ngRoute', 'ngCookies'])
.factory('responseObserver',
		  ['$q', function responseObserver($q) {
			
			return {
		        request: function (config) {
		        	console.log(config)
		            return config || $q.when(config);
		        },
		        requestError: function(request){
		        	console.log(request)
		            return $q.reject(request);
		        },
		        response: function (response) {
		        	console.log("response ", response)
		            return response || $q.when(response);
		        },
		        responseError: function (response) {
		            if (response && response.status === 412) {
		            	var message = {type: 'error', 'msg':'Problem in processing your request.'};
		            	$rootScope.$emit('NotificationEvent', message);
		            	$rootScope.logout();
		            }
		            if (response && response.status === 401) {
		            	var message = {type: 'error', 'msg':'Invalid Login Credentials or Session Expired.'};
		            	$rootScope.$emit('NotificationEvent', message);
		            	$rootScope.logout();
		            }
		            return $q.reject(response);
		        }
		    };
		    
		}])
.config(function($routeProvider, $httpProvider){
	$httpProvider.interceptors.push('responseObserver');
	$routeProvider.when('/chapters', {
		templateUrl: 'templates/chapters.html',
		controller: 'chapterController as chCtl',
	});
	
	$routeProvider.when('/themes', {
		templateUrl: 'templates/themes.html',
		controller: 'themeController as thCtl',
	});
	
	$routeProvider.when('/rules', {
		templateUrl: 'templates/rules.html',
		controller: 'ruleController as rlCtl',
	});
	
	$routeProvider.when('/imps', {
		templateUrl: 'templates/implementations.html',
		controller: 'impController as impCtl',
	});
	
	$routeProvider.when('/login', {
		templateUrl: 'templates/login.html',
		controller: 'loginController as logCtl',
	});
	
//	$routeProvider.otherwise('/');
})
.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');
}])
.run(['$rootScope', 'UserService', function(rootScope, UserService){
	rootScope.isConnected = function(){
		return UserService.isConnected();
	}
}])
