angular
    .module('app')
    .controller('OrganizationController', OrganizationController);

function OrganizationController($uibModal, dataService, $rootScope) {
    let vm = this;
    _refreshCustomerData();

    //HTTP GET- get all organizations collection
    function _refreshCustomerData() {
        let dataPromise = dataService.getData('http://localhost:8080/organizations');
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
