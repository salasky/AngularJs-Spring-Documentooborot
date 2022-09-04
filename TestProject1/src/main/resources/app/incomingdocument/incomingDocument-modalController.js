angular
    .module('app')
    .controller('IncomingDocumentModalController', IncomingDocumentModalController);

function IncomingDocumentModalController($uibModalInstance, syncData, $rootScope, dataService) {

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
    ;

    function editDocument(incomingDocument) {
        loadPersonData()
        vm.incomingDocumentForm.id = incomingDocument.id;
        vm.incomingDocumentForm.name = incomingDocument.name;
        vm.incomingDocumentForm.text = incomingDocument.text;
        vm.incomingDocumentForm.regNumber = incomingDocument.regNumber;
        vm.incomingDocumentForm.creatingDate = new Date(incomingDocument.creatingDate);
        vm.incomingDocumentForm.authorId = incomingDocument.authorId;
        vm.incomingDocumentForm.senderId = incomingDocument.senderId;
        vm.incomingDocumentForm.destinationId = incomingDocument.destinationId;
        vm.incomingDocumentForm.number = incomingDocument.number;
        vm.incomingDocumentForm.dateOfRegistration = new Date(incomingDocument.dateOfRegistration);
    }

    function addDocument() {
        loadPersonData()
        vm.incomingDocumentForm.id = -1;
        vm.incomingDocumentForm.name = "";
        vm.incomingDocumentForm.text = "";
        vm.incomingDocumentForm.regNumber = "";
        vm.incomingDocumentForm.creatingDate = "";
        vm.incomingDocumentForm.authorId = "";
        vm.incomingDocumentForm.senderId = "";
        vm.incomingDocumentForm.destinationId = "";
        vm.incomingDocumentForm.number = "";
        vm.incomingDocumentForm.dateOfRegistration = "";
    }

    function _refreshIncomingDocuments() {
        const dataPromise = dataService.getData('http://localhost:8080/incomingdocuments');
        dataPromise.then(function (value) {
            $rootScope.rootIncomingDocuments = value;
        }).catch(error => console.error(error));
    }

    vm.ok = function () {
        vm.incomingDocumentForm.authorId = vm.myAuthor.id;
        vm.incomingDocumentForm.senderId = vm.mySender.id;
        vm.incomingDocumentForm.destinationId = vm.myDestination.id;

        if (vm.incomingDocumentForm.id == -1) {
            vm.incomingDocumentForm.id = null
            dataService.postData('http://localhost:8080/incomingdocuments/add', vm.incomingDocumentForm)
                .then(_refreshIncomingDocuments)
                .catch(error => console.error(error));

        } else {
            dataService.putData('http://localhost:8080/incomingdocuments/update', vm.incomingDocumentForm)
                .then(_refreshIncomingDocuments)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteIncomingDocuments = function () {
        dataService.deleteData('http://localhost:8080/incomingdocuments/' + vm.data.id)
            .then(_refreshIncomingDocuments)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };

    function loadPersonData() {
        const dataPromise = dataService.getData('http://localhost:8080/persons');
        dataPromise.then(function (value) {
            vm.persons = value;
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
        }).catch(error => console.error(error));
    }
}

