angular
    .module('app').factory('organizationService', function ($http, $q, HTTP_METHOD, URLS) {
    return {
        getOrganization: function (id) {
            const deferred = $q.defer();
            $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.organizations + id
            })
                .then(function success(response) {
                    deferred.resolve(response.data);
                }, function error(response) {
                    deferred.reject(response.status);
                });
            return deferred.promise;
        },
        getOrganizations: function () {
            const deferred = $q.defer();
            $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.organizations
            })
                .then(function success(response) {
                    deferred.resolve(response.data);
                }, function error(response) {
                    deferred.reject(response.status);
                });
            return deferred.promise;
        },
        postData: function (url, dataForm) {
            return $http({
                method: HTTP_METHOD.POST,
                url: url,
                data: angular.toJson(dataForm),
            });
        },
        putData: function (url, dataForm) {
            return $http({
                method: HTTP_METHOD.PUT,
                url: url,
                data: angular.toJson(dataForm),
            });
        },
        deleteData: function (url) {
            return $http({
                method: HTTP_METHOD.DELETE,
                url: url,
            });
        }
    }
});