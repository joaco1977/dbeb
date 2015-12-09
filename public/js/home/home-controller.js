angular.module('dbeb')
  .controller('HomeController', ['$scope','Auth', 

  	function ($scope,Auth) {

  	$scope.isLoggedIn = function(socialNet) {
  		return Auth.isLoggedIn(socialNet);
  	}


  }]);
