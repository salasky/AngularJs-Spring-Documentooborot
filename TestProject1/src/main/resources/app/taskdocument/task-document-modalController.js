angular
    .module('app')
    .controller('TaskDocumentModalController',TaskDocumentModalController );

function TaskDocumentModalController ($uibModalInstance, $http, syncData, $rootScope) {
    let vm=this;
    vm.data = syncData;
    vm.taskDocumentForm = {
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

    vm.persons = [];

    if (vm.data != undefined) {
        editDocument(vm.data);
    } else {
        addDocument();
    }
    ;

    function editDocument(taskDocument) {
        loadPersonData()
        vm.taskDocumentForm.id = taskDocument.id;
        vm.taskDocumentForm.name = taskDocument.name;
        vm.taskDocumentForm.text = taskDocument.text;
        vm.taskDocumentForm.regNumber = taskDocument.regNumber;
        vm.taskDocumentForm.creatingDate = new Date(taskDocument.creatingDate);
        vm.taskDocumentForm.authorId = taskDocument.authorId;
        vm.taskDocumentForm.execPeriod = taskDocument.execPeriod;
        vm.taskDocumentForm.responsibleId = taskDocument.responsibleId;
        vm.taskDocumentForm.controlPersonId = taskDocument.controlPersonId;
        vm.taskDocumentForm.signOfControl = taskDocument.signOfControl;
        vm.taskDocumentForm.outDate = new Date(taskDocument.outDate);
    }

    function addDocument() {
        loadPersonData()
        vm.taskDocumentForm.id = -1;
        vm.taskDocumentForm.name = "";
        vm.taskDocumentForm.text = "";
        vm.taskDocumentForm.regNumber = "";
        vm.taskDocumentForm.creatingDate = "";
        vm.taskDocumentForm.authorId = "";
        vm.taskDocumentForm.execPeriod = "";
        vm.taskDocumentForm.responsibleId = "";
        vm.taskDocumentForm.controlPersonId = "";
        vm.taskDocumentForm.signOfControl = "";
        vm.taskDocumentForm.outDate = "";

    }

    function _success(response) {
        _refreshDocuments();
    }

    function _error(response) {
        console.log(response);
        alert(vm.error_message = "Error! " + response.data.errorMessage + response.data.timestamp);
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

    vm.ok = function () {
        vm.taskDocumentForm.authorId = vm.myAuthor.id;
        vm.taskDocumentForm.responsibleId = vm.myResponsible.id;
        vm.taskDocumentForm.controlPersonId = vm.myControlPerson.id;
        let method = "";
        let url = "";
        if (vm.taskDocumentForm.id == -1) {
            vm.taskDocumentForm.id = null
            method = "POST";
            url = 'http://localhost:8080/taskdocuments/add';
        } else {
            method = "PUT";
            url = 'http://localhost:8080/taskdocuments/update';
        }
        $http({
            method: method,
            url: url,
            data: angular.toJson(vm.taskDocumentForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteTaskDocuments = function () {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/taskdocuments/' + vm.data.id
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
                if (el.id == vm.data.responsibleId) {
                    vm.myResponsible = el;
                }
                if (el.id == vm.data.controlPersonId) {
                    vm.myControlPerson = el;
                }
            }
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
};

