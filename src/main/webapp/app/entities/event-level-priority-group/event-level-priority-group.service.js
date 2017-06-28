(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('EventLevelPriorityGroup', EventLevelPriorityGroup);

    EventLevelPriorityGroup.$inject = ['$resource'];

    function EventLevelPriorityGroup ($resource) {
        var resourceUrl =  'api/event-level-priority-groups/:id';

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
