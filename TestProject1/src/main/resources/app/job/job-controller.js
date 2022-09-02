angular
    .module('app')
    .controller('JobController',JobController );


function JobController ($uibModal, $rootScope, dataService) {
            let vm=this;
            _refreshCustomerData();


            /* Private Methods */

            //HTTP GET- get all jobs collection
            function _refreshCustomerData() {
                let dataPromise =  dataService.getData('http://localhost:8080/jobs');
                dataPromise.then(function (value) {
                    $rootScope.rootJobs = value;
                }).catch(error => console.error(error));
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
        };
