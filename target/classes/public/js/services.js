angular.module('rsa')
.factory('ChaptersService', ['$http', function($http){
		
		return {
			getChapters: function(){
				return $http.get('/chapters');
			}
		}
	}
])

.factory('ImpsService', ['$http', function($http){
		
		return {
			getImps: function(){
				return $http.get('/imps');
			}
		}
	}
])

.factory('RulesService', ['$http', function($http){
		
		return {
			getRules: function(){
				return $http.get('/rules');
			}
		}
	}
])

.factory('ThemesService', ['$http', function($http){
		
		return {
			getThemes: function(){
				return $http.get('/themes');
			}
		}
	}
])
.factory('MainService', ['$http', function($http){
	return {
		
	}
	
}])
.service('UserService', ['$http', '$cookies','$rootScope','$location', '$cookieStore', function(http, cookies, rootScope, location, cookieStore){
	
	var self = this;
	
	self.setUser = function(data){
		cookies.put('authenticatedUser', 'sth');
	}
	
	self.removeUser = function(){
		cookies.remove("authenticatedUser")
	}
	
	self.isConnected = function(){
		return cookies.get("authenticatedUser") != null;
	}
	
	self.transformToFormData = function(user){
		var requestStr;
		 if (user) {
			 //self.user = JSON.parse(self.user);
			 for (var key in user) {
				 if (requestStr) {
					 requestStr += '&' + key + '=' + user[key];
				 } else {
					 requestStr = key + '=' + user[key];
				 }
			 	}
			 }
		 return requestStr;
	}
	
	self.login = function(requestStr){
		http({
			    method: 'POST',
			    url: '/login',
			    data: requestStr,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			})
		.then(function(resp){
			self.setUser(resp.data);
			location.path("/")
		}, function(resp){
			console.log('sorry');
		})
	}
	
	self.logout = function(){
		{	
			 self.removeUser('authenticatedUser');
			 http.post('/logout').then(function(response){
	    		console.log('Successfully logged out on server');
	  	    },function(resp){
	  	    	console.log('log out fail')
	  	    });
		}
	}
}])
.service('SessionStateService', 
				[ '$http', '$mdDialog', '$location', 'UserService', function(http, mdDialog, location, UserService) {
					console.log('just doing my job')
					var self = this;
					this.getSessionState = function() {
						http.get('/session').then(function(resp) {}, 
					    function() {
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
						)
					}
}])