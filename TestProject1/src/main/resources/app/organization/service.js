(function () {
    /* Services */

    angular.module('app')
        .factory('restapi', ['$http', function ($http) {
            let myJson = {};
            myJson.get = $http({
                method: 'GET',
                url: 'http://localhost:8080/organizations'
            }).then(function successCallback(response) {
                return response.data;
            }, function errorCallback(response) {
                console.log(response.statusText);
            });
            myJson.all = function () {
                return myJson.get;
            };

            return myJson;
        }])

}());

