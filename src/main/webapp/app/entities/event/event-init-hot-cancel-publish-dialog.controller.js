(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventInitHotCancelPublishController',EventInitHotCancelPublishController);

    EventInitHotCancelPublishController.$inject = ['$scope', '$uibModalInstance', 'entity', 'Event'];

    function EventInitHotCancelPublishController($scope, $uibModalInstance, entity, Event) {
        var vm = this;

        vm.event = entity;
        vm.clear = clear;
        vm.cancelPublish = cancelPublish;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        function cancelPublish (id) {
        	vm.event.eventStatus = 'EDITOR';
        	Event.update(vm.event, onSaveSuccess, onSaveError);
        }
        
        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:eventUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    }
})();
