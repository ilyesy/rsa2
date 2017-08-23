angular.module('rsa',['ngRoute', 'ngCookies', 'ngMaterial'])
.factory('responseObserver',
		  ['$q', '$rootScope','$injector', '$injector', '$location', function responseObserver($q, $rootScope, injector, $injector, location) {

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
		            	console.log('response error')
		            }
		            if (response && response.status === 401) {
//		            	var SessionStateService = $injector.get('SessionStateService')
//		            	console.log('Invalid Login Credentials or Session Expired')
//		            	SessionStateService.getSessionState();
		            	var mdDialog = $injector.get('$mdDialog')
		            	var UserService = $injector.get('UserService')
						console.log('trying my best')
						console.log('session died')
						var confirm = mdDialog.confirm()
				          .title('Would you like to login ?')
				          .textContent('Your session has expired')
				          .ariaLabel('session expired')
				          .ok('Login')
				          .cancel('Home page');

				    mdDialog.show(confirm).then(function() {
				    	UserService.removeUser('authenticatedUser');
				    	location.path('/login')
				    }, function() {
				    	UserService.removeUser('authenticatedUser');
				    	location.path('/home');
				    	});
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
.run(['$rootScope', 'UserService', '$location', '$cookies', function(rootScope, UserService, location, cookies){
	rootScope.isAuthenticated = function(){
			return UserService.isConnected();
		}
	
	rootScope.$on('$routeChangeStart', function(event, next, current){
//		if(cookies.get("SESSION") != undefined && location.path() != "/login"){
//			location.path('/login')
//		}
		if(next.data){
			var access = next.data.public
			if(!access){
				if(!UserService.isConnected()){
					event.preventDefault();
					location.path('/login');
					}
				}
		}
	})	
	rootScope.logout = function(){
		UserService.logout();
	}
}])
