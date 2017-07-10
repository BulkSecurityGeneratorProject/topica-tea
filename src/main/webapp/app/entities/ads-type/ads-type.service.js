(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('AdsType', AdsType);

    AdsType.$inject = ['$resource'];

    function AdsType ($resource) {
        var resourceUrl =  'api/ads-types/:id';

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
