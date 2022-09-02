angular
    .module('app')
    .controller('DepartmentController',DepartmentController );


        function DepartmentController ( $http, $uibModal, $rootScope,dataService) {
            let vm=this;

            _refreshCustomerData();

             function _refreshCustomerData() {
                let dataPromise =  dataService.getData('http://localhost:8080/departments');
                dataPromise.then(function (value) {
                    $rootScope.rootDepartments = value;
                })
            }

            function _success(response) {
                _refreshCustomerData();
            }

            function _error(response) {
                console.log(response);
                alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
            }

            vm.openOrganizationModal = function () {
                    let modalInstance = $uibModal.open({
                        templateUrl: 'organization/modalWindow.html',
                        controller: 'modalController',
                        backdrop: false,
                        size: 'm',
                        animation: true,
                        resolve: {
                            syncData: () => vm.organization,
                        }
                    });
                    return modalInstance;
                }


                vm.openModal = function (department) {
                        let modalInstance = $uibModal.open({
                            templateUrl: 'department/modalWindow.html',
                            controller: 'DepartmentModalController as vm',
                            backdrop: false,
                            size: 'm',
                            animation: true,
                            resolve: {
                                syncData: () => department,
                            }
                        });
                        return modalInstance;
                    };

                     function organizationInfo(department) {
                        let dataPromise = dataService.getData('http://localhost:8080/organizations/' + department.organizationId);
                         dataPromise.then(function (value) {
                            vm.organization = value;
                             let tabNo = department
                             tabNo.organization = vm.organization;
                             tabNo.index = department.fullName + ' ' + department.id.substring(0, 3)
                             if (vm.tabs.includes(tabNo)) {
                                 vm.activeTabNo = tabNo;
                             } else {
                                 vm.tabs.push(tabNo);
                                 vm.activeTabNo = tabNo;
                             }
                        });
                    };

                    vm.activeTabNo = 0;
                    vm.tabs = [];
                    vm.department = "";

                    vm.info = function (department) {
                        organizationInfo(department);

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

                };