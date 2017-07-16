(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventOrderController',EventOrderController);

    EventOrderController.$inject = ['$uibModalInstance', 'entity', 'Event'];

    function EventOrderController($uibModalInstance, entity, Event) {
        var vm = this;

        vm.event = entity;
        vm.clear = clear;
        vm.confirmOrder = confirmOrder;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmOrder (id) {
            Event.updateStatus({id : id, status: 'EDITOR', type: 0},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
