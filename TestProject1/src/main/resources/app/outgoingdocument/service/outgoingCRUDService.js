angular
    .module('app').factory('outgoingDocumentService',function ($http, HTTP_METHOD, URLS) {
    return {
        getOutgoingDocuments: async function () {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.outgoingDocuments
            })
        },
        getOutgoingDocument: function (id) {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.outgoingDocuments+id
            })
        },
        postOutgoingDocument: function (dataForm) {
            return $http({
                method: HTTP_METHOD.POST,
                url: URLS.baseUrl + URLS.outgoingDocumentAdd,
                data: angular.toJson(dataForm),
            });
        },
        putOutgoingDocument: function (dataForm) {
            return $http({
                method: HTTP_METHOD.PUT,
                url: URLS.baseUrl + URLS.outgoingDocumentUpdate,
                data: angular.toJson(dataForm),
            });
        },
        deleteOutgoingDocument: function (id) {
            return $http({
                method: HTTP_METHOD.DELETE,
                url: URLS.baseUrl + URLS.outgoingDocuments + id,
            });
        }
    }
});