'use strict';

angular.module('dbeb')
  .controller('LoginController', ['$scope','Auth', function ($scope,Auth) { 

    



    $scope.logout = function(socialNet) {
    	Auth.logout(socialNet);
    }


    $scope.isLoggedIn = function(socialNet) {
    	
    	return Auth.isLoggedIn(socialNet);

    }

    $scope.getCurrentUser = function() {
        Auth.getUserFromSocial('facebook',function(result) {
            $scope.currentUser = result;
        });
    }


    $scope.login = function (socialNet) {
        Auth.login(socialNet);
    }


  }]);