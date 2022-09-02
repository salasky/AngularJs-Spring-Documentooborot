angular
    .module('app')
    .controller('OutgoingDocumentController',OutgoingDocumentController );

function OutgoingDocumentController ( $compile, $sce, $window, $uibModal, dataService, $rootScope) {
    let vm=this;
    _refreshOutgoingDocuments();
    function _refreshOutgoingDocuments() {
        let dataPromise =  dataService.getData('http://localhost:8080/outgoingdocuments');
        dataPromise.then(function (value) {
            $rootScope.rootOutgoingDocuments = value;
        }).catch(error => console.error(error));
    }

    function personInfo(outgoingDocument) {
        let dataPromise =  dataService.getData('http://localhost:8080/persons/' + outgoingDocument.authorId);
        dataPromise.then(function (value) {
            vm.author = value;
            let tabNo = outgoingDocument;
            tabNo.author = vm.author;
            tabNo.index = outgoingDocument.name + ' ' + outgoingDocument.id.substring(0, 3)
            if(vm.tabs.includes(tabNo)){
                vm.activeTabNo = tabNo;
            }else{
                vm.tabs.push(tabNo);
                vm.activeTabNo = tabNo;
            }
        }).catch(error => console.error(error));
    };

    vm.openModal = function (outgoingDocument) {
        let modalInstance = $uibModal.open({
            templateUrl: 'outgoingdocument/modalWindow.html',
            controller: 'OutgoingDocumentModalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => outgoingDocument,
            }
        });
        return modalInstance;
    };


    vm.activeTabNo = 0;
    vm.tabs = [];
    vm.outgoingDocument = "";


    vm.info = function (outgoingDocument) {
        personInfo(outgoingDocument);

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


















