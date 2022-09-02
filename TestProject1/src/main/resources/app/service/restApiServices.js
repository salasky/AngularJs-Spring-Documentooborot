angular
    .module('app').factory('dataService', function ($http, $q) {
    return {
        getData: function (url) {
            let deferred = $q.defer();
            $http({method: 'GET', url: url})
                .then(function success(response) {
                    deferred.resolve(response.data);
                }, function error(response) {
                    deferred.reject(response.status);
                });
            return deferred.promise;
        },
        postData: function (url,dataForm) {
           return  $http({
                method: "POST",
                url: url,
                data: angular.toJson(dataForm),
                headers: {
                    'Content-Type': 'application/json'
                }
            });
        },
        putData: function (url,dataForm) {
            return  $http({
                method: "PUT",
                url: url,
                data: angular.toJson(dataForm),
                headers: {
                    'Content-Type': 'application/json'
                }
            });},
        deleteData: function (url) {
            return  $http({
                method: "DELETE",
                url: url,
                headers: {
                    'Content-Type': 'application/json'
                }
            });}
    }
});