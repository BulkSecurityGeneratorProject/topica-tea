(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('Event', Event);

    Event.$inject = ['$resource', 'DateUtils'];

    function Event ($resource, DateUtils) {
        var resourceUrl =  'api/events/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.schedule = DateUtils.convertDateTimeFromServer(data.schedule);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },
            'queryUserWithRole': {
            	url : 'api/users-role/:role',
            	method:'GET',
            	isArray: true
            },
            'distribute': {
            	url : resourceUrl + '/:userId',
            	method:'GET'
            },
            'editor': {
            	url : 'api/events/editor',
            	method:'POST'
            },
            'saveHotEvent': {
            	url : 'api/event-hot',
            	method:'POST'
            },
            'updateStatus': {
            	url : resourceUrl + '/updateStatus/:status/:type',
            	method:'GET'
            },
        });
    }
})();
