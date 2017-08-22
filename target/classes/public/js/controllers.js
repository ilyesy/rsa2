angular.module('rsa')
.controller('homeController', ['UserService',  function(UserService) {
	console.log('home controller');
}])
.controller('chapterController', ['ChaptersService', function(ChaptersService) {
	console.log('chapctl');
	var self = this;
	self.res={};
	ChaptersService.getChapters().then(function(resp){
		self.res = resp.data
		console.log('ok')
	},function(){})
		
	}])
	
.controller('impController', ['ImpsService', function(ImpsService) {
	console.log('impCtl');
	var self = this;
	self.res={};
	ImpsService.getImps().then(function(resp){
		self.res = resp.data
		//console.log(resp.data)
	},function(){})
	}])
	
.controller('ruleController', ['RulesService', function(RulesService) {
	var self = this;
	self.res = {};
	RulesService.getRules().then(function(resp){
		self.res = resp.data
		//console.log(self.res)
	}, function(){})
	}])
	
.controller('themeController', ['ThemesService','MainService', 'UserService', function(ThemesService,MainService, UserService) {
	var self = this;
	self.res={}
	ThemesService.getThemes().then(function(resp){
		self.res = resp.data;
		//console.log(resp.data)
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
	
	
