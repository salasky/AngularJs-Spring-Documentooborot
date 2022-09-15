angular
    .module('app')
    .controller('JobController', JobController);


function JobController($uibModal, $rootScope, jobService, $scope) {
    const vm = this;

    vm.$onInit = function () {
        _refreshCustomerData();
    }

    async function _refreshCustomerData() {
        try {
            let response  = await jobService.getJobs();
            vm.jobs= response.data;
            $scope.$apply();
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    $scope.$on("refreshJobs", function (evt, data) {
        vm.jobs = data;
    });

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
