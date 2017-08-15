angular.module('rsa')
.controller('mainController', [function() {
	console.log('main controller');
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
	
.controller('themeController', ['ThemesService', function(ThemesService) {
	var self = this;
	self.res={}
	ThemesService.getThemes().then(function(resp){
		self.res = resp.data;
		console.log(resp.data)
	}, function(){
	})
	}])
	
	
