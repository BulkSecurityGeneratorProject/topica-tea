(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ChannelGroupDetailController', ChannelGroupDetailController);

    ChannelGroupDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ChannelGroup'];

    function ChannelGroupDetailController($scope, $rootScope, $stateParams, previousState, entity, ChannelGroup) {
        var vm = this;

        vm.channelGroup = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:channelGroupUpdate', function(event, result) {
            vm.channelGroup = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
