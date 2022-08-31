angular
    .module('app').controller('OutgoingDocumentModalController', function ($scope, $uibModalInstance, $http, syncData, $rootScope) {

    $scope.data = syncData;
    $scope.outgoingDocumentForm = {
        id: -1,
        name: "",
        text: "",
        regNumber: "",
        creatingDate: "",
        authorId: "",
        senderId: "",
        deliveryType: ""
    }

    $scope.persons = [];
    $scope.deliveryTypes = [{label: 'TELEGRAM',}, {label: 'EMAIL',}, {label: 'ROCKET_CHAT',}, {label: 'MESSENGER',}, {label: 'RUSSIAN_POST'}];
    $scope.myDeliveryType = {
        label: ""
    }

    function selectMyDeliveryType(incomingDocument) {
        for (const el of $scope.deliveryTypes) {
            if (el.label == incomingDocument.deliveryType) {
                $scope.myDeliveryType = el;

            }
        }
    }

    if ($scope.data != undefined) {
        editDocument($scope.data);
    } else {
        addDocument();
    }
    ;

    function editDocument(incomingDocument) {
        loadPersonData()
        selectMyDeliveryType(incomingDocument);
        $scope.outgoingDocumentForm.id = incomingDocument.id;
        $scope.outgoingDocumentForm.name = incomingDocument.name;
        $scope.outgoingDocumentForm.text = incomingDocument.text;
        $scope.outgoingDocumentForm.regNumber = incomingDocument.regNumber;
        $scope.outgoingDocumentForm.creatingDate = new Date(incomingDocument.creatingDate);
        $scope.outgoingDocumentForm.authorId = incomingDocument.authorId;
        $scope.outgoingDocumentForm.senderId = incomingDocument.senderId;
        $scope.outgoingDocumentForm.deliveryType = incomingDocument.deliveryType;

    }

    function addDocument() {
        loadPersonData()
        $scope.outgoingDocumentForm.id = -1;
        $scope.outgoingDocumentForm.name = "";
        $scope.outgoingDocumentForm.text = "";
        $scope.outgoingDocumentForm.regNumber = "";
        $scope.outgoingDocumentForm.creatingDate = "";
        $scope.outgoingDocumentForm.authorId = "";
        $scope.outgoingDocumentForm.senderId = "";
        $scope.outgoingDocumentForm.deliveryType = "";

    }

    function _success(response) {
        _refreshOutgoingDocuments();
    }

    function _error(response) {
        console.log(response);
        alert($scope.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
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

    $scope.ok = function () {
        $scope.outgoingDocumentForm.authorId = $scope.myAuthor.id;
        $scope.outgoingDocumentForm.senderId = $scope.mySender.id;
        $scope.outgoingDocumentForm.deliveryType = $scope.myDeliveryType.label;
        var method = "";
        var url = "";
        if ($scope.outgoingDocumentForm.id == -1) {
            $scope.outgoingDocumentForm.id = null
            method = "POST";
            url = 'http://localhost:8080/outgoingdocuments/add';
        } else {
            method = "PUT";
            url = 'http://localhost:8080/outgoingdocuments/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.outgoingDocumentForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    $scope.cancel = function () {
        $uibModalInstance.close()
    }

    $scope.deleteOutgoingDocuments = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/outgoingdocuments/' + $scope.data.id
        }).then(_success, _error);
    };

    function loadPersonData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/persons'
        }).then(function successCallback(response) {
            $scope.persons = response.data;
            for (const el of $scope.persons) {
                if (el.id == $scope.data.authorId) {
                    $scope.myAuthor = el;
                }
                if (el.id == $scope.data.senderId) {
                    $scope.mySender = el;
                }
            }
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
});

