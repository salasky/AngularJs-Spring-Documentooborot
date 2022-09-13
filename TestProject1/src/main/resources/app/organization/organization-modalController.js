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
        let response = await organizationService.getOrganizations().catch(err=>alert(err.data.errorMessage))
        $rootScope.$broadcast("refreshOrganizations", response.data);
    }

    vm.ok = async function () {
        if (vm.organizationsForm.id == -1) {
            vm.organizationsForm.id = null
            vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            await organizationService.postOrganization(vm.organizationsForm).catch(err=>alert(err.data.errorMessage))
            _refreshCustomerData()

        } else {
            if (!Array.isArray(vm.organizationsForm.contactNumbers)) {
                vm.organizationsForm.contactNumbers = Array.of(vm.organizationsForm.contactNumbers);
            }
            await organizationService.putOrganization(vm.organizationsForm).catch(err=>alert(err.data.errorMessage))
            _refreshCustomerData();
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteOrganization = async function () {
        await organizationService.deleteOrganization(vm.data.id).catch(err=>alert(err.data.errorMessage))
        _refreshCustomerData();
        $uibModalInstance.close();
    };
};

