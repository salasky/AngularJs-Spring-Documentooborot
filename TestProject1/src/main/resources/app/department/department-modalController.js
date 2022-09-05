angular
    .module('app')
    .controller('DepartmentModalController', DepartmentModalController);

function DepartmentModalController($uibModalInstance, $rootScope, syncData, dataService, URLS) {
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
        Object.assign( vm.departmentsForm, department)
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


    function _refreshCustomerData() {
        const dataPromise = dataService.getData(URLS.baseUrl+URLS.departments);
        dataPromise.then(function (departments) {
            $rootScope.rootDepartments = departments;
        }).catch(error => console.error(error));
    }

    vm.ok = function () {
        vm.departmentsForm.organizationId = vm.myOrganization.id;

        if (vm.departmentsForm.id === -1) {
            vm.departmentsForm.id = null
            dataService.postData(URLS.baseUrl+URLS.departmentAdd, vm.departmentsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        } else {
            dataService.putData(URLS.baseUrl+URLS.departmentUpdate, vm.departmentsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteDepartment = function () {
        dataService.deleteData(URLS.baseUrl+URLS.departments + vm.data.id)
            .then(_refreshCustomerData)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };


    function loadOrganizationData() {
        const dataPromise = dataService.getData(URLS.baseUrl+URLS.organizations);
        dataPromise.then(function (organizations) {
            vm.organizations = organizations;
            for (const el of vm.organizations) {
                if (el.id == vm.data.organizationId) {
                    vm.myOrganization = el;
                }
            }
        }).catch(error => console.error(error));
    }
}

