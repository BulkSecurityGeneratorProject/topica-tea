(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventLevelPriorityChannelDetailController', EventLevelPriorityChannelDetailController);

    EventLevelPriorityChannelDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'EventLevelPriorityChannel'];

    function EventLevelPriorityChannelDetailController($scope, $rootScope, $stateParams, previousState, entity, EventLevelPriorityChannel) {
        var vm = this;

        vm.eventLevelPriorityChannel = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:eventLevelPriorityChannelUpdate', function(event, result) {
            vm.eventLevelPriorityChannel = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
