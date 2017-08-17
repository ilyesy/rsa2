angular.module('rsa')
.controller('mainController', ['MainService', function(MainService) {
	console.log('main controller');
	var self = this;
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
		console.log(resp.data)
	},function(){})
	}])
	
.controller('ruleController', ['RulesService', function(RulesService) {
	var self = this;
	self.res = {};
	RulesService.getRules().then(function(resp){
		self.res = resp.data
		console.log(self.res)
	}, function(){})
	}])
	
.controller('themeController', ['ThemesService','MainService', function(ThemesService,MainService) {
	var self = this;
	self.res={}
	ThemesService.getThemes().then(function(resp){
		self.res = resp.data;
		console.log(resp.data)
	}, function(){
	})
	self.logout = function(){
		console.log("logging out")
		MainService.logout();
	}
	}])
	
	.controller('loginController', ['$http', 'MainService', function($http, MainService) {
	var self = this;
	self.user={}
	self.submit = function(){
		
		var requestStr;
		 if (self.user) {
			 //self.user = JSON.parse(self.user);
			 for (var key in self.user) {
				 if (requestStr) {
					 requestStr += '&' + key + '=' + self.user[key];
				 } else {
					 requestStr = key + '=' + self.user[key];
				 }
			 	}
			 }

		 
		//$http.post('/login',{username: self.user.username, password: self.user.password}, {headers:{'Content-Type': 'application/x-www-form-urlencoded'}} )
		$http({
			    method: 'POST',
			    url: '/login',
			    data: requestStr,
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			})
		.then(function(resp){
			console.log("logged in")
			//console.log(resp.data);
		}, function(resp){
			console.log(resp)
			console.log('sorry');
		})
	}
	
	
	
	
	}])
	
	
