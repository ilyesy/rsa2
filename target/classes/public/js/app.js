angular.module('rsa',['ngRoute', 'ngCookies'])
.factory('responseObserver',
		  ['$q', '$rootScope', function responseObserver($q, $rootScope) {
			
			return {
		        request: function (config) {
		            return config || $q.when(config);
		        },
		        requestError: function(request){
		            return $q.reject(request);
		        },
		        response: function (response) {
		            return response || $q.when(response);
		        },
		        responseError: function (response) {
		            if (response && response.status === 412) {
//		            	var message = {type: 'error', 'msg':'Problem in processing your request.'};
//		            	$rootScope.$emit('NotificationEvent', message);
//		            	$rootScope.logout();
		            }
		            if (response && response.status === 401) {
//		            	console.log("session expired")
//		            	var message = {type: 'error', 'msg':'Invalid Login Credentials or Session Expired.'};
//		            	$rootScope.$emit('NotificationEvent', message);
//		            	console.log($rootScope)
//		            	$rootScope.logout();
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
		data: {
			public: false
		}
	});
	
	$routeProvider.when('/themes', {
		templateUrl: 'templates/themes.html',
		controller: 'themeController as thCtl',
		data: {
			public: false
		}
	});
	
	$routeProvider.when('/rules', {
		templateUrl: 'templates/rules.html',
		controller: 'ruleController as rlCtl',
		data: {
			public: false
		}
	});
	
	$routeProvider.when('/imps', {
		templateUrl: 'templates/implementations.html',
		controller: 'impController as impCtl',
		data: {
			public: false
		}
	});
	
	$routeProvider.when('/login', {
		templateUrl: 'templates/login.html',
		controller: 'loginController as logCtl',
		data: {
			public: true
		}
	});
	
	$routeProvider.when('/home', {
		templateUrl: 'templates/home.html',
		controller: 'homeController as homCtl',
		data: {
			public: true
		}
	});
	
	$routeProvider.otherwise('/home');
})
.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');
}])
.run(['$rootScope', 'UserService', '$location', function(rootScope, UserService, location){
	rootScope.isAuthenticated = function(){
			return UserService.isConnected();
		}
	
	rootScope.$on('$routeChangeStart', function(event, next, current){
		if(next.data){
		var access = next.data.public
		if(!access){
			if(!UserService.isConnected()){
				event.preventDefault()
				location.path('/login')
				}
			}
		}
	})	
	rootScope.logout = function(){
		UserService.logout()
	}
}])
