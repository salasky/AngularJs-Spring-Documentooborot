angular
    .module('app')
    .controller('PersonModalController', PersonModalController);

function PersonModalController($uibModalInstance, personService, syncData, $rootScope, jobService, departmentService) {
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
        Object.assign(vm.personsForm, person);
        vm.personsForm.birthDay = new Date(person.birthDay);
    }

    function addPerson() {
        loadDepartmentData()
    }

    async function _refreshCustomerData() {
        let response = await personService.getPersons();
        $rootScope.$broadcast("refreshPersons", response.data);
    }

    vm.ok = async function () {
        vm.personsForm.departmentId = vm.myDepartment.id;
        vm.personsForm.jobTittleId = vm.myJob.id;

        if (vm.personsForm.id == -1) {
            vm.personsForm.id = null
            await personService.postPerson(vm.personsForm).catch(err=>alert(err.data.errorMessage))
            _refreshCustomerData()
        } else {
            await personService.putPerson(vm.personsForm).catch(err=>alert(err.data.errorMessage))
            _refreshCustomerData()
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deletePerson = async function () {
        await personService.deletePerson(vm.data.id).catch(err=>alert(err.data.errorMessage))
        _refreshCustomerData();
        $uibModalInstance.close();
    };

    async function loadDepartmentData() {
        let responseDepartments = await departmentService.getDepartments().catch(err=>alert(err.data.errorMessage))
        vm.departments = responseDepartments.data;
        if (vm.data) {
            for (const el of vm.departments) {
                if (el.id == vm.data.departmentId) {
                    vm.myDepartment = el;
                }
            }
        }
        let responseJobs = await jobService.getJobs().catch(err=>alert(err.data.errorMessage))
        vm.jobs = responseJobs.data;
        if (vm.data) {
            for (const el of vm.jobs) {
                if (el.id == vm.data.jobTittleId) {
                    vm.myJob = el;
                }
            }
        }
    }
}

