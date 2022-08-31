angular
    .module('app').controller('PersonModalController', function ($scope, $uibModalInstance, $http, syncData, $rootScope) {

        $scope.data = syncData;
        $scope.personsForm = {
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
        $scope.departments = [];
        $scope.jobs = [];


        if ($scope.data != undefined) {
            editPerson($scope.data);
        } else {
            addPerson();
        }
        ;

        function editPerson(person) {
            loadDepartmentData()
            loadJobData();
            $scope.personsForm.id = person.id;
            $scope.personsForm.lastName = person.lastName;
            $scope.personsForm.secondName = person.secondName;
            $scope.personsForm.firstName = person.firstName;
            $scope.personsForm.photoRef = person.photoRef;
            $scope.personsForm.birthDay = person.birthDay;
            $scope.personsForm.phoneNumber = person.phoneNumber;
            $scope.personsForm.birthDay = new Date(person.birthDay);
        }

        function addPerson() {
            loadDepartmentData()
            loadJobData();
            $scope.personsForm.id = -1;
            $scope.personsForm.lastName = "";
            $scope.personsForm.secondName = "";
            $scope.personsForm.firstName = "";
            $scope.personsForm.photoRef = "";
            $scope.personsForm.birthDay = "";
            $scope.personsForm.phoneNumber = "";
        }

        function _success(response) {
            _refreshCustomerData();
        }

        function _error(response) {
            console.log(response);
            alert($scope.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

        }

        function _refreshCustomerData() {
            $scope.persons = [];
            $http({
                method: 'GET',
                url: 'http://localhost:8080/persons'
            }).then(function successCallback(response) {
                $rootScope.rootPersons = response.data;
            }, function errorCallback(response) {
                console.log(response.statusText);
            });
        }

        $scope.ok = function () {
            $scope.personsForm.departmentId = $scope.myDepartment.id;
            $scope.personsForm.jobTittleId = $scope.myJob.id;
            let method = "";
            let url = "";
            if ($scope.personsForm.id == -1) {
                $scope.personsForm.id = null
                method = "POST";
                url = 'http://localhost:8080/persons/add';
            } else {

                method = "PUT";
                url = 'http://localhost:8080/persons/update';
            }
            $http({
                method: method,
                url: url,
                data: angular.toJson($scope.personsForm),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(_success, _error);
            $uibModalInstance.close();
        }

        $scope.cancel = function () {
            $uibModalInstance.close()
        }

        $scope.deletePerson = function () {
            $http({
                method: 'DELETE',
                url: 'http://localhost:8080/persons/' + $scope.data.id
            }).then(_success, _error);
        };


        function loadDepartmentData() {
            $http({
                method: 'GET',
                url: 'http://localhost:8080/departments'
            }).then(function successCallback(response) {
                $scope.departments = response.data;
                for (const el of $scope.departments) {
                    if (el.id == $scope.data.departmentId) {
                        $scope.myDepartment = el;
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
                $scope.jobs = response.data;
                for (const el of $scope.jobs) {
                    if (el.id == $scope.data.jobTittleId) {
                        $scope.myJob = el;
                    }
                }
            }, function errorCallback(response) {
                console.log(response.statusText);
            });
        }
    }
);

