'use strict';

angular.module('dbeb')
  .controller('CompanyController', ['$scope', '$modal', 'resolvedCompany', 'Company',
    function ($scope, $modal, resolvedCompany, Company) {

      $scope.companies = resolvedCompany;

      $scope.create = function () {
        $scope.clear();
        $scope.open();
      };

      $scope.update = function (id) {
        $scope.company = Company.get({id: id});
        $scope.open(id);
      };

      $scope.delete = function (id) {
        Company.delete({id: id},
          function () {
            $scope.companies = Company.query();
          });
      };

      $scope.save = function (id) {
        if (id) {
          Company.update({id: id}, $scope.company,
            function () {
              $scope.companies = Company.query();
              $scope.clear();
            });
        } else {
          Company.save($scope.company,
            function () {
              $scope.companies = Company.query();
              $scope.clear();
            });
        }
      };

      $scope.clear = function () {
        $scope.company = {
          
          "name": "",
          
          "info": "",
          
          "active": "",
          
          "locationId": "",
          
          "facebook": "",
          
          "email": "",
          
          "webSite": "",
          
          "id": ""
        };
      };

      $scope.open = function (id) {
        var companySave = $modal.open({
          templateUrl: 'company-save.html',
          controller: 'CompanySaveController',
          resolve: {
            company: function () {
              return $scope.company;
            }
          }
        });

        companySave.result.then(function (entity) {
          $scope.company = entity;
          $scope.save(id);
        });
      };
    }])
  .controller('CompanySaveController', ['$scope', '$modalInstance', 'company',
    function ($scope, $modalInstance, company) {
      $scope.company = company;

      

      $scope.ok = function () {
        $modalInstance.close($scope.company);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }]);
