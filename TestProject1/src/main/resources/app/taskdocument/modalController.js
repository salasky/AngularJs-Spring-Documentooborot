angular
    .module('app').controller('TaskDocumentModalController', function ($scope, $uibModalInstance, $http, syncData, $rootScope) {

    $scope.data = syncData;
    $scope.taskDocumentForm = {
        id: -1,
        name: "",
        text: "",
        regNumber: "",
        creatingDate: "",
        authorId: "",
        outDate: "",
        execPeriod: "",
        responsibleId: "",
        signOfControl: "",
        controlPersonId: ""
    }

    $scope.persons = [];

    if ($scope.data != undefined) {
        editDocument($scope.data);
    } else {
        addDocument();
    }
    ;

    function editDocument(taskDocument) {
        loadPersonData()
        $scope.taskDocumentForm.id = taskDocument.id;
        $scope.taskDocumentForm.name = taskDocument.name;
        $scope.taskDocumentForm.text = taskDocument.text;
        $scope.taskDocumentForm.regNumber = taskDocument.regNumber;
        $scope.taskDocumentForm.creatingDate = new Date(taskDocument.creatingDate);
        $scope.taskDocumentForm.authorId = taskDocument.authorId;
        $scope.taskDocumentForm.execPeriod = taskDocument.execPeriod;
        $scope.taskDocumentForm.responsibleId = taskDocument.responsibleId;
        $scope.taskDocumentForm.controlPersonId = taskDocument.controlPersonId;
        $scope.taskDocumentForm.signOfControl = taskDocument.signOfControl;
        $scope.taskDocumentForm.outDate = new Date(taskDocument.outDate);
    }

    function addDocument() {
        loadPersonData()
        $scope.taskDocumentForm.id = -1;
        $scope.taskDocumentForm.name = "";
        $scope.taskDocumentForm.text = "";
        $scope.taskDocumentForm.regNumber = "";
        $scope.taskDocumentForm.creatingDate = "";
        $scope.taskDocumentForm.authorId = "";
        $scope.taskDocumentForm.execPeriod = "";
        $scope.taskDocumentForm.responsibleId = "";
        $scope.taskDocumentForm.controlPersonId = "";
        $scope.taskDocumentForm.signOfControl = "";
        $scope.taskDocumentForm.outDate = "";

    }

    function _success(response) {
        _refreshDocuments();
    }

    function _error(response) {
        console.log(response);
        alert($scope.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
    }

    function _refreshDocuments() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/taskdocuments'
        }).then(function successCallback(response) {
            $rootScope.rootTaskDocuments = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.ok = function () {
        $scope.taskDocumentForm.authorId = $scope.myAuthor.id;
        $scope.taskDocumentForm.responsibleId = $scope.myResponsible.id;
        $scope.taskDocumentForm.controlPersonId = $scope.myControlPerson.id;
        var method = "";
        var url = "";
        if ($scope.taskDocumentForm.id == -1) {
            $scope.taskDocumentForm.id = null
            method = "POST";
            url = 'http://localhost:8080/taskdocuments/add';
        } else {
            method = "PUT";
            url = 'http://localhost:8080/taskdocuments/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.taskDocumentForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    $scope.cancel = function () {
        $uibModalInstance.close()
    }

    $scope.deleteTaskDocuments = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/taskdocuments/' + $scope.data.id
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
                if (el.id == $scope.data.responsibleId) {
                    $scope.myResponsible = el;
                }
                if (el.id == $scope.data.controlPersonId) {
                    $scope.myControlPerson = el;
                }
            }
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
});

