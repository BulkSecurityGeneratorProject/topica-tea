(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('ChannelGroup', ChannelGroup);

    ChannelGroup.$inject = ['$resource'];

    function ChannelGroup ($resource) {
        var resourceUrl =  'api/channel-groups/:id';

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
