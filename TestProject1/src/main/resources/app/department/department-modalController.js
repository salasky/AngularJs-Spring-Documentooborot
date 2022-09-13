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

            await departmentService.postDepartment(vm.departmentsForm).catch(err=>alert(err.data.errorMessage))
            _refreshCustomerData()
        } else {
            await departmentService.putDepartment(vm.departmentsForm).catch(err=>alert(err.data.errorMessage))
            _refreshCustomerData();
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteDepartment = async function () {
        await departmentService.deleteDepartment(vm.data.id).catch(err=>alert(err.data.errorMessage))
        _refreshCustomerData();
        $uibModalInstance.close();
    };

    async function loadOrganizationData() {
        let response = await organizationService.getOrganizations().catch(err=>alert(err.data.errorMessage))
        vm.organizations = response.data;
        if (vm.data) {
            for (const el of vm.organizations) {
                if (el.id == vm.data.organizationId) {
                    vm.myOrganization = el;
                }
            }
        }
    }
}

