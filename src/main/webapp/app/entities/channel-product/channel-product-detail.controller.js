(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ChannelProductDetailController', ChannelProductDetailController);

    ChannelProductDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ChannelProduct', 'Product', 'AdsType'];

    function ChannelProductDetailController($scope, $rootScope, $stateParams, previousState, entity, ChannelProduct, Product, AdsType) {
        var vm = this;

        vm.channelProduct = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:channelProductUpdate', function(event, result) {
            vm.channelProduct = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
