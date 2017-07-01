(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventDetailController', EventDetailController);

    EventDetailController.$inject = ['$scope', '$state', '$rootScope', '$stateParams', 'previousState', 'entity', 'Event', 'Question', 'Article'];

    function EventDetailController($scope, $state, $rootScope, $stateParams, previousState, entity, Event, Question, Article) {
        var vm = this;

        vm.event = entity;
        vm.previousState = previousState.name;
        
        vm.directorApprove = directorApprove;
        vm.directorDeny = directorDeny;
        
        function directorApprove() {
        	Event.updateStatus({id : entity.id, status: 'DIRECTOR_APPROVE', type: 0}, onSaveSuccess, onSaveError);
        }
        
        function directorDeny() {
        	Event.updateStatus({id : entity.id, status: 'DIRECTOR_DENY', type: 0}, onSaveSuccess, onSaveError);
        }
        
        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:eventUpdate', result);
            vm.isSaving = false;
            $state.go('event', null, { reload: 'event' });
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:eventUpdate', function(event, result) {
            vm.event = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
