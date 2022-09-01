angular
    .module('app')
    .controller('PersonModalController',PersonModalController );

function PersonModalController ($uibModalInstance, $http, syncData, $rootScope) {
        let vm=this;
        
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


        if (vm.data != undefined) {
            editPerson(vm.data);
        } else {
            addPerson();
        }
        ;

        function editPerson(person) {
            loadDepartmentData()
            loadJobData();
            vm.personsForm.id = person.id;
            vm.personsForm.lastName = person.lastName;
            vm.personsForm.secondName = person.secondName;
            vm.personsForm.firstName = person.firstName;
            vm.personsForm.photoRef = person.photoRef;
            vm.personsForm.birthDay = person.birthDay;
            vm.personsForm.phoneNumber = person.phoneNumber;
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

        function _success(response) {
            _refreshCustomerData();
        }

        function _error(response) {
            console.log(response);
            alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

        }

        function _refreshCustomerData() {
            vm.persons = [];
            $http({
                method: 'GET',
                url: 'http://localhost:8080/persons'
            }).then(function successCallback(response) {
                $rootScope.rootPersons = response.data;
            }, function errorCallback(response) {
                console.log(response.statusText);
            });
        }

        vm.ok = function () {
            vm.personsForm.departmentId = vm.myDepartment.id;
            vm.personsForm.jobTittleId = vm.myJob.id;
            let method = "";
            let url = "";
            if (vm.personsForm.id == -1) {
                vm.personsForm.id = null
                method = "POST";
                url = 'http://localhost:8080/persons/add';
            } else {

                method = "PUT";
                url = 'http://localhost:8080/persons/update';
            }
            $http({
                method: method,
                url: url,
                data: angular.toJson(vm.personsForm),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(_success, _error);
            $uibModalInstance.close();
        }

        vm.cancel = function () {
            $uibModalInstance.close()
        }

        vm.deletePerson = function () {
            $http({
                method: 'DELETE',
                url: 'http://localhost:8080/persons/' + vm.data.id
            }).then(_success, _error);
            $uibModalInstance.close();
        };


        function loadDepartmentData() {
            $http({
                method: 'GET',
                url: 'http://localhost:8080/departments'
            }).then(function successCallback(response) {
                vm.departments = response.data;
                for (const el of vm.departments) {
                    if (el.id == vm.data.departmentId) {
                        vm.myDepartment = el;
                    }
                }
            }, function errorCallback(response) {
                console.log(response.statusText);
            });
        }

        function loadJobData() {
            $http({
                method: 'GET',
                url: 'http://localhost:8080/jobs'
            }).then(function successCallback(response) {
                vm.jobs = response.data;
                for (const el of vm.jobs) {
                    if (el.id == vm.data.jobTittleId) {
                        vm.myJob = el;
                    }
                }
            }, function errorCallback(response) {
                console.log(response.statusText);
            });
        }
    };

