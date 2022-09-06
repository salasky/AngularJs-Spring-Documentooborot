angular
    .module('app')
    .controller('jobsModalController', jobsModalController);

function jobsModalController($uibModalInstance, syncData, dataService, $rootScope, URLS) {

    const vm = this;
    vm.data = syncData;

    vm.jobsForm = {
        id: -1,
        name: ""
    };
    if (vm.data) {
        editJob(vm.data);
    }

    function editJob(job) {
        vm.jobsForm = job;
    }


    vm.ok = function () {
        if (vm.jobsForm.id == -1) {
            vm.jobsForm.id = null
            dataService.postData(URLS.baseUrl + URLS.jobAdd, vm.jobsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        } else {
            dataService.putData(URLS.baseUrl + URLS.jobUpdate, vm.jobsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteJob = function () {
        dataService.deleteData(URLS.baseUrl + URLS.jobs + vm.data.id)
            .then(_refreshCustomerData)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };

    function _refreshCustomerData() {
        const dataPromise = dataService.getData(URLS.baseUrl + URLS.jobs);
        dataPromise.then(function (value) {
            $rootScope.rootJobs = value;
        }).catch(error => console.error(error));
    }
};

