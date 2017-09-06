angular.module('rsa')
.factory('ChaptersService', ['$http', function($http){
		
		return {
			getChapters: function(){
				return $http.get('/chapters/slice');
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
.service('UserService', ['$http', '$cookies','$rootScope','$location', '$route', function(http, cookies, rootScope, location, route){
	
	var self = this;
	
	self.addToCookie = function(key, value){
		cookies.put(key, value);
	}
	
	self.removeUser = function(){
		cookies.remove("authenticatedUser")
		cookies.remove("roles")
	}
	
	self.isConnected = function(){
		return cookies.get("authenticatedUser") != null;
	}
	
	self.hasAuthorization = function(path){
		var roles = cookies.getObject('roles')
		if(roles){
			var authorizedRoles = route.routes[path].data.authorized;
			var isAuthorized = false;
			var role
			for(role in roles){
				if(authorizedRoles.indexOf(roles[role]) != -1){
					isAuthorized = true;
				}
			}
			return isAuthorized;
		}
		else return false;
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
			self.addToCookie('authenticatedUser', 'user');
			http.get('/principal').then(function(resp){
				cookies.putObject('roles', resp.data )
			})
			location.path("/");
		}, function(resp){
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
				['$injector', '$mdDialog', 'UserService', '$location', function(injector, mdDialog, UserService, location) {
					var self = this;
										
					self.notAuthenticated401PopUp = function() {
							var confirm = mdDialog.confirm()
					          .title('Would you like to login ?')
					          .textContent('Your session has expired')
					          .ariaLabel('session expired')
					          .ok('Login')
					          .cancel('Home page');

							mdDialog.show(confirm).then(function() {
					    	UserService.removeUser('authenticatedUser');
					    	location.path('/login')
					    }, function(){
					    	UserService.removeUser('authenticatedUser');
					    	location.path('/home');
					    	});
						
					}
}])