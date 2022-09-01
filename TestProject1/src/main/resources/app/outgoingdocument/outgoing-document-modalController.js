angular
    .module('app')
    .controller('OutgoingDocumentModalController',OutgoingDocumentModalController );

function OutgoingDocumentModalController ($uibModalInstance, $http, syncData, $rootScope) {
    let vm=this;
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

    if (vm.data != undefined) {
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

    function _success(response) {
        _refreshOutgoingDocuments();
    }

    function _error(response) {
        console.log(response);
        alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
    }

    function _refreshOutgoingDocuments() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/outgoingdocuments'
        }).then(function successCallback(response) {
            $rootScope.rootOutgoingDocuments = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    vm.ok = function () {
        vm.outgoingDocumentForm.authorId = vm.myAuthor.id;
        vm.outgoingDocumentForm.senderId = vm.mySender.id;
        vm.outgoingDocumentForm.deliveryType = vm.myDeliveryType.label;
        let method = "";
        let url = "";
        if (vm.outgoingDocumentForm.id == -1) {
            vm.outgoingDocumentForm.id = null
            method = "POST";
            url = 'http://localhost:8080/outgoingdocuments/add';
        } else {
            method = "PUT";
            url = 'http://localhost:8080/outgoingdocuments/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson(vm.outgoingDocumentForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteOutgoingDocuments = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/outgoingdocuments/' + vm.data.id
        }).then(_success, _error);
        $uibModalInstance.close();
    };

    function loadPersonData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/persons'
        }).then(function successCallback(response) {
            vm.persons = response.data;
            for (const el of vm.persons) {
                if (el.id == vm.data.authorId) {
                    vm.myAuthor = el;
                }
                if (el.id == vm.data.senderId) {
                    vm.mySender = el;
                }
            }
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
};

