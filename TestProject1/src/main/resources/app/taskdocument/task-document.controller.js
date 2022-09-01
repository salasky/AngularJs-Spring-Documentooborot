angular
    .module('app')
    .controller('TaskDocumentController',TaskDocumentController );

function TaskDocumentController ($http, $uibModal, $rootScope) {

            let vm=this;
            _refreshDocuments();

            function _refreshDocuments() {
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/taskdocuments'
                }).then(function successCallback(response) {
                    $rootScope.rootTaskDocuments = response.data;
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            }


            function personInfo(taskDocument) {
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/persons/' + taskDocument.authorId
                }).then(function successCallback(response) {
                    vm.author = response.data;

                    let tabNo = taskDocument;
                    tabNo.author = vm.author;
                    tabNo.index = taskDocument.name + ' ' + taskDocument.id.substring(0, 3)
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



