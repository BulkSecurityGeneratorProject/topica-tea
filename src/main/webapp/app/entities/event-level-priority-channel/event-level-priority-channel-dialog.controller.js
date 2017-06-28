(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventLevelPriorityChannelDialogController', EventLevelPriorityChannelDialogController);

    EventLevelPriorityChannelDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'EventLevelPriorityChannel'];

    function EventLevelPriorityChannelDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, EventLevelPriorityChannel) {
        var vm = this;

        vm.eventLevelPriorityChannel = entity;
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
            if (vm.eventLevelPriorityChannel.id !== null) {
                EventLevelPriorityChannel.update(vm.eventLevelPriorityChannel, onSaveSuccess, onSaveError);
            } else {
                EventLevelPriorityChannel.save(vm.eventLevelPriorityChannel, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:eventLevelPriorityChannelUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
