(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventConfirmDialogController',EventConfirmDialogController);

    EventConfirmDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Event'];

    function EventConfirmDialogController($stateParams, $uibModalInstance, entity, Event) {
        var vm = this;

        vm.event = entity;
        vm.type = $stateParams.type;
        vm.clear = clear;
        vm.confirmDialog = confirmDialog;
        vm.message;
        vm.title;
        vm.btnConfirm;

        initConfirmDialog();
        
        function initConfirmDialog() {
        	if (vm.type == 0) {
        		vm.title = "Xác nhận gửi đi"
        		vm.message = "Bạn có chắn chắn gửi đơn hàng đi không?";
        		vm.btnConfirm = "Gửi đi";
        	}
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

//        function sendToApproval(id) {
//            Event.updateStatus({id : id, status: 'WAIT_BOSS_APPROVE', type: 0}, onSaveSuccess, onSaveError);
//            
//            function onSaveSuccess (result) {
//                $scope.$emit('topicaEventAmplifyApp:eventUpdate', result);
//                vm.isSaving = false;
//                $state.go('event', null, { reload: 'event' });
//            }
//
//            function onSaveError () {
//                vm.isSaving = false;
//            }
//        }
        // type: 0 , confirm sendToApproval
        function confirmDialog (id) {
        	console.log('vm.type:' + vm.type);
        	if (vm.type == 0) {
            	// Check vm.type
                Event.updateStatus({id : id, status: 'WAIT_BOSS_APPROVE', type: 0},
                    function () {
                        $uibModalInstance.close(true);
                    });
        	}
        }
    }
})();
