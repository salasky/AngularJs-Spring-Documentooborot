angular
    .module('app')
    .controller('TaskDocumentModalController', TaskDocumentModalController);

function TaskDocumentModalController($uibModalInstance, taskDocumentService, syncData, $rootScope, personService) {
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
        loadPersonData();
    }


    async function _refreshDocuments() {
        try {
            let response = await taskDocumentService.getTaskDocuments()
            $rootScope.$broadcast("refreshTaskDocuments", response.data);
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    vm.ok = async function () {
        vm.taskDocumentForm.authorId = vm.myAuthor.id;
        vm.taskDocumentForm.responsibleId = vm.myResponsible.id;
        vm.taskDocumentForm.controlPersonId = vm.myControlPerson.id;
        if (vm.taskDocumentForm.id === -1) {
            vm.taskDocumentForm.id = null
            try {
                await taskDocumentService.postTaskDocument(vm.taskDocumentForm)
                _refreshDocuments()
            } catch (error) {
                alert(err.data.errorMessage)
            }
        } else {
            try {
                await taskDocumentService.putTaskDocument(vm.taskDocumentForm)
                _refreshDocuments()
            } catch (error) {
                alert(err.data.errorMessage)
            }
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteTaskDocuments = async function () {
        try {
            await taskDocumentService.deleteTaskDocument(vm.data.id)
            _refreshDocuments()
        } catch (error) {
            alert(err.data.errorMessage)
        }
        $uibModalInstance.close();
    };

    async function loadPersonData() {
        try {
            let responsePersons = await personService.getPersons()
            vm.persons = responsePersons.data;
        } catch (error) {
            alert(err.data.errorMessage)
        }
        if (vm.data) {
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
        }
    }
}

