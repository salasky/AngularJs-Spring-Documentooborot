angular
    .module('app')
    .controller('OrganizationController', OrganizationController);

function OrganizationController($uibModal, $rootScope, organizationService, $scope) {
    const vm = this;

    vm.$onInit = function () {
        _refreshCustomerData();
    }

    async function _refreshCustomerData() {
        try {
            let response = await organizationService.getOrganizations()
            vm.organizations = response.data;
            $scope.$apply();
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    $scope.$on("refreshOrganizations", function (evt, data) {
        vm.organizations = data;
    });

    vm.openModal = function (organization) {
        return $uibModal.open({
            templateUrl: 'organization/modalWindow.html',
            controller: 'modalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => organization,
            }
        });
    };
};
