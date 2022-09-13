angular
    .module('app')
    .controller('OutgoingDocumentModalController', OutgoingDocumentModalController);

function OutgoingDocumentModalController($uibModalInstance, outgoingDocumentService, syncData, $rootScope, personService) {
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
    }
    async function _refreshOutgoingDocuments() {
        let response = await outgoingDocumentService.getOutgoingDocuments();
        $rootScope.$broadcast("refreshOutgoingDocuments", response.data);
    }


    vm.ok = async function () {
        vm.outgoingDocumentForm.authorId = vm.myAuthor.id;
        vm.outgoingDocumentForm.senderId = vm.mySender.id;
        vm.outgoingDocumentForm.deliveryType = vm.myDeliveryType.label;
        if (vm.outgoingDocumentForm.id == -1) {
            vm.outgoingDocumentForm.id = null
            await outgoingDocumentService.postOutgoingDocument(vm.outgoingDocumentForm).catch(err=>alert(err.data.errorMessage))
            _refreshOutgoingDocuments()
        } else {
            await outgoingDocumentService.putOutgoingDocument(vm.outgoingDocumentForm).catch(err=>alert(err.data.errorMessage))
            _refreshOutgoingDocuments()
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteOutgoingDocuments = async function () {
        await outgoingDocumentService.deleteOutgoingDocument(vm.data.id).catch(err=>alert(err.data.errorMessage))
        _refreshOutgoingDocuments()
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
            }
        }
    }
};

