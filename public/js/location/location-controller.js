'use strict';

angular.module('dbeb')
  .controller('LocationController', ['$scope', '$modal', 'resolvedLocation', 'Location',
    function ($scope, $modal, resolvedLocation, Location) {

      $scope.locations = resolvedLocation;

      $scope.create = function () {
        $scope.clear();
        $scope.open();
      };

      $scope.update = function (id) {
        $scope.location = Location.get({id: id});
        $scope.open(id);
      };

      $scope.delete = function (id) {
        Location.delete({id: id},
          function () {
            $scope.locations = Location.query();
          });
      };

      $scope.save = function (id) {
        if (id) {
          Location.update({id: id}, $scope.location,
            function () {
              $scope.locations = Location.query();
              $scope.clear();
            });
        } else {
          Location.save($scope.location,
            function () {
              $scope.locations = Location.query();
              $scope.clear();
            });
        }
      };

      $scope.clear = function () {
        $scope.location = {
          
          "formattedAddress": "",
          
          "longitud": "",
          
          "latitud": "",
          
          "id": ""
        };
      };

      $scope.open = function (id) {
        var locationSave = $modal.open({
          templateUrl: 'location-save.html',
          controller: 'LocationSaveController',
          resolve: {
            location: function () {
              return $scope.location;
            }
          }
        });

        locationSave.result.then(function (entity) {
          $scope.location = entity;
          $scope.save(id);
        });
      };
    }])
  .controller('LocationSaveController', ['$scope', '$modalInstance', 'location',
    function ($scope, $modalInstance, location) {
      $scope.location = location;

      

      $scope.ok = function () {
        $modalInstance.close($scope.location);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }]);
