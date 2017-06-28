(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ChannelGroupDialogController', ChannelGroupDialogController);

    ChannelGroupDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ChannelGroup'];

    function ChannelGroupDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ChannelGroup) {
        var vm = this;

        vm.channelGroup = entity;
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
            if (vm.channelGroup.id !== null) {
                ChannelGroup.update(vm.channelGroup, onSaveSuccess, onSaveError);
            } else {
                ChannelGroup.save(vm.channelGroup, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:channelGroupUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
