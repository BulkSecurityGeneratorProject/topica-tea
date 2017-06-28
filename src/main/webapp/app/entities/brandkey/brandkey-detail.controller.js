(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('BrandkeyDetailController', BrandkeyDetailController);

    BrandkeyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Brandkey'];

    function BrandkeyDetailController($scope, $rootScope, $stateParams, previousState, entity, Brandkey) {
        var vm = this;

        vm.brandkey = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:brandkeyUpdate', function(event, result) {
            vm.brandkey = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
