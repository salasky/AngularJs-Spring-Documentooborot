angular
    .module('app')
    .controller('OutgoingDocumentModalController', OutgoingDocumentModalController);

function OutgoingDocumentModalController($uibModalInstance, dataService, syncData, $rootScope, URLS) {
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

    function selectMyDeliveryType(outgoingDocument) {
        for (const el of vm.deliveryTypes) {
            if (el.label == outgoingDocument.deliveryType) {
                vm.myDeliveryType = el;
            }
        }
    }

    if (vm.data) {
        editDocument(vm.data);
    } else {
        addDocument();
    }

    function editDocument(outgoingDocument) {
        loadPersonData()
        selectMyDeliveryType(outgoingDocument);
        Object.assign(vm.outgoingDocumentForm, outgoingDocument)
        vm.outgoingDocumentForm.creatingDate = new Date(outgoingDocument.creatingDate);
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
        const dataPromise = dataService.getData(URLS.baseUrl + URLS.outgoingDocuments);
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
            dataService.postData(URLS.baseUrl + URLS.outgoingDocumentAdd, vm.outgoingDocumentForm)
                .then(_refreshOutgoingDocuments)
                .catch(error => console.error(error));
            ;
        } else {
            dataService.putData(URLS.baseUrl + URLS.outgoingDocumentUpdate, vm.outgoingDocumentForm)
                .then(_refreshOutgoingDocuments)
                .catch(error => console.error(error));
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteOutgoingDocuments = function () {
        dataService.deleteData(URLS.baseUrl + URLS.outgoingDocuments + vm.data.id)
            .then(_refreshOutgoingDocuments)
            .catch(error => console.error(error));
        $uibModalInstance.close();
    };

    function loadPersonData() {
        let dataPromise = dataService.getData(URLS.baseUrl + URLS.persons);
        dataPromise.then(function (persons) {
            vm.persons = persons;
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

