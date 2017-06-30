(function() {
    'use strict';
    angular
        .module('topicaEventAmplifyApp')
        .factory('BrandkeyProduct', BrandkeyProduct);

    BrandkeyProduct.$inject = ['$resource'];

    function BrandkeyProduct ($resource) {
        var resourceUrl =  'api/brandkey-products/:id';

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
            'update': { method:'PUT' },
            'saveAll': {
            	url : 'api/brandkey-products/save-all',
            	method:'POST',
            	isArray: true
            },
        });
    }
})();
