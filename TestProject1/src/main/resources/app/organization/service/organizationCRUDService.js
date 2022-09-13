angular
    .module('app').factory('organizationService',function ($http, HTTP_METHOD, URLS) {
    return {
        getOrganizations: async function () {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.organizations
            })
        },
        getOrganization: function (id) {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.organizations+id
            })
        },
        postOrganization: function (dataForm) {
            return $http({
                method: HTTP_METHOD.POST,
                url: URLS.baseUrl + URLS.organizationAdd,
                data: angular.toJson(dataForm),
            });
        },
        putOrganization: function (dataForm) {
            return $http({
                method: HTTP_METHOD.PUT,
                url: URLS.baseUrl + URLS.organizationUpdate,
                data: angular.toJson(dataForm),
            });
        },
        deleteOrganization: function (id) {
            return $http({
                method: HTTP_METHOD.DELETE,
                url: URLS.baseUrl + URLS.organizations + id,
            });
        }
    }
});