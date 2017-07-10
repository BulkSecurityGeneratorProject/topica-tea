(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('AdsTypeDetailController', AdsTypeDetailController);

    AdsTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AdsType'];

    function AdsTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, AdsType) {
        var vm = this;

        vm.adsType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:adsTypeUpdate', function(event, result) {
            vm.adsType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
