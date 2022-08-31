angular
    .module('app').controller('modalController', function ($scope, $uibModalInstance, $http, syncData, $rootScope) {

    $scope.data = syncData;

    $scope.organizationsForm = {
        id: -1,
        fullName: "",
        shortName: "",
        supervisor: "",
        contactNumbers: ""
    };
    if ($scope.data != undefined) {
        editOrganization($scope.data);
    } else {
        addOrganization();
    }
    ;


    function editOrganization(organization) {
        $scope.organizationsForm.id = organization.id;
        $scope.organizationsForm.fullName = organization.fullName;
        $scope.organizationsForm.shortName = organization.shortName;
        $scope.organizationsForm.supervisor = organization.supervisor;
        $scope.organizationsForm.contactNumbers = organization.contactNumbers;
    }

    function addOrganization() {
        $scope.organizationsForm.id = -1;
        $scope.organizationsForm.fullName = "";
        $scope.organizationsForm.shortName = "";
        $scope.organizationsForm.supervisor = "";
        $scope.organizationsForm.contactNumbers = "";
    }

    function _success(response) {
        _refreshCustomerData();
    }

    function _error(response) {
        console.log(response);
        alert($scope.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

    }

    function _refreshCustomerData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/organizations'
        }).then(function successCallback(response) {
            $rootScope.rootOrganizations = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });

    }

    $scope.ok = function () {
        let method = "";
        let url = "";
        if ($scope.organizationsForm.id == -1) {
            $scope.organizationsForm.id = null
            $scope.organizationsForm.contactNumbers = Array.of($scope.organizationsForm.contactNumbers);
            method = "POST";
            url = 'http://localhost:8080/organizations/add';
        } else {
            if (!Array.isArray($scope.organizationsForm.contactNumbers)) {
                $scope.organizationsForm.contactNumbers = Array.of($scope.organizationsForm.contactNumbers);
            }
            method = "PUT";
            url = 'http://localhost:8080/organizations/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.organizationsForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    $scope.cancel = function () {
        $uibModalInstance.close()
    }


    $scope.deleteOrganization = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/organizations/' + $scope.data.id
        }).then(_success, _error);
    };

});

