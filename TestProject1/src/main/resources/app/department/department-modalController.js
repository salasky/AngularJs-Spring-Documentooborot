angular
    .module('app').controller('DepartmentModalController', function ($uibModalInstance, $http, syncData, $rootScope) {
    let vm=this;
 
    vm.data = syncData;
    vm.departmentsForm = {
        id: -1,
        fullName: "",
        shortName: "",
        supervisor: "",
        contactNumber: "",
        organizationId: ""
    };
    vm.organizations = [];

    if (vm.data != undefined) {
        editDepartment(vm.data);
    } else {
        addDepartment();
    };

    function editDepartment(department) {
        loadOrganizationData()
        vm.departmentsForm=department;
    }

    function addDepartment() {
        loadOrganizationData()
        vm.departmentsForm.id = -1;
        vm.departmentsForm.fullName = "";
        vm.departmentsForm.shortName = "";
        vm.departmentsForm.supervisor = "";
        vm.departmentsForm.contactNumber = "";
        vm.departmentsForm.organizationId = "";
    }

    function _success(response) {
        _refreshCustomerData();
    }

    function _error(response) {
        console.log(response);
        alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

    }

    function _refreshCustomerData() {
        vm.departments = [];
        $http({
            method: 'GET',
            url: 'http://localhost:8080/departments'
        }).then(function successCallback(response) {
            $rootScope.rootDepartments = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    vm.ok = function () {
        vm.departmentsForm.organizationId = vm.myOrganization.id;

        let method = "";
        let url = "";
        if (vm.departmentsForm.id == -1) {
            vm.departmentsForm.id = null
            method = "POST";
            url = 'http://localhost:8080/departments/add';
        } else {

            method = "PUT";
            url = 'http://localhost:8080/departments/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson(vm.departmentsForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteDepartment = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/departments/' + vm.data.id
        }).then(_success, _error);
    };


    function loadOrganizationData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/organizations'
        }).then(function successCallback(response) {
            vm.organizations = response.data;
            for (const el of vm.organizations) {
                if (el.id == vm.data.organizationId) {
                    vm.myOrganization = el;
                }
            }
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
});

