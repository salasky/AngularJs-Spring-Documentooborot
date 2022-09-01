angular
    .module('app').controller('IncomingDocumentModalController', function ($uibModalInstance, $http, syncData, $rootScope) {
    let vm=this;
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

    if (vm.data != undefined) {
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

    function _success(response) {
        _refreshIncomingDocuments();
    }

    function _error(response) {
        console.log(response);
        alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
    }

    function _refreshIncomingDocuments() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/incomingdocuments'
        }).then(function successCallback(response) {
            $rootScope.rootIncomingDocuments = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    vm.ok = function () {
        vm.incomingDocumentForm.authorId = vm.myAuthor.id;
        vm.incomingDocumentForm.senderId = vm.mySender.id;
        vm.incomingDocumentForm.destinationId = vm.myDestination.id;
        let method = "";
        let url = "";
        if (vm.incomingDocumentForm.id == -1) {
            vm.incomingDocumentForm.id = null
            method = "POST";
            url = 'http://localhost:8080/incomingdocuments/add';
        } else {
            method = "PUT";
            url = 'http://localhost:8080/incomingdocuments/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson(vm.incomingDocumentForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteIncomingDocuments = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/incomingdocuments/' + vm.data.id
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
                if (el.id == vm.data.destinationId) {
                    vm.myDestination = el;
                }
            }
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
});

