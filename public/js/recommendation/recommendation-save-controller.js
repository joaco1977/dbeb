'use strict';

angular
		.module('dbeb')
		.controller(
				'RecommendationSaveController',
				[
						'$scope',
						'$uibModalInstance',
						'$http',
						'recommendation',
						'Tag',
						function($scope, $uibModalInstance, $http,
								recommendation, Tag) {
							$scope.recommendation = recommendation;

							$scope.result = "";
							// $scope.details = '';
							$scope.options = {};
							$scope.recommendation.company = {};
							$scope.recommendation.company.location = {};
							$scope.recommendation.company.location.details = {};
							$scope.recommendation.company.location.formattedAddress = '';
							$scope.recommendation.tags = [];

							if (!$scope.recommendation.id) {
								$scope.recommendation.votes = 0;
							}

							$scope.max = 5;
							$scope.isReadonly = false;

							$scope.hoveringOver = function(value) {
								$scope.overStar = value;
								$scope.percent = 100 * (value / $scope.max);
							};

							$scope.recodateDateOptions = {
								dateFormat : 'yyyy-mm-dd',
								maxDate : -1

							};

							$scope.loadTags = function(query) {

								return $http.get('dbeb/tags?name=' + query);

							};

							$scope.ok = function() {
								$uibModalInstance.close($scope.recommendation);
							};

							$scope.cancel = function() {
								$uibModalInstance.dismiss('cancel');
							};
						} ]);
