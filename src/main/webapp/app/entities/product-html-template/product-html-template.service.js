(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('ProductHtmlTemplate', ProductHtmlTemplate);

    ProductHtmlTemplate.$inject = ['$resource'];

    function ProductHtmlTemplate ($resource) {
        var resourceUrl =  'api/product-html-templates/:id';

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
