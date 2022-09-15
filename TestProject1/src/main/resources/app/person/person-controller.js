angular
    .module('app')
    .controller('PersonController', PersonController);

function PersonController(personService, $uibModal, $rootScope, $scope, departmentService, jobService, organizationService) {
    const vm = this;
    vm.department = "";
    vm.job = "";
    vm.organization = "";
    vm.activeTabNo = 0;
    vm.tabs = [];

    vm.$onInit = function () {
        _refreshCustomerData();
    }
    async function _refreshCustomerData() {
        try {
            let response = await personService.getPersons()
            vm.persons = response.data;
            $scope.$apply();
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    $scope.$on("refreshPersons", function (evt, data) {
        vm.persons = data;
    });

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

    async function staffInfoGet(person) {
        try {
            let responseDepartment = await departmentService.getDepartment(person.departmentId)
            vm.department = responseDepartment.data;
            let responseOrganization= await organizationService.getOrganization(vm.department.organizationId)
            vm.organization = responseOrganization.data;
            let responseJob= await jobService.getJob(person.jobTittleId);
            vm.job = responseJob.data;
        } catch (error) {
            alert(err.data.errorMessage)
        }
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
        $scope.$apply();
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



