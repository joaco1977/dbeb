'use strict';

angular.module('dbeb')
  .factory('User', ['$resource', function ($resource) {
    return  $resource('dbeb/users/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}

    });
  }]);
