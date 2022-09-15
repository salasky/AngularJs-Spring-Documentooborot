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
        try {
            let response = await personService.getPersons();
            $rootScope.$broadcast("refreshPersons", response.data);
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    vm.ok = async function () {
        vm.personsForm.departmentId = vm.myDepartment.id;
        vm.personsForm.jobTittleId = vm.myJob.id;

        if (vm.personsForm.id == -1) {
            vm.personsForm.id = null
            try {
                await personService.postPerson(vm.personsForm)
                _refreshCustomerData()
            } catch (error) {
                alert(err.data.errorMessage)
            }
        } else {
            try {
                await personService.putPerson(vm.personsForm)
                _refreshCustomerData()
            } catch (error) {
                alert(err.data.errorMessage)
            }
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deletePerson = async function () {
        try {
            await personService.deletePerson(vm.data.id)
            _refreshCustomerData();
        } catch (error) {
            alert(err.data.errorMessage)
        }
        $uibModalInstance.close();
    };

    async function loadDepartmentData() {
        try {
            let responseDepartments = await departmentService.getDepartments()
            vm.departments = responseDepartments.data;
        } catch (error) {
            alert(err.data.errorMessage)
        }
        if (vm.data) {
            for (const el of vm.departments) {
                if (el.id == vm.data.departmentId) {
                    vm.myDepartment = el;
                }
            }
        }
        try {
            let responseJobs = await jobService.getJobs()
            vm.jobs = responseJobs.data;
        } catch (error) {
            alert(err.data.errorMessage)
        }
        if (vm.data) {
            for (const el of vm.jobs) {
                if (el.id == vm.data.jobTittleId) {
                    vm.myJob = el;
                }
            }
        }
    }
}

