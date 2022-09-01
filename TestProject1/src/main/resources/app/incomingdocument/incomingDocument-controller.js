angular
    .module('app')
    .controller('IncomingDocumentController', ['$scope', '$http', '$uibModal', '$rootScope',

        function ($scope, $http, $uibModal, $rootScope) {
            _refreshIncomingDocuments();

            function _refreshIncomingDocuments() {
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/incomingdocuments'
                }).then(function successCallback(response) {
                    $rootScope.rootIncomingDocuments = response.data;
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            }


            function personInfo(incomingDocument) {
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/persons/' + incomingDocument.authorId
                }).then(function successCallback(response) {
                    $scope.author = response.data;

                    let tabNo = incomingDocument;
                    tabNo.author = $scope.author;
                    tabNo.index = incomingDocument.name + ' ' + incomingDocument.id.substring(0, 3)
                    if($scope.tabs.includes(tabNo)){
                        $scope.activeTabNo = tabNo;
                    }else{
                        $scope.tabs.push(tabNo);
                        $scope.activeTabNo = tabNo;

                    }
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            };


            $scope.openModal = function (incomingDocument) {
                let modalInstance = $uibModal.open({
                    templateUrl: 'incomingdocument/modalWindow.html',
                    controller: 'IncomingDocumentModalController',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => incomingDocument,
                    }
                });
                return modalInstance;
            };


            $scope.activeTabNo = 0;
            $scope.tabs = [];

            $scope.info = function (incomingDocument) {
                personInfo(incomingDocument);
            };

            $scope.remove = function (index) {
                if (index === 0) {
                    if ($scope.activeTabNo === $scope.tabs[0]) {
                        $scope.activeTabNo = 0;
                    }
                } else {
                    if ($scope.activeTabNo === $scope.tabs[index]) {
                        $scope.activeTabNo = $scope.tabs[index - 1];
                    }
                }
                $scope.tabs.splice(index, 1);
            };
            $scope.activeTab = function (tabNo) {
                $scope.activeTabNo = tabNo;
            };

        }]);



