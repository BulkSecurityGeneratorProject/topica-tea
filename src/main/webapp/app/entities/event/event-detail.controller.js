(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventDetailController', EventDetailController);

    EventDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Event', 'Question', 'Article'];

    function EventDetailController($scope, $rootScope, $stateParams, previousState, entity, Event, Question, Article) {
        var vm = this;

        vm.event = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:eventUpdate', function(event, result) {
            vm.event = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
