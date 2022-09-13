angular
    .module('app').factory('taskDocumentService',function ($http, HTTP_METHOD, URLS) {
    return {
        getTaskDocuments: async function () {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.taskDocuments
            })
        },
        getTaskDocument: function (id) {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.taskDocuments+id
            })
        },
        postTaskDocument: function (dataForm) {
            return $http({
                method: HTTP_METHOD.POST,
                url: URLS.baseUrl + URLS.taskDocumentAdd,
                data: angular.toJson(dataForm),
            });
        },
        putTaskDocument: function (dataForm) {
            return $http({
                method: HTTP_METHOD.PUT,
                url: URLS.baseUrl + URLS.taskDocumentUpdate,
                data: angular.toJson(dataForm),
            });
        },
        deleteTaskDocument: function (id) {
            return $http({
                method: HTTP_METHOD.DELETE,
                url: URLS.baseUrl + URLS.taskDocuments + id,
            });
        }
    }
});