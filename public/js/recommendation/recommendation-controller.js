'use strict';

angular.module('dbeb')
  .controller('RecommendationController', ['$scope', '$modal', 'resolvedRecommendation',
   'Recommendation','Auth',
    function ($scope, $modal, resolvedRecommendation, Recommendation,Auth) {

      $scope.result = '';
      //$scope.details = '';
      $scope.options = {};

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
        	
          $scope.recommendation.company.location.latitude =
        	  $scope.recommendation.company.location.details.geometry.location.lat();
          $scope.recommendation.company.location.longitude =
        	  $scope.recommendation.company.location.details.geometry.location.lng();
          
          delete $scope.recommendation.company.location.details;
          
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
          size: 'lg',
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

      
      $scope.result = "";
      //$scope.details = '';
      $scope.options = {};
      $scope.recommendation.company = {};
      $scope.recommendation.company.location = {};
      $scope.recommendation.company.location.details = {};
      $scope.recommendation.company.location.formattedAddress = '';

      $scope.votes = 0;
      $scope.max = 5;
      $scope.isReadonly = false;
      

      $scope.hoveringOver = function(value) {
        $scope.overStar = value;
        $scope.percent = 100 * (value / $scope.max);
      };
      
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
