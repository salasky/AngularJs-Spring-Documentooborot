angular
    .module('app').controller('IncomingDocumentModalController', function ($scope, $uibModalInstance, $http, syncData, $rootScope) {

    $scope.data = syncData;
    $scope.incomingDocumentForm = {
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
    $scope.persons = [];

    if ($scope.data != undefined) {
        editDocument($scope.data);
    } else {
        addDocument();
    }
    ;

    function editDocument(incomingDocument) {
        loadPersonData()
        $scope.incomingDocumentForm.id = incomingDocument.id;
        $scope.incomingDocumentForm.name = incomingDocument.name;
        $scope.incomingDocumentForm.text = incomingDocument.text;
        $scope.incomingDocumentForm.regNumber = incomingDocument.regNumber;
        $scope.incomingDocumentForm.creatingDate = new Date(incomingDocument.creatingDate);
        $scope.incomingDocumentForm.authorId = incomingDocument.authorId;
        $scope.incomingDocumentForm.senderId = incomingDocument.senderId;
        $scope.incomingDocumentForm.destinationId = incomingDocument.destinationId;
        $scope.incomingDocumentForm.number = incomingDocument.number;
        $scope.incomingDocumentForm.dateOfRegistration = new Date(incomingDocument.dateOfRegistration);
    }

    function addDocument() {
        loadPersonData()
        $scope.incomingDocumentForm.id = -1;
        $scope.incomingDocumentForm.name = "";
        $scope.incomingDocumentForm.text = "";
        $scope.incomingDocumentForm.regNumber = "";
        $scope.incomingDocumentForm.creatingDate = "";
        $scope.incomingDocumentForm.authorId = "";
        $scope.incomingDocumentForm.senderId = "";
        $scope.incomingDocumentForm.destinationId = "";
        $scope.incomingDocumentForm.number = "";
        $scope.incomingDocumentForm.dateOfRegistration = "";
    }

    function _success(response) {
        _refreshIncomingDocuments();
    }

    function _error(response) {
        console.log(response);
        alert($scope.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
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

    $scope.ok = function () {
        $scope.incomingDocumentForm.authorId = $scope.myAuthor.id;
        $scope.incomingDocumentForm.senderId = $scope.mySender.id;
        $scope.incomingDocumentForm.destinationId = $scope.myDestination.id;
        let method = "";
        let url = "";
        if ($scope.incomingDocumentForm.id == -1) {
            $scope.incomingDocumentForm.id = null
            method = "POST";
            url = 'http://localhost:8080/incomingdocuments/add';
        } else {
            method = "PUT";
            url = 'http://localhost:8080/incomingdocuments/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.incomingDocumentForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    $scope.cancel = function () {
        $uibModalInstance.close()
    }

    $scope.deleteIncomingDocuments = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/incomingdocuments/' + $scope.data.id
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
                if (el.id == $scope.data.destinationId) {
                    $scope.myDestination = el;
                }
            }
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
});

