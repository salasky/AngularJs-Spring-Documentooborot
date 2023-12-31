angular.module('app')
    .config(['$stateProvider', '$urlRouterProvider', (function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/");

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'home/index.html',
            })
            .state('test-page', {
                url: '/test-page',
                templateUrl: 'test-page/index.view.html',
                controller: 'TestPage.IndexController',
                controllerAs: 'vm'
            })
            .state('about', {
                url: '/about',
                templateUrl: 'about/aboutIndex.html'
            })
            .state('job', {
                url: '/job',
                templateUrl: 'job/job-index.html',
                controller: 'JobController',
                controllerAs: 'vm'
            })
            .state('organization', {
                url: '/organization',
                templateUrl: 'organization/organization-index.html',
                controller: 'OrganizationController',
                controllerAs: 'vm'
            })
            .state('department', {
                url: '/department',
                templateUrl: 'department/department-index.html',
                controller: 'DepartmentController',
                controllerAs: 'vm'
            })
            .state('person', {
                url: '/person',
                templateUrl: 'person/person-index.html',
                controller: 'PersonController',
                controllerAs: 'vm'
            })
            .state('incomingDocument', {
                url: '/incomingDocument',
                templateUrl: 'incomingdocument/incomingDocument-index.html',
                controller: 'IncomingDocumentController',
                controllerAs: 'vm'
            })
            .state('taskDocument', {
                url: '/taskDocument',
                templateUrl: 'taskdocument/task-document-index.html',
                controller: 'TaskDocumentController',
                controllerAs: 'vm'
            })

            .state('outgoingDocument', {
                url: '/outgoingDocument',
                templateUrl: 'outgoingdocument/outgoing-document-view.html',
                controller: 'OutgoingDocumentController',
                controllerAs: 'vm'
            })
    })])

