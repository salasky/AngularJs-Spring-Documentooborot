angular
    .module('app')
    .controller('PersonModalController', PersonModalController);

function PersonModalController($uibModalInstance, dataService, syncData, $rootScope, URLS) {
    const vm = this;

    vm.data = syncData;
    vm.personsForm = {
        id: -1,
        lastName: "",
        secondName: "",
        firstName: "",
        photoRef: "",
        birthDay: "",
        phoneNumber: "",
        jobTittleId: "",
        departmentId: "",

    };
    vm.departments = [];
    vm.jobs = [];


    if (vm.data) {
        editPerson(vm.data);
    } else {
        addPerson();
    }

    function editPerson(person) {
        loadDepartmentData()
        loadJobData();
        Object.assign( vm.personsForm, person);
        vm.personsForm.birthDay = new Date(person.birthDay);
    }

    function addPerson() {
        loadDepartmentData()
        loadJobData();
        vm.personsForm.id = -1;
        vm.personsForm.lastName = "";
        vm.personsForm.secondName = "";
        vm.personsForm.firstName = "";
        vm.personsForm.photoRef = "";
        vm.personsForm.birthDay = "";
        vm.personsForm.phoneNumber = "";
    }

    function _refreshCustomerData() {
        const dataPromise = dataService.getData(URLS.baseUrl+URLS.persons);
        dataPromise.then(function (persons) {
            $rootScope.rootPersons = persons;
        }).catch(error => console.error(error));
    }

    vm.ok = function () {
        vm.personsForm.departmentId = vm.myDepartment.id;
        vm.personsForm.jobTittleId = vm.myJob.id;

        if (vm.personsForm.id == -1) {
            vm.personsForm.id = null
            dataService.postData(URLS.baseUrl+URLS.personAdd, vm.personsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        } else {
            dataService.putData(URLS.baseUrl+URLS.personUpdate, vm.personsForm)
                .then(_refreshCustomerData)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deletePerson = function () {
        dataService.deleteData(URLS.baseUrl+URLS.persons + vm.data.id)
            .then(_refreshCustomerData)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };

    function loadDepartmentData() {
        const dataPromise = dataService.getData(URLS.baseUrl+URLS.departments);
        dataPromise.then(function (departments) {
            vm.departments = departments;
            for (const el of vm.departments) {
                if (el.id == vm.data.departmentId) {
                    vm.myDepartment = el;
                }
            }
        }).catch(error => console.error(error));
    }

    function loadJobData() {
        const dataPromise = dataService.getData(URLS.baseUrl+URLS.jobs);
        dataPromise.then(function (jobs) {
            vm.jobs = jobs;
            for (const el of vm.jobs) {
                if (el.id == vm.data.jobTittleId) {
                    vm.myJob = el;
                }
            }
        }).catch(error => console.error(error));
    }
}

