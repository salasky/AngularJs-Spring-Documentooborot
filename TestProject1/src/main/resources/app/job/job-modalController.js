angular
    .module('app')
    .controller('jobsModalController', jobsModalController);

function jobsModalController($uibModalInstance, syncData, dataService, $rootScope) {

    const vm = this;
    vm.data = syncData;

    vm.jobsForm = {
        id: -1,
        name: ""
    };
    if (vm.data) {
        editJob(vm.data);
    } else {
        addJob();
    }

    function editJob(job) {
        vm.jobsForm = job;
    }

    function addJob() {
        vm.jobsForm.id = -1;
        vm.jobsForm.name = "";
    }

    function _success(response) {
        _refreshCustomerData();
    }

    function _error(response) {
        console.log(response);
        alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

    }

    vm.ok = function () {
        if (vm.jobsForm.id == -1) {
            vm.jobsForm.id = null
            dataService.postData('http://localhost:8080/jobs/add', vm.jobsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        } else {
            dataService.putData('http://localhost:8080/jobs/update', vm.jobsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteJob = function () {
        dataService.deleteData('http://localhost:8080/jobs/' + vm.data.id)
            .then(_refreshCustomerData)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };

    function _refreshCustomerData() {
        const dataPromise = dataService.getData('http://localhost:8080/jobs');
        dataPromise.then(function (value) {
            $rootScope.rootJobs = value;
        }).catch(error => console.error(error));
    }
};

