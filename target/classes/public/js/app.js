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
		data: {
			authorized: []
		},
//		resolve: {
//			checkSession: ['SessionStateService', function(SessionStateService){
//				console.log('lkjflskjfdlskjdflk')
//				 SessionStateService.getSessionState();
//			}]
//		}
	});
	
	$routeProvider.when('/themes', {
		templateUrl: 'templates/themes.html',
		controller: 'themeController as thCtl',
		data: {
			authorized: []
		}
	});
	
	$routeProvider.when('/rules', {
		templateUrl: 'templates/rules.html',
		controller: 'ruleController as rlCtl',
		data: {
			authorized: []
		}
	});
	
	$routeProvider.when('/imps', {
		templateUrl: 'templates/implementations.html',
		controller: 'impController as impCtl',
		data: {
			authorized: []
		}
	});
	
	$routeProvider.when('/login', {
		templateUrl: 'templates/login.html',
		controller: 'loginController as logCtl',
		data: {
			authorized: []
		}
	});
	
	$routeProvider.when('/home', {
		templateUrl: 'templates/home.html',
		controller: 'homeController as homCtl',
		data: {
			authorized: []
		}
	});
	
	$routeProvider.when('/admin', {
		templateUrl: "templates/admin.html",
		controller: "AdminController as admCtl",
			data: {
			authorized: ["ROLE_ADMIN"]
		}
	})
	
	$routeProvider.otherwise('/home');
})
.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');
}])
.run(['$rootScope', 'UserService', '$location', function(rootScope, UserService, location){
	rootScope.isAuthenticated = function(){
			return UserService.isConnected();
		}
	
	
	rootScope.logout = function(){
		UserService.logout();
	}
	
	
	rootScope.$on('$routeChangeStart', function(event, next, current){
		console.log(next.$$route.data.authorized.length)
		if(next.$$route.data.authorized.length){
			var path = next.originalPath
			console.log(path)
			console.log(UserService.hasAuthorization(path))
			if(!UserService.hasAuthorization(path)){
				event.preventDefault()
				location.path('/login')
			}
		}
	})
	
	
	rootScope.hasAuthorization = function(path){
		
		return UserService.hasAuthorization(path)
//		var request = new XMLHttpRequest();
//		request.open('GET', '/principal', false);  // `false` makes the request synchronous
//		request.send(null);
//
//		return request.responseText
		}
	
}])
