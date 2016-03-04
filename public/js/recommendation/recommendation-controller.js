'use strict';

angular
		.module('dbeb')
		.controller(
				'RecommendationController',
				[
						'$scope',
						'$uibModal',
						'Recommendation',
						'Auth',
						'Tag',
						function($scope, $uibModal, Recommendation,Auth, Tag) {

							$scope.fetching = false;
							$scope.limit = 3;
							$scope.offset = 0;
							$scope.disabled = false;
							$scope.page = 1;
							
							$scope.recommendations = [];

							$scope.nextPage = function() {
								$scope.fetching = true;
								
								var recoTemp = Recommendation.query({
									offset : $scope.offset,
									searchText: $scope.searchText
								});

								recoTemp.$promise.then(function(data) {
									$scope.fetching = false;
									
									if (data.length) {
										$scope.offset += data.length;
										angular.forEach(data, function(item) {
											
											$scope.recommendations.push(item);
											
										});
									} else {
										$scope.disabled = true;
									}
									
									
									
								});
							};

							$scope.result = '';
							// $scope.details = '';
							$scope.options = {};

							$scope.max = 5;
							$scope.isReadonly = true;

							$scope.hoveringOver = function(value) {
								$scope.overStar = value;
								$scope.percent = 100 * (value / $scope.max);
							};

							$scope.create = function() {
								$scope.clear();
								$scope.open();
							};

							$scope.update = function(id) {
								$scope.recommendation = Recommendation.get({
									id : id
								});
								$scope.open(id);
							};

							/*
							 * $scope.delete = function (id) {
							 * Recommendation.delete({id: id}, function () {
							 * $scope.recommendations = Recommendation.query();
							 * }); };
							 */

							$scope.save = function(id) {
								if (id) {
									Recommendation.update({
										id : id
									}, $scope.recommendation, function() {
										$scope.clear();
										$scope.recommendations = Recommendation
												.query();

									});
								} else {

									$scope.recommendation.company.location.latitude = $scope.recommendation.company.location.details.geometry.location
											.lat();
									$scope.recommendation.company.location.longitude = $scope.recommendation.company.location.details.geometry.location
											.lng();

									delete $scope.recommendation.company.location.details;

									$scope.recommendation.createUser = $scope.currentUser;

									Recommendation
											.save(
													$scope.recommendation,
													function() {
														$scope.recommendations = Recommendation
																.query();
														$scope.clear();
													});
								}
							};

							$scope.clear = function() {
								$scope.recommendation = {

									"name" : "",

									"info" : "",

									"active" : "",

									"detail" : "",

									"votes" : "",

									"recodate" : "",

									"id" : ""
								};
							};

							$scope.isLoggedIn = function(socialNet) {
								return Auth.isLoggedIn(socialNet);
							}

							$scope.open = function(id) {
								var recommendationSave = $uibModal
										.open({
											templateUrl : 'recommendation-save.html',
											controller : 'RecommendationSaveController',
											size : 'lg',
											resolve : {
												recommendation : function() {
													return $scope.recommendation;
												}
											}
										});

								recommendationSave.result
										.then(function(entity) {
											$scope.recommendation = entity;
											$scope.save(id);
										});
							};
							
							
							$scope.openLocation = function(location) {
								var locationDetail = $uibModal
										.open({
											templateUrl : 'views/location/location.html',
											controller : 'LocationDetailController',
											size : 'lg',
											resolve : {
												location : function() {
													return location;
												}
											}
										});

								
							};
							
							$scope.searchRecoByText = function() {
								$scope.recommendations = [];
								$scope.offset = 0;
								$scope.nextPage();
							};
							

						} ]);