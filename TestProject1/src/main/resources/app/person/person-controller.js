angular
    .module('app')
    .controller('PersonController', PersonController);

function PersonController(dataService, $uibModal, $rootScope) {
    const vm = this;
    vm.department = "";
    vm.job = "";
    vm.organization = "";
    vm.activeTabNo = 0;
    vm.tabs = [];

    _refreshCustomerData();

    function _refreshCustomerData() {
        const dataPromise = dataService.getData('http://localhost:8080/persons');
        dataPromise.then(function (value) {
            $rootScope.rootPersons = value;
        }).catch(error => console.error(error));
    }

    vm.openModal = function (person) {
        const modalInstance = $uibModal.open({
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
        const dataPromiseDepartment = dataService.getData('http://localhost:8080/departments/' + person.departmentId);
        dataPromiseDepartment.then(function (value) {
            vm.department = value;
            const dataPromiseOrganization= dataService.getData('http://localhost:8080/organizations/' + vm.department.organizationId);
            dataPromiseOrganization.then(function (value) {
                vm.organization = value;

                const dataPromiseJob = dataService.getData('http://localhost:8080/jobs/' + person.jobTittleId);
                dataPromiseJob.then(function (value) {
                    vm.job = value;
                    let tabNo = person;
                    tabNo.organization = vm.organization;
                    tabNo.department = vm.department;
                    tabNo.job = vm.job;
                    tabNo.index = person.secondName + ' ' + person.id.substring(0, 3)
                    if (vm.tabs.includes(tabNo)) {
                        vm.activeTabNo = tabNo;
                    } else {
                        vm.tabs.push(tabNo);
                        vm.activeTabNo = tabNo;
                    }
                }).catch(error => console.error(error));
            }).catch(error => console.error(error));
        }).catch(error => console.error(error));
    };

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
    vm.activateTab = function (tabNo) {
        vm.activeTabNo = tabNo;
    };

    vm.openOrganizationModal = function () {
        return $uibModal.open({
            templateUrl: 'organization/modalWindow.html',
            controller: 'modalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => vm.organization,
            }
        });
    }


    vm.openDepartmentModal = function () {
        return $uibModal.open({
            templateUrl: 'department/modalWindow.html',
            controller: 'DepartmentModalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => vm.department,
            }
        });
    };

    vm.openJobModal = function () {
        return $uibModal.open({
            templateUrl: 'job/modalWindow.html',
            controller: 'jobsModalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => vm.job,
            }
        });
    };

}



