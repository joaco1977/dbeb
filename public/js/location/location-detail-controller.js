'use strict';

angular.module('dbeb').controller(
		'LocationDetailController',
		[ '$scope', '$uibModalInstance', 'location', 'NgMap',
				function($scope, $uibModalInstance, location, NgMap) {
					
					$scope.location = location;
			
					$scope.cancel = function() {
						$uibModalInstance.dismiss('cancel');
					};
					
					
					
					$scope.$on('mapInitialized', function(event,map) {
					    var marker = map.markers[0];

					    $scope.$watch('location.latitude + location.longitude',function(newVal,oldVal) {
						    console.log(newVal);
						    console.log(oldVal);
					    	if(newVal === oldVal) {
						    	return;
						    }
						    // checks if value has changed 
						    map.setCenter({lat:$scope.location.latitude,lng:$scope.location.longitude});
						    marker.setPosition({lat:$scope.location.longitude,lng:$scope.location.longitude});
					    });
					});
					

				}]);
