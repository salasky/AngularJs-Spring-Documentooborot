angular
        .module('app').factory('departmentService',function ($http, HTTP_METHOD, URLS) {
        return {
            getDepartments: async function () {
                return $http({
                    method: HTTP_METHOD.GET,
                    url: URLS.baseUrl + URLS.departments
                })
            },
            getDepartment: function (id) {
                return $http({
                    method: HTTP_METHOD.GET,
                    url: URLS.baseUrl + URLS.departments+id
                })
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