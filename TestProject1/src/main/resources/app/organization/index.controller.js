angular
    .module('app')
    .controller('Organization.IndexController', ['$scope', '$http', '$uibModal', 'restapi', '$rootScope',

        function ($scope, $http, $uibModal, restapi, $rootScope) {

            _refreshCustomerData();

            $scope.deleteOrganization = function (organization) {
                $http({
                    method: 'DELETE',
                    url: 'http://localhost:8080/organizations/' + organization.id
                }).then(_success, _error);
            };

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
                alert($scope.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);

            }

            $scope.openModal = function (organization) {
                var modalInstance = $uibModal.open({
                    templateUrl: 'organization/modalWindow.html',
                    controller: 'modalController',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => organization,
                    }
                });
                return modalInstance;
            };
        }])
