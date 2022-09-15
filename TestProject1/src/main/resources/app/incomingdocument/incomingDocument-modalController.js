angular
    .module('app')
    .controller('IncomingDocumentModalController', IncomingDocumentModalController);

function IncomingDocumentModalController($uibModalInstance, syncData, $rootScope, incomingDocumentService, personService) {

    const vm = this;
    vm.data = syncData;
    vm.incomingDocumentForm = {
        id: -1,
        name: "",
        text: "",
        regNumber: "",
        creatingDate: "",
        authorId: "",
        senderId: "",
        destinationId: "",
        number: "",
        dateOfRegistration: ""
    }
    vm.persons = [];

    if (vm.data) {
        editDocument(vm.data);
    } else {
        addDocument();
    }

    function editDocument(incomingDocument) {
        loadPersonData()
        Object.assign(vm.incomingDocumentForm, incomingDocument);
        vm.incomingDocumentForm.creatingDate = new Date(incomingDocument.creatingDate);
        vm.incomingDocumentForm.dateOfRegistration = new Date(incomingDocument.dateOfRegistration);
    }

    function addDocument() {
        loadPersonData()
    }

    async function _refreshIncomingDocuments() {
        try {
            let response = await incomingDocumentService.getIncomingDocuments()
            $rootScope.$broadcast("refreshIncomingDocuments", response.data);
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }

    vm.ok = async function () {
        vm.incomingDocumentForm.authorId = vm.myAuthor.id;
        vm.incomingDocumentForm.senderId = vm.mySender.id;
        vm.incomingDocumentForm.destinationId = vm.myDestination.id;

        if (vm.incomingDocumentForm.id == -1) {
            vm.incomingDocumentForm.id = null
            try {
                await incomingDocumentService.postIncomingDocument(vm.incomingDocumentForm)
                _refreshIncomingDocuments()
            } catch (error) {
                alert(err.data.errorMessage)
            }
        } else {
            try {
                await incomingDocumentService.putIncomingDocument(vm.incomingDocumentForm)
                _refreshIncomingDocuments()
            } catch (error) {
                alert(err.data.errorMessage)
            }
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteIncomingDocuments = async function () {
        try {
            await incomingDocumentService.deleteTaskDocument(vm.data.id)
            _refreshIncomingDocuments()
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
                if (el.id == vm.data.senderId) {
                    vm.mySender = el;
                }
                if (el.id == vm.data.destinationId) {
                    vm.myDestination = el;
                }
            }
        }
    }
}

