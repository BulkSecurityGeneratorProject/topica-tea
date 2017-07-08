(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('FanpageDetailController', FanpageDetailController);

    FanpageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fanpage'];

    function FanpageDetailController($scope, $rootScope, $stateParams, previousState, entity, Fanpage) {
        var vm = this;

        vm.fanpage = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:fanpageUpdate', function(event, result) {
            vm.fanpage = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
