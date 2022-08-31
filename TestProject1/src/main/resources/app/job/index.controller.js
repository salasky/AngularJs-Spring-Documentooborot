angular
    .module('app')
    .controller('Job.IndexController', ['$scope', '$http', '$uibModal', '$rootScope',

        function ($scope, $http, $uibModal, $rootScope) {

            _refreshCustomerData();


            /* Private Methods */

            //HTTP GET- get all jobs collection
            function _refreshCustomerData() {
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/jobs'
                }).then(function successCallback(response) {
                    $rootScope.rootJobs = response.data;
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });

            }

            function _success(response) {
                _refreshCustomerData();
            }

            function _error(response) {
                console.log(response);
                alert($scope.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
            }

            $scope.openModal = function (jobs) {
                var modalInstance = $uibModal.open({
                    templateUrl: 'job/modalWindow.html',
                    controller: 'jobsModalController',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => jobs,
                    }
                });
                return modalInstance;
            };
        }])
