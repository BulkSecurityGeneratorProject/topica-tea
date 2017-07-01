(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventDistributeController',EventDistributeController);

    EventDistributeController.$inject = ['$uibModalInstance', 'entity', 'Event'];

    function EventDistributeController($uibModalInstance, entity, Event) {
        var vm = this;

        vm.event = entity;
        vm.clear = clear;
        vm.confirmDistribute = confirmDistribute;
        vm.writerUsers = [];
        vm.selectedUser;
        
        loadAllWriterUser();
        
        function loadAllWriterUser() {
        	Event.queryUserWithRole({role : 'ROLE_WRITER'
            }, onSuccess, onError);
            function onSuccess(data, headers) {
                vm.writerUsers = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDistribute () {
        	console.log("confirmDistribute," + angular.toJson(vm.selectedUser));
            Event.distribute({id: vm.event.id, userId: vm.selectedUser.id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
