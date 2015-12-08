'use strict';

angular.module('dbeb')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/locations', {
        templateUrl: 'views/location/locations.html',
        controller: 'LocationController',
        resolve:{
          resolvedLocation: ['Location', function (Location) {
            return Location.query();
          }]
        }
      })
    }]);
