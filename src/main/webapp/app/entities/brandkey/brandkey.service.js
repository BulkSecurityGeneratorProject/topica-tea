(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('Brandkey', Brandkey);

    Brandkey.$inject = ['$resource'];

    function Brandkey ($resource) {
        var resourceUrl =  'api/brandkeys/:id';

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
