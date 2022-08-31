angular
    .module('app').controller('jobsModalController', function ($scope, $uibModalInstance, $http, syncData, $window, $rootScope) {

    $scope.data = syncData;

    $scope.jobsForm = {
        id: -1,
        name: ""
    };
    if ($scope.data != undefined) {
        editJob($scope.data);
    } else {
        addJob();
    }
    ;


    function editJob(job) {
        $scope.jobsForm.id = job.id;
        $scope.jobsForm.name = job.name;

    }

    function addJob() {
        $scope.jobsForm.id = -1;
        $scope.jobsForm.name = "";
    }

    function _success(response) {
        _refreshCustomerData();
    }

    function _error(response) {
        console.log(response);
        alert($scope.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

    }


    $scope.ok = function () {
        var method = "";
        var url = "";
        if ($scope.jobsForm.id == -1) {
            $scope.jobsForm.id = null
            method = "POST";
            url = 'http://localhost:8080/jobs/add';
        } else {
            method = "PUT";
            url = 'http://localhost:8080/jobs/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.jobsForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    $scope.cancel = function () {
        $uibModalInstance.close()
    }

    $scope.deleteJob = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/jobs/' + $scope.data.id
        }).then(_success, _error);
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
});

