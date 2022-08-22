import Home from './js/controllers/HomeController'
import Job from './js/controllers/JobController'

var scotchApp = angular.module('scotchApp', ['ngRoute',Job,Home]);

// configure our routes
scotchApp.config(function($routeProvider) {
    $routeProvider

        // route for the home page
        .when('/', {
            templateUrl : 'home.html',
            controller  : 'HomeController'
        })

        // route for the about page
        .when('/job', {
            templateUrl : 'job.html',
            controller  : 'JobController'
        })

        // route for the about page
        .when('/about', {
            templateUrl : 'about.html',
            controller  : 'aboutController'
        })
});


// create the controller and inject Angular's $scope
scotchApp.controller('mainController', function($scope) {
    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
});

scotchApp.controller('aboutController', function($scope) {
    $scope.message = 'Look! I am an about page.';
});

scotchApp.controller('contactController', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});

export default scotchApp