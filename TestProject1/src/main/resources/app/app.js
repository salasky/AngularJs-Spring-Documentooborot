angular.module('app', ['ui.router', 'ngTouch', 'ngAnimate', 'ui.bootstrap'])
    .config(config)
    .run(run);

function config($stateProvider, $urlRouterProvider) {
    // default route
    $urlRouterProvider.otherwise("/");

    // app routes
    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: 'home/index.view.html',
            controller: 'Home.IndexController',
            controllerAs: 'vm'
        })
        .state('test-page', {
            url: '/test-page',
            templateUrl: 'test-page/index.view.html',
            controller: 'TestPage.IndexController',
            controllerAs: 'vm'
        })
        .state('about', {
            url: '/about',
            templateUrl: 'about/index.view.html',
            controller: 'About.IndexController',
            controllerAs: 'vm'
        })
        .state('job', {
            url: '/job',
            templateUrl: 'job/index.view.html',
            controller: 'Job.IndexController',
            controllerAs: 'vm'
        })
        .state('organization', {
            url: '/organization',
            templateUrl: 'organization/index.view.html',
            controller: 'Organization.IndexController',
            controllerAs: 'vm'
        })
        .state('department', {
            url: '/department',
            templateUrl: 'department/index.view.html',
            controller: 'Department.IndexController',
            controllerAs: 'vm'
        })
        .state('person', {
            url: '/person',
            templateUrl: 'person/index.view.html',
            controller: 'Person.IndexController',
            controllerAs: 'vm'
        })
        .state('incomingDocument', {
            url: '/incomingDocument',
            templateUrl: 'incomingdocument/index.view.html',
            controller: 'IncomingDocument.IndexController',
            controllerAs: 'vm'
        })
        .state('taskDocument', {
            url: '/taskDocument',
            templateUrl: 'taskdocument/index.view.html',
            controller: 'TaskDocument.IndexController',
            controllerAs: 'vm'
        })

        .state('outgoingDocument', {
            url: '/outgoingDocument',
            templateUrl: 'outgoingdocument/index.view.html',
            controller: 'OutgoingDocument.IndexController',
            controllerAs: 'vm'
        })
    ;
}

function run($rootScope) {
    $rootScope.moduleName = "app";
    $rootScope.message = "Hello AngularJS";
    $rootScope.rootJobs = [];
    $rootScope.rootOrganizations = [];
    $rootScope.rootDepartments = [];
    $rootScope.rootPersons = [];

    $rootScope.rootIncomingDocuments = [];
    $rootScope.rootOutgoingDocuments = [];
    $rootScope.rootTaskDocuments = [];
};