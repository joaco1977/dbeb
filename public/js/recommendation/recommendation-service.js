'use strict';

angular.module('dbeb')
  .factory('Recommendation', ['$resource', function ($resource) {
    return $resource('dbeb/recommendations/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}
    });
  }]);
