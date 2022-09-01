angular
    .module('app')
    .controller('IncomingDocumentController',IncomingDocumentController );

function IncomingDocumentController ( $http, $uibModal, $rootScope) {

            let vm=this;
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
                    vm.author = response.data;

                    let tabNo = incomingDocument;
                    tabNo.author = vm.author;
                    tabNo.index = incomingDocument.name + ' ' + incomingDocument.id.substring(0, 3)
                    if(vm.tabs.includes(tabNo)){
                        vm.activeTabNo = tabNo;
                    }else{
                        vm.tabs.push(tabNo);
                        vm.activeTabNo = tabNo;
                    }
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            };


            vm.openModal = function (incomingDocument) {
                let modalInstance = $uibModal.open({
                    templateUrl: 'incomingdocument/modalWindow.html',
                    controller: 'IncomingDocumentModalController as vm',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => incomingDocument,
                    }
                });
                return modalInstance;
            };


            vm.activeTabNo = 0;
            vm.tabs = [];

            vm.info = function (incomingDocument) {
                personInfo(incomingDocument);
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
            vm.activeTab = function (tabNo) {
                vm.activeTabNo = tabNo;
            };
        };



