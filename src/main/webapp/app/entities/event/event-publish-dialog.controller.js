(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventPublishController',EventPublishController);

    EventPublishController.$inject = ['$uibModalInstance', 'entity', 'Event'];

    function EventPublishController($uibModalInstance, entity, Event) {
        var vm = this;

        vm.event = entity;
        vm.clear = clear;
        vm.confirmPublish = confirmPublish;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmPublish (id) {
        	Event.updateStatus({id : entity.id, status: 'MANAGER_APPROVE', type: 1}, 
                    function () {
                $uibModalInstance.close(true);
            });
        }
    }
})();
