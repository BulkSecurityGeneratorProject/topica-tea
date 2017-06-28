(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventLevelPriorityGroupDialogController', EventLevelPriorityGroupDialogController);

    EventLevelPriorityGroupDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'EventLevelPriorityGroup'];

    function EventLevelPriorityGroupDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, EventLevelPriorityGroup) {
        var vm = this;

        vm.eventLevelPriorityGroup = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.eventLevelPriorityGroup.id !== null) {
                EventLevelPriorityGroup.update(vm.eventLevelPriorityGroup, onSaveSuccess, onSaveError);
            } else {
                EventLevelPriorityGroup.save(vm.eventLevelPriorityGroup, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:eventLevelPriorityGroupUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
