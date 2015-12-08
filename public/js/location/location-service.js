'use strict';

angular.module('dbeb')
  .factory('Location', ['$resource', function ($resource) {
    return $resource('dbeb/locations/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}
    });
  }]);
