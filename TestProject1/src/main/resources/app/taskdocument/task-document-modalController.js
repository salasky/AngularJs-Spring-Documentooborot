angular
    .module('app')
    .controller('TaskDocumentModalController', TaskDocumentModalController);

function TaskDocumentModalController($uibModalInstance, dataService, syncData, $rootScope) {
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
        vm.taskDocumentForm.id = taskDocument.id;
        vm.taskDocumentForm.name = taskDocument.name;
        vm.taskDocumentForm.text = taskDocument.text;
        vm.taskDocumentForm.regNumber = taskDocument.regNumber;
        vm.taskDocumentForm.creatingDate = new Date(taskDocument.creatingDate);
        vm.taskDocumentForm.authorId = taskDocument.authorId;
        vm.taskDocumentForm.execPeriod = taskDocument.execPeriod;
        vm.taskDocumentForm.responsibleId = taskDocument.responsibleId;
        vm.taskDocumentForm.controlPersonId = taskDocument.controlPersonId;
        vm.taskDocumentForm.signOfControl = taskDocument.signOfControl;
        vm.taskDocumentForm.outDate = new Date(taskDocument.outDate);
    }

    function addDocument() {
        loadPersonData()
        vm.taskDocumentForm.id = -1;
        vm.taskDocumentForm.name = "";
        vm.taskDocumentForm.text = "";
        vm.taskDocumentForm.regNumber = "";
        vm.taskDocumentForm.creatingDate = "";
        vm.taskDocumentForm.authorId = "";
        vm.taskDocumentForm.execPeriod = "";
        vm.taskDocumentForm.responsibleId = "";
        vm.taskDocumentForm.controlPersonId = "";
        vm.taskDocumentForm.signOfControl = "";
        vm.taskDocumentForm.outDate = "";
    }


    function _refreshDocuments() {
        const dataPromise = dataService.getData('http://localhost:8080/taskdocuments');
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
            dataService.postData('http://localhost:8080/taskdocuments/add', vm.taskDocumentForm)
                .then(_refreshDocuments)
                .catch(error => console.error(error));
        } else {
            dataService.putData('http://localhost:8080/taskdocuments/update', vm.taskDocumentForm)
                .then(_refreshDocuments)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteTaskDocuments = function () {
        dataService.deleteData('http://localhost:8080/taskdocuments/' + vm.data.id)
            .then(_refreshDocuments)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };

    function loadPersonData() {
        const dataPromise = dataService.getData('http://localhost:8080/persons');
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

