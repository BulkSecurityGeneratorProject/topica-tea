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
        
        // 0
        // 1: director -> manager
        // 2: manager -> publish
        function initConfirmDialog() {
        	if (vm.type == 0) {
        		vm.title = "Xác nhận gửi đi"
        		vm.message = "Bạn có chắn chắn gửi đơn hàng đi không?";
        		vm.btnConfirm = "Gửi đi";
        	} else if (vm.type == 1) {
        		vm.title = "Xác nhận gửi đi"
            	vm.message = "Bạn có chắn chắn gửi đơn hàng đến quản lý duyệt không?";
            	vm.btnConfirm = "Gửi đi";
        	} else if (vm.type == 2) {
        		vm.title = "Xác nhận khếch đại"
            	vm.message = "Bạn có chắn chắn khếch đại sự kiện này không?";
            	vm.btnConfirm = "Khếch đại";
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
        	} else if (vm.type == 1) {
            	// Check vm.type
                Event.updateStatus({id : id, status: 'DIRECTOR_APPROVE', type: 0},
                    function () {
                        $uibModalInstance.close(true);
                    });
        	} else if (vm.type == 2) {
            	// Check vm.type
                Event.updateStatus({id : id, status: 'MANAGER_APPROVE', type: 0},
                    function () {
                        $uibModalInstance.close(true);
                    });
        	}
        }
    }
})();
