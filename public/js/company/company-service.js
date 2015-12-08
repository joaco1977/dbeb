'use strict';

angular.module('dbeb')
  .factory('Company', ['$resource', function ($resource) {
    return $resource('dbeb/companies/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}
    });
  }]);
