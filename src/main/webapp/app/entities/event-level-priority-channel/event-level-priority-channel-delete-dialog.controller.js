(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventLevelPriorityChannelDeleteController',EventLevelPriorityChannelDeleteController);

    EventLevelPriorityChannelDeleteController.$inject = ['$uibModalInstance', 'entity', 'EventLevelPriorityChannel'];

    function EventLevelPriorityChannelDeleteController($uibModalInstance, entity, EventLevelPriorityChannel) {
        var vm = this;

        vm.eventLevelPriorityChannel = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            EventLevelPriorityChannel.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
