(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventEditorController', EventEditorController);

    EventEditorController.$inject = ['$scope', '$state', '$rootScope', '$stateParams', 'previousState', 'entity', 'Event', 'Question', 'Article'];

    function EventEditorController($scope, $state, $rootScope, $stateParams, previousState, entity, Event, Question, Article) {
        var vm = this;

        vm.event = entity;
        vm.previousState = previousState.name;
        vm.content;
        vm.editor = editor;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:eventUpdate', function(event, result) {
            vm.event = result;
        });
        $scope.$on('$destroy', unsubscribe);
        
        function editor() {
        	// Update field
        	entity.content = vm.content;
        	
        	console.log("editor: " + angular.toJson(entity));
            Event.editor(entity, onSaveSuccess, onSaveError);
        }
        
        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:eventUpdate', result);
//            $uibModalInstance.close(result);
            vm.isSaving = false;
            $state.go('event', null, { reload: 'event' });
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    }
})();
