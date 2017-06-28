(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventLevelPriorityGroupDeleteController',EventLevelPriorityGroupDeleteController);

    EventLevelPriorityGroupDeleteController.$inject = ['$uibModalInstance', 'entity', 'EventLevelPriorityGroup'];

    function EventLevelPriorityGroupDeleteController($uibModalInstance, entity, EventLevelPriorityGroup) {
        var vm = this;

        vm.eventLevelPriorityGroup = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            EventLevelPriorityGroup.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
