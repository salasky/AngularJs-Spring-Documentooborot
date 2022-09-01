angular
    .module('app')
    .controller('PersonController',PersonController );

function PersonController (  $http, $uibModal, $rootScope) {
            let vm=this;
            _refreshCustomerData();
            vm.department = "";
            vm.job = "";
            vm.organization = "";

            /* Private Methods */

            //HTTP GET- get all organizations collection
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

            function _success(response) {
                _refreshCustomerData();
            }

            function _error(response) {
                console.log(response);
                alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
            }

            vm.openModal = function (person) {
                let modalInstance = $uibModal.open({
                    templateUrl: 'person/modalWindow.html',
                    controller: 'PersonModalController as vm',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => person,
                    }
                });
                return modalInstance;
            };

            function staffInfoGet(person) {
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/departments/' + person.departmentId
                }).then(function successCallback(response) {
                    vm.department = response.data;
                    //После загрузки информации о департаменте, загружаем информацию о организации
                    $http({
                        method: 'GET',
                        url: 'http://localhost:8080/organizations/' + vm.department.organizationId
                    }).then(function successCallback(response) {
                        vm.organization = response.data;
                        $http({
                            method: 'GET',
                            url: 'http://localhost:8080/jobs/' + person.jobTittleId
                        }).then(function successCallback(response) {
                            vm.job = response.data;
                            let tabNo = person;
                            tabNo.organization = vm.organization;
                            tabNo.department = vm.department;
                            tabNo.job = vm.job;
                            tabNo.index = person.secondName + ' ' + person.id.substring(0, 3)
                            if(vm.tabs.includes(tabNo)){
                                vm.activeTabNo = tabNo;
                            }else{
                                vm.tabs.push(tabNo);
                                vm.activeTabNo = tabNo;
                            }

                        }, function errorCallback(response) {
                            console.log(response.statusText);
                        });
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                    });

                }, function errorCallback(response) {
                    console.log(response.statusText);
                });

            };

            vm.activeTabNo = 0;
            vm.tabs = [];

            vm.info = function (person) {
                staffInfoGet(person);
            };
            vm.remove = function (index) {
                if (index === 0) {
                    if (vm.activeTabNo === vm.tabs[0]) {
                        vm.activeTabNo = 0;
                    }
                } else {
                    if (vm.activeTabNo === vm.tabs[index]) {
                        vm.activeTabNo = vm.tabs[index - 1];
                    }
                }
                vm.tabs.splice(index, 1);
            };
            vm.activeTab = function (tabNo) {
                vm.activeTabNo = tabNo;
            };

            vm.openOrganizationModal = function () {
                let modalInstance = $uibModal.open({
                    templateUrl: 'organization/modalWindow.html',
                    controller: 'modalController as vm',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => vm.organization,
                    }
                });
                return modalInstance;
            }


            vm.openDepartmentModal = function () {
                let modalInstance = $uibModal.open({
                    templateUrl: 'department/modalWindow.html',
                    controller: 'DepartmentModalController as vm',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => vm.department,
                    }
                });
                return modalInstance;
            };

            vm.openJobModal = function () {
                let modalInstance = $uibModal.open({
                    templateUrl: 'job/modalWindow.html',
                    controller: 'jobsModalController as vm',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => vm.job,
                    }
                });
                return modalInstance;
            };

        };



