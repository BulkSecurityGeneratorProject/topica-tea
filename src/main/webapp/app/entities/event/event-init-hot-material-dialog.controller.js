(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventInitHotMaterialController',EventInitHotMaterialController);

    EventInitHotMaterialController.$inject = ['$scope', '$uibModalInstance', 'entity', 'Event'];

    function EventInitHotMaterialController($scope, $uibModalInstance, entity, Event) {
        var vm = this;

        vm.event = entity;
        vm.clear = clear;
        vm.addMaterial = addMaterial;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
        
        function addMaterial (id) {
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
