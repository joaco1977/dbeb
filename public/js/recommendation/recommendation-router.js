'use strict';

angular.module('dbeb')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/recommendations', {
        templateUrl: 'views/recommendation/recommendations.html',
        controller: 'RecommendationController',
        authenticate: false
      })
    }]);
