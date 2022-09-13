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
        let response = await incomingDocumentService.getIncomingDocuments().catch(err=>alert(err.data.errorMessage))
        $rootScope.$broadcast("refreshIncomingDocuments", response.data);
    }

    vm.ok = async function () {
        vm.incomingDocumentForm.authorId = vm.myAuthor.id;
        vm.incomingDocumentForm.senderId = vm.mySender.id;
        vm.incomingDocumentForm.destinationId = vm.myDestination.id;

        if (vm.incomingDocumentForm.id == -1) {
            vm.incomingDocumentForm.id = null
            await incomingDocumentService.postIncomingDocument(vm.incomingDocumentForm).catch(err=>alert(err.data.errorMessage))
            _refreshIncomingDocuments()

        } else {
            await incomingDocumentService.putIncomingDocument(vm.incomingDocumentForm).catch(err=>alert(err.data.errorMessage))
            _refreshIncomingDocuments()
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteIncomingDocuments = async function () {
        await incomingDocumentService.deleteTaskDocument(vm.data.id).catch(err=>alert(err.data.errorMessage))
        _refreshIncomingDocuments()
        $uibModalInstance.close();
    };

    async function loadPersonData() {
        let responsePersons = await personService.getPersons().catch(err=>alert(err.data.errorMessage))
        vm.persons = responsePersons.data;

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

