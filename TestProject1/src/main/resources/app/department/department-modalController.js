angular
    .module('app')
    .controller('DepartmentModalController', DepartmentModalController);

function DepartmentModalController($uibModalInstance, $rootScope, syncData, departmentService, organizationService) {
    const vm = this;
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

    if (vm.data) {
        editDepartment(vm.data);
    } else {
        addDepartment();
    }

    function editDepartment(department) {
        loadOrganizationData()
        Object.assign(vm.departmentsForm, department)
    }

    function addDepartment() {
        loadOrganizationData()
    }

    async function _refreshCustomerData() {
        let response = await departmentService.getDepartments();
        $rootScope.$broadcast("refreshDepartment", response.data);
    }

    vm.ok = async function () {
        vm.departmentsForm.organizationId = vm.myOrganization.id;
        if (vm.departmentsForm.id === -1) {
            vm.departmentsForm.id = null

            try {
                await departmentService.postDepartment(vm.departmentsForm)
                _refreshCustomerData()
            } catch (error) {
                alert(err.data.errorMessage)
            }

        } else {
            try {
                await departmentService.putDepartment(vm.departmentsForm)
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

    vm.deleteDepartment = async function () {
        try {
            await departmentService.deleteDepartment(vm.data.id)
            _refreshCustomerData();
        } catch (error) {
            alert(err.data.errorMessage)
        }
        $uibModalInstance.close();
    };

    async function loadOrganizationData() {
        try {
            let response = await organizationService.getOrganizations()
            vm.organizations = response.data;
        } catch (error) {
            alert(err.data.errorMessage)
        }
        if (vm.data) {
            for (const el of vm.organizations) {
                if (el.id == vm.data.organizationId) {
                    vm.myOrganization = el;
                }
            }
        }
    }
}

