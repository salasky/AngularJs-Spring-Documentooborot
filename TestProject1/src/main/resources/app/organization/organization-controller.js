angular
    .module('app')
    .controller('OrganizationController', OrganizationController);

function OrganizationController($uibModal, dataService, $rootScope, URLS) {
    const vm = this;
    _refreshCustomerData();

    function _refreshCustomerData() {
        const dataPromise = dataService.getData(URLS.baseUrl+URLS.organizations);
        dataPromise.then(function (value) {
            $rootScope.rootOrganizations = value;
        }).catch(error => console.error(error));
    }

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
