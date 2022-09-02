angular
    .module('app')
    .controller('JobController', JobController);


function JobController($uibModal, $rootScope, dataService) {
    let vm = this;
    _refreshCustomerData();


    /* Private Methods */

    //HTTP GET- get all jobs collection
    function _refreshCustomerData() {
        let dataPromise = dataService.getData('http://localhost:8080/jobs');
        dataPromise.then(function (value) {
            $rootScope.rootJobs = value;
        }).catch(error => console.error(error));
    }

    vm.openModal = function (jobs) {
        return $uibModal.open({
            templateUrl: 'job/modalWindow.html',
            controller: 'jobsModalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => jobs,
            }
        });
    };
}
