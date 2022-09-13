angular
    .module('app').factory('jobService',function ($http, HTTP_METHOD, URLS) {
    return {
        getJobs: async function () {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.jobs
            })
        },
        getJob: function (id) {
            return $http({
                method: HTTP_METHOD.GET,
                url: URLS.baseUrl + URLS.jobs+id
            })
        },
        postJob: function (dataForm) {
            return $http({
                method: HTTP_METHOD.POST,
                url: URLS.baseUrl + URLS.jobAdd,
                data: angular.toJson(dataForm),
            });
        },
        putJob: function (dataForm) {
            return $http({
                method: HTTP_METHOD.PUT,
                url: URLS.baseUrl + URLS.jobUpdate,
                data: angular.toJson(dataForm),
            });
        },
        deleteJob: function (id) {
            return $http({
                method: HTTP_METHOD.DELETE,
                url: URLS.baseUrl + URLS.jobs + id,
            });
        }
    }
});