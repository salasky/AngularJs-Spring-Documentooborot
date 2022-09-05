angular.module('app').constant('URLS', {
    baseUrl: 'http://localhost:8080/',
    departments: 'departments/',
    departmentAdd:'departments/add',
    departmentUpdate:'departments/update',

    taskDocuments:'taskdocuments/',
    taskDocumentAdd:'taskdocuments/add',
    taskDocumentUpdate:'taskdocuments/update',

    incomingDocuments:'incomingdocuments/',
    incomingDocumentAdd:'incomingdocuments/add',
    incomingDocumentUpdate:'incomingdocuments/update',

    outgoingDocuments:'outgoingdocuments/',
    outgoingDocumentAdd:'outgoingdocuments/add',
    outgoingDocumentUpdate:'outgoingdocuments/update',

    organizations:'organizations/',
    organizationAdd:'organizations/add',
    organizationUpdate:'organizations/update',

    persons:'persons/',
    personAdd:'persons/add',
    personUpdate:'persons/update',

    jobs:'jobs/',
    jobAdd:'jobs/add',
    jobUpdate:'jobs/update',

})
    .constant('HTTP_METHOD', {
        GET: 'GET',
        POST:'POST',
        PUT:'PUT',
        DELETE:'DELETE'
    });