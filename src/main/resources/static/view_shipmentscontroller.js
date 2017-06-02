 
            var app = angular.module("ShipmentManagement", []);
         
           		//Controller Part
            	app.controller("ShipmentController", function($scope, $http) {           
                $scope.shipments = [];
                //Now load the data from server
                _refreshshipmentData();
                /* Private Methods */
                //HTTP GET- get all shipments collection
                function _refreshshipmentData() {
                    $http({
                        method : 'GET',
                        url : 'http://localhost:8080/Shipments'
                    }).then(function successCallback(response) {
                        $scope.shipments = response.data;
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                    });
                }
         
                function _success(response) {
                    _refreshshipmentData();
                    _clearFormData()
                }
         
                function _error(response) {
                    console.log(response.statusText);
                }
            });
     