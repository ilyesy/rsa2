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
	
.controller('ruleController', ['$http', 'RulesService', 'DTOptionsBuilder', 'DTColumnBuilder', '$compile', function(http, RulesService, DTOptionsBuilder, DTColumnBuilder, $compile) {
	var vm = this;
	vm.textArray = [
		'/rules/slice?page=1&size=2',
		'/rules/slice?page=0&size=2'
	];
	vm.config = {}
	vm.myf = function(){
		var e = document.getElementsByClassName("paginate_button"); 
		var ee = angular.element(e);
		console.log(ee)
		var randomNumber = Math.floor(Math.random()*vm.textArray.length);
		return vm.textArray[randomNumber]
	}
	vm.dtOptions = DTOptionsBuilder.fromSource('/rules/slice')
	.withDataProp(function(json) {
//		console.log()
//		  console.log(json);
		  json.recordsTotal = json.page.totalElements;
		  json.recordsFiltered = json.page.totalElements;
//		  json.draw = 1;
//		  console.log(json);
		  return json._embedded.resourceList;
		})
    .withPaginationType('full_numbers')
    .withOption('displayLength', 2)
    .withOption('serverSide', true)
//    .withOption('processing', true)
    .withOption('createdRow', function (row) {
            // Recompiling so we can bind Angular directive to the DT
            $compile(angular.element(row).contents())(vm);
        })
        .withOption('saveState', true)
//    .withOption('bFilter', true)
    .withDOM('lfrtip');
	vm.dtColumns = [
    DTColumnBuilder.newColumn('title').withTitle('Title'),
    DTColumnBuilder.newColumn('description').withTitle('Description'),
    DTColumnBuilder.newColumn('_links.self.href').withTitle('link').renderWith(function(data, type, full) {
        return '<a href="' + full._links.self.href +'"> see </a>';
    })
    ];
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
	
	
