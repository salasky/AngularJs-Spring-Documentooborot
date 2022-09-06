angular
    .module('app')
    .controller('TaskDocumentModalController', TaskDocumentModalController);

function TaskDocumentModalController($uibModalInstance, dataService, syncData, $rootScope, URLS) {
    const vm = this;
    vm.data = syncData;
    vm.taskDocumentForm = {
        id: -1,
        name: "",
        text: "",
        regNumber: "",
        creatingDate: "",
        authorId: "",
        outDate: "",
        execPeriod: "",
        responsibleId: "",
        signOfControl: "",
        controlPersonId: ""
    }
    vm.persons = [];

    if (vm.data) {
        editDocument(vm.data);
    } else {
        addDocument();
    }

    function editDocument(taskDocument) {
        loadPersonData()
        Object.assign(vm.taskDocumentForm, taskDocument);
        vm.taskDocumentForm.creatingDate = new Date(taskDocument.creatingDate);
        vm.taskDocumentForm.outDate = new Date(taskDocument.outDate);
    }

    function addDocument() {
        loadPersonData()
        vm.taskDocumentForm.id = -1;
    }


    function _refreshDocuments() {
        const dataPromise = dataService.getData(URLS.baseUrl + URLS.taskDocuments);
        dataPromise.then(function (taskDocuments) {
            $rootScope.rootTaskDocuments = taskDocuments;
        }).catch(error => console.error(error));
    }

    vm.ok = function () {
        vm.taskDocumentForm.authorId = vm.myAuthor.id;
        vm.taskDocumentForm.responsibleId = vm.myResponsible.id;
        vm.taskDocumentForm.controlPersonId = vm.myControlPerson.id;
        if (vm.taskDocumentForm.id === -1) {
            vm.taskDocumentForm.id = null
            dataService.postData(URLS.baseUrl + URLS.taskDocumentAdd, vm.taskDocumentForm)
                .then(_refreshDocuments)
                .catch(error => console.error(error));
        } else {
            dataService.putData(URLS.baseUrl + URLS.taskDocumentUpdate, vm.taskDocumentForm)
                .then(_refreshDocuments)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteTaskDocuments = function () {
        dataService.deleteData(URLS.baseUrl + URLS.taskDocuments + vm.data.id)
            .then(_refreshDocuments)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };

    function loadPersonData() {
        const dataPromise = dataService.getData(URLS.baseUrl + URLS.persons);
        dataPromise.then(function (persons) {
            vm.persons = persons;
            for (const el of vm.persons) {
                if (el.id == vm.data.authorId) {
                    vm.myAuthor = el;
                }
                if (el.id == vm.data.responsibleId) {
                    vm.myResponsible = el;
                }
                if (el.id == vm.data.controlPersonId) {
                    vm.myControlPerson = el;
                }
            }
        }).catch(error => console.error(error));
    }
}

