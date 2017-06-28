(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ChannelGroupDeleteController',ChannelGroupDeleteController);

    ChannelGroupDeleteController.$inject = ['$uibModalInstance', 'entity', 'ChannelGroup'];

    function ChannelGroupDeleteController($uibModalInstance, entity, ChannelGroup) {
        var vm = this;

        vm.channelGroup = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ChannelGroup.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
