angular.module('app', ['ui.router', 'ngTouch', 'ngAnimate', 'ui.bootstrap'])
    .run(run);

function run($rootScope) {
    $rootScope.rootJobs = [];
    $rootScope.rootOrganizations = [];
    $rootScope.rootDepartments = [];
    $rootScope.rootPersons = [];
    $rootScope.rootIncomingDocuments = [];
    $rootScope.rootOutgoingDocuments = [];
    $rootScope.rootTaskDocuments = [];
};


