angular
    .module('app')
    .controller('OrganizationController',OrganizationController );

function OrganizationController ( $http, $uibModal, restapi, $rootScope) {
            let vm=this;
            _refreshCustomerData();


            /* Private Methods */

            //HTTP GET- get all organizations collection
            function _refreshCustomerData() {
                restapi.all().then(function (resp) {
                    $rootScope.rootOrganizations = resp;
                });

            }

            function _success(response) {
                _refreshCustomerData();
            }

            function _error(response) {
                console.log(response);
                alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

            }

            vm.openModal = function (organization) {
                let modalInstance = $uibModal.open({
                    templateUrl: 'organization/modalWindow.html',
                    controller: 'modalController as vm',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => organization,
                    }
                });
                return modalInstance;
            };
        };
