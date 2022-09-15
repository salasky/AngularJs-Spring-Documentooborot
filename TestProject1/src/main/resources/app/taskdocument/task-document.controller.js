angular
    .module('app')
    .controller('TaskDocumentController', TaskDocumentController);

function TaskDocumentController(taskDocumentService, $uibModal, $rootScope, $scope, personService) {
    const vm = this;
    vm.activeTabNo = 0;
    vm.tabs = [];


    vm.$onInit = function () {
        _refreshCustomerData();
    }
    async function _refreshCustomerData() {
        try {
            let response = await taskDocumentService.getTaskDocuments()
            vm.taskDocuments = response.data;
            $scope.$apply();
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    $scope.$on("refreshTaskDocuments", function (evt, data) {
        vm.taskDocuments = data;
    });

    async function personInfo(taskDocument) {
        try {
            let responsePerson = await personService.getPerson(taskDocument.authorId)
            vm.author = responsePerson.data;
        } catch (error) {
            alert(err.data.errorMessage)
        }

        let tabNo = taskDocument;
        tabNo.author = vm.author;
        tabNo.index = taskDocument.name + ' ' + taskDocument.id.substring(0, 3)
        if (vm.tabs.includes(tabNo)) {
            vm.activeTabNo = tabNo;
        } else {
            vm.tabs.push(tabNo);
            vm.activeTabNo = tabNo;
        }
        $scope.$apply();
    }

    vm.openModal = function (taskDocument) {
        return $uibModal.open({
            templateUrl: 'taskdocument/modalWindow.html',
            controller: 'TaskDocumentModalController as vm',
            backdrop: false,
            size: 'm',
            animation: true,
            resolve: {
                syncData: () => taskDocument,
            }
        });
    };

    vm.info = function (taskDocument) {
        personInfo(taskDocument);
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



