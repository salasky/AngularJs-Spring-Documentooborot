angular
    .module('app')
    .controller('modalController', modalController);

function modalController($uibModalInstance, syncData, $rootScope, organizationService) {

    const vm = this;
    vm.data = syncData;

    vm.organizationsForm = {
        id: -1,
        fullName: "",
        shortName: "",
        supervisor: "",
        contactNumbers: ""
    }

    if (vm.data) {
        vm.organizationsForm = vm.data;
    }

    async function _refreshCustomerData() {
        try {
            let response = await organizationService.getOrganizations()
            $rootScope.$broadcast("refreshOrganizations", response.data);
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    vm.ok = async function () {
        if (vm.organizationsForm.id == -1) {
            vm.organizationsForm.id = null
            vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            try {
                await organizationService.postOrganization(vm.organizationsForm)
                _refreshCustomerData()
            } catch (error) {
                alert(err.data.errorMessage)
            }
        } else {
            if (!Array.isArray(vm.organizationsForm.contactNumbers)) {
                vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            }
            try {
                await organizationService.putOrganization(vm.organizationsForm)
                _refreshCustomerData();
            } catch (error) {
                alert(err.data.errorMessage)
            }
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteOrganization = async function () {
        try {
            await organizationService.deleteOrganization(vm.data.id)
            _refreshCustomerData();
        } catch (error) {
            alert(err.data.errorMessage)
        }
        $uibModalInstance.close();
    };
};

