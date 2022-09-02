angular
    .module('app')
    .controller('IncomingDocumentController',IncomingDocumentController );

function IncomingDocumentController ( $http, $uibModal, $rootScope, dataService) {

            let vm=this;
            _refreshIncomingDocuments();

            function _refreshIncomingDocuments() {
                let dataPromise =  dataService.getData('http://localhost:8080/incomingdocuments');
                dataPromise.then(function (value) {
                    $rootScope.rootIncomingDocuments = value;
                }).catch(error => console.error(error));
            }


            function personInfo(incomingDocument) {
                let dataPromise =  dataService.getData('http://localhost:8080/persons/' + incomingDocument.authorId);
                dataPromise.then(function (value) {
                    vm.author = value;
                    let tabNo = incomingDocument;
                    tabNo.author = vm.author;
                    tabNo.index = incomingDocument.name + ' ' + incomingDocument.id.substring(0, 3)
                    if(vm.tabs.includes(tabNo)){
                        vm.activeTabNo = tabNo;
                    }else{
                        vm.tabs.push(tabNo);
                        vm.activeTabNo = tabNo;
                    }
                }).catch(error => console.error(error));
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



