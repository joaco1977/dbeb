'use strict';

angular.module('dbeb')
  .controller('RecommendationController', ['$scope', '$uibModal',
   'Recommendation','resolvedRecommendation','Auth','Tag',
    function ($scope, $uibModal, Recommendation,
      resolvedRecommendation,Auth,Tag) {



      $scope.nextPage = function() {
        var tempReco = Recommendation.query();

        for(var i = 0; i< tempReco.length;i++) {
            $scope.recommendations.push(tempReco[i]);
        }

      };
      $scope.recommendations = resolvedRecommendation;

      $scope.result = '';
      //$scope.details = '';
      $scope.options = {};

      
      $scope.max = 5;
      $scope.isReadonly = true;


      $scope.hoveringOver = function(value) {
        $scope.overStar = value;
        $scope.percent = 100 * (value / $scope.max);
      };

      

      $scope.create = function () {
        $scope.clear();
        $scope.open();
      };

      $scope.update = function (id) {
        $scope.recommendation = Recommendation.get({id: id});
        $scope.open(id);
      };

      /*$scope.delete = function (id) {
        Recommendation.delete({id: id},
          function () {
            $scope.recommendations = Recommendation.query();
          });
      };*/

      $scope.save = function (id) {
        if (id) {
          Recommendation.update({id: id}, $scope.recommendation,
            function () {
              $scope.clear();
              $scope.recommendations = Recommendation.query();
              
            });
        } else {
        	
          $scope.recommendation.company.location.latitude =
        	  $scope.recommendation.company.location.details.geometry.location.lat();
          $scope.recommendation.company.location.longitude =
        	  $scope.recommendation.company.location.details.geometry.location.lng();
          
          delete $scope.recommendation.company.location.details;
          
          $scope.recommendation.createUser = $scope.currentUser;
          
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
          
          "detail": "",
          
          "votes": "",
          
          "recodate": "",
          
          "id": ""
        };
      };

       $scope.isLoggedIn = function(socialNet) {
        return Auth.isLoggedIn(socialNet);
       }

      $scope.open = function (id) {
        var recommendationSave = $uibModal.open({
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
  .controller('RecommendationSaveController', ['$scope', '$uibModalInstance', '$http','recommendation','Tag',
    function ($scope, $uibModalInstance,$http, recommendation,Tag) {
      $scope.recommendation = recommendation;

      
      $scope.result = "";
      //$scope.details = '';
      $scope.options = {};
      $scope.recommendation.company = {};
      $scope.recommendation.company.location = {};
      $scope.recommendation.company.location.details = {};
      $scope.recommendation.company.location.formattedAddress = '';
      $scope.recommendation.tags = [];

      

      if(!$scope.recommendation.id) {
        $scope.recommendation.votes = 0;
      }
      
      $scope.max = 5;
      $scope.isReadonly = false;
      

      $scope.hoveringOver = function(value) {
        $scope.overStar = value;
        $scope.percent = 100 * (value / $scope.max);
      };
      
      $scope.recodateDateOptions = {
        dateFormat: 'yyyy-mm-dd',
        maxDate: -1
        
      };
      
      $scope.loadTags = function(query) {
    	  
        return $http.get('dbeb/tags?name=' + query);
        
      };

      $scope.ok = function () {
        $uibModalInstance.close($scope.recommendation);
      };

      $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
      };
    }]);
