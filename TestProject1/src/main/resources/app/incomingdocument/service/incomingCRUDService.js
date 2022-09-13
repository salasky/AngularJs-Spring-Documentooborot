angular
    .module('app').factory('incomingDocumentService',function ($http, HTTP_METHOD, URLS) {
    return {
        getIncomingDocuments: async function () {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.incomingDocuments
            })
        },
        getIncomingDocument: function (id) {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.incomingDocuments+id
            })
        },
        postIncomingDocument: function (dataForm) {
            return $http({
                method: HTTP_METHOD.POST,
                url: URLS.baseUrl + URLS.incomingDocumentAdd,
                data: angular.toJson(dataForm),
            });
        },
        putIncomingDocument: function (dataForm) {
            return $http({
                method: HTTP_METHOD.PUT,
                url: URLS.baseUrl + URLS.incomingDocumentUpdate,
                data: angular.toJson(dataForm),
            });
        },
        deleteIncomingDocument: function (id) {
            return $http({
                method: HTTP_METHOD.DELETE,
                url: URLS.baseUrl + URLS.incomingDocuments + id,
            });
        }
    }
});