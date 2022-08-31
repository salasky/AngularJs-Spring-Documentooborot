angular
    .module('app').controller('DepartmentModalController', function ($scope, $uibModalInstance, $http, syncData, $rootScope) {

    $scope.data = syncData;
    $scope.departmentsForm = {
        id: -1,
        fullName: "",
        shortName: "",
        supervisor: "",
        contactNumber: "",
        organizationId: ""
    };
    $scope.organizations = [];

    if ($scope.data != undefined) {
        editDepartment($scope.data);
    } else {
        addDepartment();
    }
    ;

    function editDepartment(department) {
        loadOrganizationData()
        $scope.departmentsForm.id = department.id;
        $scope.departmentsForm.fullName = department.fullName;
        $scope.departmentsForm.shortName = department.shortName;
        $scope.departmentsForm.supervisor = department.supervisor;
        $scope.departmentsForm.contactNumber = department.contactNumber;
        $scope.departmentsForm.organizationId = department.organizationId;
    }

    function addDepartment() {
        loadOrganizationData()
        $scope.departmentsForm.id = -1;
        $scope.departmentsForm.fullName = "";
        $scope.departmentsForm.shortName = "";
        $scope.departmentsForm.supervisor = "";
        $scope.departmentsForm.contactNumber = "";
        $scope.departmentsForm.organizationId = "";
    }

    function _success(response) {
        _refreshCustomerData();
    }

    function _error(response) {
        console.log(response);
        alert($scope.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

    }

    function _refreshCustomerData() {
        $scope.departments = [];
        $http({
            method: 'GET',
            url: 'http://localhost:8080/departments'
        }).then(function successCallback(response) {
            $rootScope.rootDepartments = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.ok = function () {
        $scope.departmentsForm.organizationId = $scope.myOrganization.id;

        var method = "";
        var url = "";
        if ($scope.departmentsForm.id == -1) {
            $scope.departmentsForm.id = null
            method = "POST";
            url = 'http://localhost:8080/departments/add';
        } else {

            method = "PUT";
            url = 'http://localhost:8080/departments/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.departmentsForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    $scope.cancel = function () {
        $uibModalInstance.close()
    }

    $scope.deleteDepartment = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/departments/' + $scope.data.id
        }).then(_success, _error);
    };


    function loadOrganizationData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/organizations'
        }).then(function successCallback(response) {
            $scope.organizations = response.data;
            for (const el of $scope.organizations) {
                if (el.id == $scope.data.organizationId) {
                    $scope.myOrganization = el;
                }
            }
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
});

