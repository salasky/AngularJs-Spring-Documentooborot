angular
    .module('app')
    .controller('TaskDocumentController',TaskDocumentController );

function TaskDocumentController (dataService, $uibModal, $rootScope) {

            let vm=this;
            _refreshDocuments();

            function _refreshDocuments() {
                let dataPromise =  dataService.getData('http://localhost:8080/taskdocuments');
                dataPromise.then(function (value) {
                    $rootScope.rootTaskDocuments = value;
                }).catch(error => console.error(error));
            }

            function personInfo(taskDocument) {
                let dataPromise =  dataService.getData('http://localhost:8080/persons/' + taskDocument.authorId);
                dataPromise.then(function (value) {
                    vm.author = value;
                    let tabNo = taskDocument;
                    tabNo.author = vm.author;
                    tabNo.index = taskDocument.name + ' ' + taskDocument.id.substring(0, 3)
                    if(vm.tabs.includes(tabNo)){
                        vm.activeTabNo = tabNo;
                    }else{
                        vm.tabs.push(tabNo);
                        vm.activeTabNo = tabNo;
                    }
                }).catch(error => console.error(error));
            };


            vm.openModal = function (taskDocument) {
                let modalInstance = $uibModal.open({
                    templateUrl: 'taskdocument/modalWindow.html',
                    controller: 'TaskDocumentModalController as vm',
                    backdrop: false,
                    size: 'm',
                    animation: true,
                    resolve: {
                        syncData: () => taskDocument,
                    }
                });
                return modalInstance;
            };


            vm.activeTabNo = 0;
            vm.tabs = [];


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
            vm.activeTab = function (tabNo) {
                vm.activeTabNo = tabNo;
            };

        };



