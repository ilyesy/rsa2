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