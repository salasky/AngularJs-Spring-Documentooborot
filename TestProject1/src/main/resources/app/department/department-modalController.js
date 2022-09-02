angular
    .module('app')
    .controller('DepartmentModalController', DepartmentModalController);

function DepartmentModalController($uibModalInstance, $rootScope, syncData, dataService) {
    let vm = this;
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

    if (vm.data ) {
        editDepartment(vm.data);
    } else {
        addDepartment();
    }
    ;

    function editDepartment(department) {
        loadOrganizationData()
        vm.departmentsForm.id = department.id;
        vm.departmentsForm.fullName = department.fullName;
        vm.departmentsForm.shortName = department.shortName;
        vm.departmentsForm.supervisor = department.supervisor;
        vm.departmentsForm.contactNumber = department.contactNumber;
        vm.departmentsForm.organizationId = department.organizationId;
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
        let dataPromise =  dataService.getData('http://localhost:8080/departments');
        dataPromise.then(function (value) {
            $rootScope.rootDepartments = value;
        }).catch(error => console.error(error));
    }

    vm.ok = function () {
        vm.departmentsForm.organizationId = vm.myOrganization.id;

        if (vm.departmentsForm.id == -1) {
            vm.departmentsForm.id = null
            dataService.postData('http://localhost:8080/departments/add',vm.departmentsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));;
        } else {
            dataService.putData('http://localhost:8080/departments/update',vm.departmentsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));;
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteDepartment = function () {
        dataService.deleteData('http://localhost:8080/departments/' + vm.data.id)
            .then(_refreshCustomerData)
            .catch(error => console.error(error));;
        $uibModalInstance.close();
    };


    function loadOrganizationData() {
        let dataPromise =  dataService.getData('http://localhost:8080/organizations');
        dataPromise.then(function (value) {
            vm.organizations = value;
            for (const el of vm.organizations) {
                if (el.id == vm.data.organizationId) {
                    vm.myOrganization = el;
                }
            }
        }).catch(error => console.error(error));
    }
};

