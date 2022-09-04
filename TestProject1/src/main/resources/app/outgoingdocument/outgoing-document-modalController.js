angular
    .module('app')
    .controller('OutgoingDocumentModalController', OutgoingDocumentModalController);

function OutgoingDocumentModalController($uibModalInstance, dataService, syncData, $rootScope) {
    const vm = this;
    vm.data = syncData;
    vm.outgoingDocumentForm = {
        id: -1,
        name: "",
        text: "",
        regNumber: "",
        creatingDate: "",
        authorId: "",
        senderId: "",
        deliveryType: ""
    }

    vm.persons = [];
    vm.deliveryTypes = [{label: 'TELEGRAM',}, {label: 'EMAIL',}, {label: 'ROCKET_CHAT',}, {label: 'MESSENGER',}, {label: 'RUSSIAN_POST'}];
    vm.myDeliveryType = {
        label: ""
    }

    function selectMyDeliveryType(incomingDocument) {
        for (const el of vm.deliveryTypes) {
            if (el.label == incomingDocument.deliveryType) {
                vm.myDeliveryType = el;
            }
        }
    }

    if (vm.data) {
        editDocument(vm.data);
    } else {
        addDocument();
    }
    ;

    function editDocument(incomingDocument) {
        loadPersonData()
        selectMyDeliveryType(incomingDocument);
        vm.outgoingDocumentForm.id = incomingDocument.id;
        vm.outgoingDocumentForm.name = incomingDocument.name;
        vm.outgoingDocumentForm.text = incomingDocument.text;
        vm.outgoingDocumentForm.regNumber = incomingDocument.regNumber;
        vm.outgoingDocumentForm.creatingDate = new Date(incomingDocument.creatingDate);
        vm.outgoingDocumentForm.authorId = incomingDocument.authorId;
        vm.outgoingDocumentForm.senderId = incomingDocument.senderId;
        vm.outgoingDocumentForm.deliveryType = incomingDocument.deliveryType;
    }

    function addDocument() {
        loadPersonData()
        vm.outgoingDocumentForm.id = -1;
        vm.outgoingDocumentForm.name = "";
        vm.outgoingDocumentForm.text = "";
        vm.outgoingDocumentForm.regNumber = "";
        vm.outgoingDocumentForm.creatingDate = "";
        vm.outgoingDocumentForm.authorId = "";
        vm.outgoingDocumentForm.senderId = "";
        vm.outgoingDocumentForm.deliveryType = "";
    }

    function _refreshOutgoingDocuments() {
        const dataPromise = dataService.getData('http://localhost:8080/outgoingdocuments');
        dataPromise.then(function (value) {
            $rootScope.rootOutgoingDocuments = value;
        }).catch(error => console.error(error));
    }

    vm.ok = function () {
        vm.outgoingDocumentForm.authorId = vm.myAuthor.id;
        vm.outgoingDocumentForm.senderId = vm.mySender.id;
        vm.outgoingDocumentForm.deliveryType = vm.myDeliveryType.label;
        if (vm.outgoingDocumentForm.id == -1) {
            vm.outgoingDocumentForm.id = null
            dataService.postData('http://localhost:8080/outgoingdocuments/add', vm.outgoingDocumentForm)
                .then(_refreshOutgoingDocuments)
                .catch(error => console.error(error));
            ;
        } else {
            dataService.putData('http://localhost:8080/outgoingdocuments/update', vm.outgoingDocumentForm)
                .then(_refreshOutgoingDocuments)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteOutgoingDocuments = function () {
        dataService.deleteData('http://localhost:8080/outgoingdocuments/' + vm.data.id)
            .then(_refreshOutgoingDocuments)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };

    function loadPersonData() {
        let dataPromise = dataService.getData('http://localhost:8080/persons');
        dataPromise.then(function (value) {
            vm.persons = value;
            for (const el of vm.persons) {
                if (el.id == vm.data.authorId) {
                    vm.myAuthor = el;
                }
                if (el.id == vm.data.senderId) {
                    vm.mySender = el;
                }
            }
        }).catch(error => console.error(error));
    }
};

