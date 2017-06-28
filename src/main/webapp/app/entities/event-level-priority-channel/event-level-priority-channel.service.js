(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('EventLevelPriorityChannel', EventLevelPriorityChannel);

    EventLevelPriorityChannel.$inject = ['$resource'];

    function EventLevelPriorityChannel ($resource) {
        var resourceUrl =  'api/event-level-priority-channels/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
