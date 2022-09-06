angular
    .module('app')
    .controller('IncomingDocumentModalController', IncomingDocumentModalController);

function IncomingDocumentModalController($uibModalInstance, syncData, $rootScope, dataService, URLS) {

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
        vm.incomingDocumentForm.id = -1;
    }

    function _refreshIncomingDocuments() {
        const dataPromise = dataService.getData(URLS.baseUrl + URLS.incomingDocuments);
        dataPromise.then(function (incomingDocuments) {
            $rootScope.rootIncomingDocuments = incomingDocuments;
        }).catch(error => console.error(error));
    }

    vm.ok = function () {
        vm.incomingDocumentForm.authorId = vm.myAuthor.id;
        vm.incomingDocumentForm.senderId = vm.mySender.id;
        vm.incomingDocumentForm.destinationId = vm.myDestination.id;

        if (vm.incomingDocumentForm.id == -1) {
            vm.incomingDocumentForm.id = null
            dataService.postData(URLS.baseUrl + URLS.incomingDocumentAdd, vm.incomingDocumentForm)
                .then(_refreshIncomingDocuments)
                .catch(error => console.error(error));

        } else {
            dataService.putData(URLS.baseUrl + URLS.incomingDocumentUpdate, vm.incomingDocumentForm)
                .then(_refreshIncomingDocuments)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteIncomingDocuments = function () {
        dataService.deleteData(URLS.baseUrl + URLS.incomingDocuments + vm.data.id)
            .then(_refreshIncomingDocuments)
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

