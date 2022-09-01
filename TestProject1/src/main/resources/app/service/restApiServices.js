app.factory('dataService', function ($http, $q) {
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
        deleteData: function (url) {
            let deferred = $q.defer();
            $http({method: 'DELETE', url: url});
        }
    }
});