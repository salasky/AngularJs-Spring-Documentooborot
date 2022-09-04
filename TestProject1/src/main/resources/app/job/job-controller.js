angular
    .module('app')
    .controller('JobController', JobController);


function JobController($uibModal, $rootScope, dataService) {
    const vm = this;
    _refreshCustomerData();

    function _refreshCustomerData() {
        const dataPromise = dataService.getData('http://localhost:8080/jobs');
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
