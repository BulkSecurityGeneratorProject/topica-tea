(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('HtmlTemplate', HtmlTemplate);

    HtmlTemplate.$inject = ['$resource'];

    function HtmlTemplate ($resource) {
        var resourceUrl =  'api/html-templates/:id';

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
