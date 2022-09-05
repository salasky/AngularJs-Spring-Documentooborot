angular
    .module('app')
    .controller('DepartmentController', DepartmentController);


function DepartmentController($uibModal, $rootScope, dataService, URLS) {
    const vm = this;
    vm.activeTabNo = 0;
    vm.tabs = [];
    _refreshCustomerData();

    function _refreshCustomerData() {
        const dataPromise = dataService.getData(URLS.baseUrl + URLS.departments);
        dataPromise.then(function (departments) {
            $rootScope.rootDepartments = departments;
        }).catch(error => console.error(error));
    }

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

    vm.openModal = function (department) {
        return $uibModal.open({
            templateUrl: 'department/modalWindow.html',
            controller: 'DepartmentModalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => department,
            }
        });
    };

    function organizationInfo(department) {
        const dataPromise = dataService.getData(URLS.baseUrl + URLS.organizations + department.organizationId);
        dataPromise.then(function (organization) {
            vm.organization = organization;
            let tabNo = department
            tabNo.organization = vm.organization;
            tabNo.index = department.fullName + ' ' + department.id.substring(0, 3)
            if (vm.tabs.includes(tabNo)) {
                vm.activeTabNo = tabNo;
            } else {
                vm.tabs.push(tabNo);
                vm.activeTabNo = tabNo;
            }
        }).catch(error => console.error(error));
    }

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
    vm.activateTab = function (tabNo) {
        vm.activeTabNo = tabNo;
    };

};