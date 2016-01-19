'use strict';

angular.module('dbeb')
  .factory('Tag', ['$resource', function ($resource) {
    return $resource('dbeb/tags/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}
    });
  }]);
