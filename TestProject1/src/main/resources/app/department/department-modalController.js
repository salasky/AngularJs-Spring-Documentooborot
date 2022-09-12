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


    function _refreshCustomerData() {
        const dataPromise = departmentService.getDepartments();
        dataPromise.then(function (departments) {
            $rootScope.$broadcast("refreshDepartment", departments);
        }).catch(error => console.error(error));
    }

    vm.ok = function () {
        vm.departmentsForm.organizationId = vm.myOrganization.id;
        if (vm.departmentsForm.id === -1) {
            vm.departmentsForm.id = null

            departmentService.postDepartment(vm.departmentsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        } else {
            departmentService.putDepartment(vm.departmentsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteDepartment = function () {
        departmentService.deleteDepartment(vm.data.id)
            .then(_refreshCustomerData)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };

    function loadOrganizationData() {
        const dataPromise = organizationService.getOrganizations();
        dataPromise.then(function (organizations) {
            vm.organizations = organizations;
            if(vm.data){
                for (const el of vm.organizations) {
                    if (el.id == vm.data.organizationId) {
                        vm.myOrganization = el;
                    }
                }
            }
        }).catch(error => console.error(error));
    }
}

