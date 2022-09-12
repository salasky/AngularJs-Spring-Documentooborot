angular
        .module('app').factory('departmentService', function ($http, $q, HTTP_METHOD, URLS) {
        return {
                getDepartments: async function () {
                    return $http({
                        method: HTTP_METHOD.GET,
                        url: URLS.baseUrl + URLS.departments
                    })
/*                const deferred = $q.defer();
                $http({
                    method: HTTP_METHOD.GET,
                    url: URLS.baseUrl + URLS.departments
                })
                    .then(function success(response) {
                        deferred.resolve(response.data);
                    }, function error(response) {
                        deferred.reject(response.status);
                    });
                return deferred.promise;*/
            },
            getDepartment: function (id) {
                const deferred = $q.defer();
                $http({
                    method: HTTP_METHOD.GET,
                    url: URLS.baseUrl + URLS.departments+id
                })
                    .then(function success(response) {
                        deferred.resolve(response.data);
                    }, function error(response) {
                        deferred.reject(response.status);
                    });
                return deferred.promise;
            },
            postDepartment: function (dataForm) {
                return $http({
                    method: HTTP_METHOD.POST,
                    url: URLS.baseUrl + URLS.departmentAdd,
                    data: angular.toJson(dataForm),
                });
            },
            putDepartment: function (dataForm) {
                return $http({
                    method: HTTP_METHOD.PUT,
                    url: URLS.baseUrl + URLS.departmentUpdate,
                    data: angular.toJson(dataForm),
                });
            },
            deleteDepartment: function (id) {
                return $http({
                    method: HTTP_METHOD.DELETE,
                    url: URLS.baseUrl + URLS.departments + id,
                });
            }
        }
    });