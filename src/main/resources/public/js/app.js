angular.module('rsa',['ngRoute', 'ngCookies', 'ngMaterial'])

.factory('responseObserver',
		  ['$q', '$rootScope','$injector', function responseObserver($q, $rootScope, injector) {
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
		            }
		            if (response && response.status === 401) {
		            	var SessionStateService = injector.get('SessionStateService')
		            	SessionStateService.notAuthenticated401PopUp()
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
		activeTab: 'chapters',
		data: {
			authorized: ["ROLE_USER", "ROLE_ADMIN"]
		}

	});
	
	$routeProvider.when('/themes', {
		templateUrl: 'templates/themes.html',
		controller: 'themeController as thCtl',
		activeTab: 'themes',
		data: {
			authorized: ["ROLE_ADMIN"]
		}
	});
	
	$routeProvider.when('/rules', {
		templateUrl: 'templates/rules.html',
		controller: 'ruleController as rlCtl',
		activeTab: 'rules',
		data: {
			authorized: ["ROLE_USER", "ROLE_ADMIN"]
		}
	});
	
	$routeProvider.when('/imps', {
		templateUrl: 'templates/implementations.html',
		controller: 'impController as impCtl',
		activeTab: 'imps',
		data: {
			authorized: ["ROLE_USER", "ROLE_ADMIN"]
		}
	});
	
	$routeProvider.when('/login', {
		templateUrl: 'templates/login.html',
		controller: 'loginController as logCtl',
		activeTab: 'login',
		data: {
			authorized: []
		}
	});
	
	$routeProvider.when('/home', {
		templateUrl: 'templates/home.html',
		controller: 'homeController as homCtl',
		activeTab: 'home',
		data: {
			authorized: []
		}
	});
	
	$routeProvider.when('/admin', {
		templateUrl: "templates/admin.html",
		controller: "AdminController as admCtl",
		activeTab: 'admin',
			data: {
			authorized: ["ROLE_ADMIN"]
		}
	})
	
	$routeProvider.when('/error', {
		templateUrl: "templates/error.html",
//		controller: " as admCtl",
			data: {
			authorized: []
		}
	})
	
	$routeProvider.otherwise('/home');
})
.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');
}])
.run(['$rootScope', 'UserService', '$location', '$http', '$route', function(rootScope, UserService, location, http, route){
	rootScope.isAuthenticated = function(){
			return UserService.isConnected();
		}
	
	
	rootScope.logout = function(){
		UserService.logout();
	}
	
	rootScope.$on('$routeChangeStart', function(event, next, current){
		if(next.$$route.data.authorized.length){
			var path = next.originalPath
			if(!UserService.hasAuthorization(path)){
				//event.preventDefault()
				location.path('/error')
			}
		}
	})
	
	
	rootScope.hasAuthorization = function(path){
		return UserService.hasAuthorization(path)
		}
	
	 
	rootScope.route = route;
	
	
}])
