(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('Fanpage', Fanpage);

    Fanpage.$inject = ['$resource'];

    function Fanpage ($resource) {
        var resourceUrl =  'api/fanpages/:id';

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
