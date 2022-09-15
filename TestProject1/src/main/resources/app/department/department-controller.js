angular
    .module('app')
    .controller('DepartmentController', DepartmentController);


function DepartmentController($uibModal, departmentService, organizationService,$scope) {
    const vm = this;
    vm.activeTabNo = 0;
    vm.tabs = [];
    vm.departments = [];

    vm.$onInit = function () {
        _refreshCustomerData()
    }


    async function _refreshCustomerData() {
        try {
            let response  = await departmentService.getDepartments();
            vm.departments= response.data;
            $scope.$apply();
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    $scope.$on("refreshDepartment", function (evt, data) {
        vm.departments = data;
    });

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

    async function organizationInfo(department) {
        try {
            let response = await organizationService.getOrganization(department.organizationId).catch(err=>alert(err.data.errorMessage))
            vm.organization = response.data;
            let tabNo = department
            tabNo.organization = vm.organization;
            tabNo.index = department.fullName + ' ' + department.id.substring(0, 3)
            if (vm.tabs.includes(tabNo)) {
                vm.activeTabNo = tabNo;
            } else {
                vm.tabs.push(tabNo);
                vm.activeTabNo = tabNo;
            }
            $scope.$apply();
        } catch (error) {
            alert(err.data.errorMessage)
        }
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