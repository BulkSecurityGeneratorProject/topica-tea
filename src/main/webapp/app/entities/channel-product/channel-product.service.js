(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('ChannelProduct', ChannelProduct);

    ChannelProduct.$inject = ['$resource'];

    function ChannelProduct ($resource) {
        var resourceUrl =  'api/channel-products/:id';

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
