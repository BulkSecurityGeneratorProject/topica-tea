(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('BrandkeyProductDetailController', BrandkeyProductDetailController);

    BrandkeyProductDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'BrandkeyProduct', 'Brandkey', 'Product'];

    function BrandkeyProductDetailController($scope, $rootScope, $stateParams, previousState, entity, BrandkeyProduct, Brandkey, Product) {
        var vm = this;

        vm.brandkeyProduct = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:brandkeyProductUpdate', function(event, result) {
            vm.brandkeyProduct = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
