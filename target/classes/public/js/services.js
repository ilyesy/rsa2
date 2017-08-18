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
		logout: function(){
			 $http.post('/logout').then(function(response){
	    		console.log('Successfully logged out on server');
	  	    },function(resp){
	  	    	console.log('log out fail')
	  	    	console.log(resp)
	  	    });
		}
	}
	
}])
.service('UserService', ['$cookies', function(cookies){
	
	this.setUser = function(data){
		cookies.put("rsacookie", data);
		console.log(cookies.get("rsacookies"))
	}
	
	this.removeUser = function(){
		cookies.remove("rsacookie")
	}
	
	this.isConnected = function(){
		return cookies.get("rsacookies") == null;
	}
}])