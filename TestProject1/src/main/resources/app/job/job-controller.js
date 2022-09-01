angular
    .module('app')
    .controller('JobController', ['$http', '$uibModal', '$rootScope',

        function ($http, $uibModal, $rootScope) {
            let vm=this;
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
                alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
            }

            vm.openModal = function (jobs) {
                let modalInstance = $uibModal.open({
                    templateUrl: 'job/modalWindow.html',
                    controller: 'jobsModalController as vm',
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
