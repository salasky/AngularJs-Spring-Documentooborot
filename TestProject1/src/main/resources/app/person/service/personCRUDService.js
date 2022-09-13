angular
    .module('app').factory('personService',function ($http, HTTP_METHOD, URLS) {
    return {
        getPersons: async function () {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.persons
            })
        },
        getPerson: function (id) {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.persons+id
            })
        },
        postPerson: function (dataForm) {
            return $http({
                method: HTTP_METHOD.POST,
                url: URLS.baseUrl + URLS.personAdd,
                data: angular.toJson(dataForm),
            });
        },
        putPerson: function (dataForm) {
            return $http({
                method: HTTP_METHOD.PUT,
                url: URLS.baseUrl + URLS.personUpdate,
                data: angular.toJson(dataForm),
            });
        },
        deletePerson: function (id) {
            return $http({
                method: HTTP_METHOD.DELETE,
                url: URLS.baseUrl + URLS.persons + id,
            })
        }
    }
});