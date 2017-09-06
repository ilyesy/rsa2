angular.module('rsa')
.controller('homeController', [function() {
	var self = this;
}])
.controller('chapterController', ['ChaptersService', '$http', '$timeout', function(ChaptersService, http, timeout) {

	var self = this;
	self.res={};
	ChaptersService.getChapters().then(function(resp){
		self.res = resp.data
	},function(){})
	

	}])
	
.controller('impController', ['ImpsService', function(ImpsService) {
	var self = this;
	self.res={};
	ImpsService.getImps().then(function(resp){
		self.res = resp.data
	},function(){})
	}])
	
.controller('ruleController', ['RulesService', function(RulesService) {
	var self = this;
	self.res = {};
	RulesService.getRules().then(function(resp){
		self.res = resp.data
	}, function(){})
	}])
	
.controller('themeController', ['ThemesService','MainService', 'UserService', function(ThemesService,MainService, UserService) {
	var self = this;
	self.res={}
	ThemesService.getThemes().then(function(resp){
		self.res = resp.data;
	}, function(){
	})
	
	}])
	
	.controller('loginController', ['$http', '$location','UserService', 'MainService', function($http, location, UserService, MainService) {
	var self = this;
	self.user={}
	self.submit = function(){
		
		var requestStr;
		requestStr = UserService.transformToFormData(self.user);
		//$http.post('/login',{username: self.user.username, password: self.user.password}, {headers:{'Content-Type': 'application/x-www-form-urlencoded'}} )
		UserService.login(requestStr)
	}	
	}])
	.controller('AdminController', [function(){
	}])
	
	
