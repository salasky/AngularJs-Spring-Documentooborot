angular
    .module('app')
    .controller('modalController', modalController);

function modalController($uibModalInstance, syncData, $rootScope, dataService) {

    const vm = this;
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
    }

    function editOrganization(organization) {
        vm.organizationsForm = organization;
    }

    function addOrganization() {
        vm.organizationsForm.id = -1;
        vm.organizationsForm.fullName = "";
        vm.organizationsForm.shortName = "";
        vm.organizationsForm.supervisor = "";
        vm.organizationsForm.contactNumbers = "";
    }

    function _refreshCustomerData() {
        const dataPromise = dataService.getData('http://localhost:8080/organizations');
        dataPromise.then(function (value) {
            $rootScope.rootOrganizations = value;
        }).catch(error => console.error(error));

    }

    vm.ok = function () {
        if (vm.organizationsForm.id == -1) {
            vm.organizationsForm.id = null
            vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            dataService.postData('http://localhost:8080/organizations/add', vm.organizationsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        } else {
            if (!Array.isArray(vm.organizationsForm.contactNumbers)) {
                vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            }
            dataService.putData('http://localhost:8080/organizations/update', vm.organizationsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteOrganization = function () {
        dataService.deleteData('http://localhost:8080/organizations/' + vm.data.id)
            .then(_refreshCustomerData)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };
};

