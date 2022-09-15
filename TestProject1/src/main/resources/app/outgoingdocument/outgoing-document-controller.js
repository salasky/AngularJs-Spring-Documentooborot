angular
    .module('app')
    .controller('OutgoingDocumentController', OutgoingDocumentController);

function OutgoingDocumentController($compile, $sce, $scope, $uibModal, $rootScope, outgoingDocumentService, personService) {
    const vm = this;
    vm.activeTabNo = 0;
    vm.tabs = [];
    vm.outgoingDocument = "";


    vm.$onInit = function () {
        _refreshCustomerData();
    }
    async function _refreshCustomerData() {
        try {
            let response = await outgoingDocumentService.getOutgoingDocuments()
            vm.outgoingDocuments = response.data;
            $scope.$apply();
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    $scope.$on("refreshOutgoingDocuments", function (evt, data) {
        vm.outgoingDocuments = data;
    });

    async function personInfo(outgoingDocument) {
        try {
            let response = await personService.getPerson(outgoingDocument.authorId)
            vm.author = response.data;
        } catch (error) {
            alert(err.data.errorMessage)
        }
        let tabNo = outgoingDocument;
        tabNo.author = vm.author;
        tabNo.index = outgoingDocument.name + ' ' + outgoingDocument.id.substring(0, 3)
        if (vm.tabs.includes(tabNo)) {
            vm.activeTabNo = tabNo;
        } else {
            vm.tabs.push(tabNo);
            vm.activeTabNo = tabNo;
        }
        $scope.$apply();
    }

    vm.openModal = function (outgoingDocument) {
        return $uibModal.open({
            templateUrl: 'outgoingdocument/modalWindow.html',
            controller: 'OutgoingDocumentModalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => outgoingDocument,
            }
        });
    };

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
    vm.activateTab = function (tabNo) {
        vm.activeTabNo = tabNo;
    };
}


















