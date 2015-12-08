'use strict';

angular.module('dbeb')
  .controller('RecommendationController', ['$scope', '$modal', 'resolvedRecommendation',
   'Recommendation','Auth',
    function ($scope, $modal, resolvedRecommendation, Recommendation,Auth) {

      $scope.recommendations = resolvedRecommendation;

      $scope.create = function () {
        $scope.clear();
        $scope.open();
      };

      $scope.update = function (id) {
        $scope.recommendation = Recommendation.get({id: id});
        $scope.open(id);
      };

      $scope.delete = function (id) {
        Recommendation.delete({id: id},
          function () {
            $scope.recommendations = Recommendation.query();
          });
      };

      $scope.save = function (id) {
        if (id) {
          Recommendation.update({id: id}, $scope.recommendation,
            function () {
              $scope.recommendations = Recommendation.query();
              $scope.clear();
            });
        } else {
          Recommendation.save($scope.recommendation,
            function () {
              $scope.recommendations = Recommendation.query();
              $scope.clear();
            });
        }
      };

      $scope.clear = function () {
        $scope.recommendation = {
          
          "name": "",
          
          "info": "",
          
          "active": "",
          
          "companyId": "",
          
          "detail": "",
          
          "votes": "",
          
          "recodate": "",
          
          "createUserEmail": "",
          
          "id": ""
        };
      };

      $scope.isLoggedIn = function(socialNet) {
        return Auth.isLoggedIn(socialNet);
      }

      $scope.open = function (id) {
        var recommendationSave = $modal.open({
          templateUrl: 'recommendation-save.html',
          controller: 'RecommendationSaveController',
          resolve: {
            recommendation: function () {
              return $scope.recommendation;
            }
          }
        });

        recommendationSave.result.then(function (entity) {
          $scope.recommendation = entity;
          $scope.save(id);
        });
      };
    }])
  .controller('RecommendationSaveController', ['$scope', '$modalInstance', 'recommendation',
    function ($scope, $modalInstance, recommendation) {
      $scope.recommendation = recommendation;

      
      $scope.recodateDateOptions = {
        dateFormat: 'yy-mm-dd',
        maxDate: -1
        
      };

      $scope.ok = function () {
        $modalInstance.close($scope.recommendation);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }]);
