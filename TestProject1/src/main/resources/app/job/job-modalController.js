angular
    .module('app')
    .controller('jobsModalController',jobsModalController );

function jobsModalController ( $uibModalInstance, $http, syncData, $window, $rootScope) {

        
    let vm=this;
    vm.data = syncData;

    vm.jobsForm = {
        id: -1,
        name: ""
    };
    if (vm.data != undefined) {
        editJob(vm.data);
    } else {
        addJob();
    }
    ;


    function editJob(job) {
        vm.jobsForm=job;
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
        let method = "";
        let url = "";
        if (vm.jobsForm.id == -1) {
            vm.jobsForm.id = null
            method = "POST";
            url = 'http://localhost:8080/jobs/add';
        } else {
            method = "PUT";
            url = 'http://localhost:8080/jobs/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson(vm.jobsForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteJob = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/jobs/' + vm.data.id
        }).then(_success, _error);
        $uibModalInstance.close();
    };

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
};

