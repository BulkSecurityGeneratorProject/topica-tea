(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventLevelPriorityGroupDetailController', EventLevelPriorityGroupDetailController);

    EventLevelPriorityGroupDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'EventLevelPriorityGroup'];

    function EventLevelPriorityGroupDetailController($scope, $rootScope, $stateParams, previousState, entity, EventLevelPriorityGroup) {
        var vm = this;

        vm.eventLevelPriorityGroup = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:eventLevelPriorityGroupUpdate', function(event, result) {
            vm.eventLevelPriorityGroup = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
