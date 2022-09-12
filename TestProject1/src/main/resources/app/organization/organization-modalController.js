angular
    .module('app')
    .controller('modalController', modalController);

function modalController($uibModalInstance, syncData, $rootScope, dataService, URLS) {

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
        vm.organizationsForm = organization;
    }

    function _refreshCustomerData() {
        const dataPromise = dataService.getData(URLS.baseUrl + URLS.organizations);
        dataPromise.then(function (value) {
            $rootScope.rootOrganizations = value;
        }).catch(error => console.error(error));
    }

    vm.ok = function () {
        if (vm.organizationsForm.id == -1) {
            vm.organizationsForm.id = null
            vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            dataService.postData(URLS.baseUrl + URLS.organizationAdd, vm.organizationsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        } else {
            if (!Array.isArray(vm.organizationsForm.contactNumbers)) {
                vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            }
            dataService.putData(URLS.baseUrl + URLS.organizationUpdate, vm.organizationsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteOrganization = function () {
        dataService.deleteData(URLS.baseUrl + URLS.organizations + vm.data.id)
            .then(_refreshCustomerData)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };
};

