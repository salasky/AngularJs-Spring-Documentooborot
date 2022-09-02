angular
    .module('app')
    .controller('modalController',modalController );

function modalController ( $uibModalInstance, $http, syncData, $rootScope) {

    let vm=this;
    vm.data = syncData;

    vm.organizationsForm = {
        id: -1,
        fullName: "",
        shortName: "",
        supervisor: "",
        contactNumbers: ""
    };
    if (vm.data) {
        editOrganization(vm.data);
    } else {
        addOrganization();
    };


    function editOrganization(organization) {
        vm.organizationsForm=organization;
    }

    function addOrganization() {
        vm.organizationsForm.id = -1;
        vm.organizationsForm.fullName = "";
        vm.organizationsForm.shortName = "";
        vm.organizationsForm.supervisor = "";
        vm.organizationsForm.contactNumbers = "";
    }

    function _success(response) {
        _refreshCustomerData();
    }

    function _error(response) {
        console.log(response);
        alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

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

    vm.ok = function () {
        let method = "";
        let url = "";
        if (vm.organizationsForm.id == -1) {
            vm.organizationsForm.id = null
            vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            method = "POST";
            url = 'http://localhost:8080/organizations/add';
        } else {
            if (!Array.isArray(vm.organizationsForm.contactNumbers)) {
                vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            }
            method = "PUT";
            url = 'http://localhost:8080/organizations/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson(vm.organizationsForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }


    vm.deleteOrganization = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/organizations/' + vm.data.id
        }).then(_success, _error);
        $uibModalInstance.close();
    };
};

