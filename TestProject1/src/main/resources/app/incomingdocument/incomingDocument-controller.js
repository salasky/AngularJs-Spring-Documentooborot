angular
    .module('app')
    .controller('IncomingDocumentController', IncomingDocumentController);

function IncomingDocumentController($uibModal, $rootScope, incomingDocumentService, $scope, personService) {

    const vm = this;
    vm.activeTabNo = 0;
    vm.tabs = [];

    vm.$onInit = function () {
        _refreshCustomerData();
    }
    async function _refreshCustomerData() {
        let response = await incomingDocumentService.getIncomingDocuments();
        vm.incomingDocuments = response.data;
        $scope.$apply();
    }

    $scope.$on("refreshIncomingDocuments", function (evt, data) {
        vm.incomingDocuments = data;
    });

    async function personInfo(incomingDocument) {
        let responsePerson = await personService.getPerson(incomingDocument.authorId);
        vm.author = responsePerson.data;
        let tabNo = incomingDocument;
        tabNo.author = vm.author;
        tabNo.index = incomingDocument.name + ' ' + incomingDocument.id.substring(0, 3)
        if (vm.tabs.includes(tabNo)) {
            vm.activeTabNo = tabNo;
        } else {
            vm.tabs.push(tabNo);
            vm.activeTabNo = tabNo;
        }
        $scope.$apply();
    }

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
    vm.activateTab = function (tabNo) {
        vm.activeTabNo = tabNo;
    };
};



