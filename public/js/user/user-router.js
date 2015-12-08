'use strict';

angular.module('dbeb')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/users', {
        templateUrl: 'views/user/users.html',
        controller: 'UserController',
        authenticate: true,
        resolve:{
          resolvedUser: ['User', function (User) {
            return User.query();
          }]
        }
      })
    }]);
