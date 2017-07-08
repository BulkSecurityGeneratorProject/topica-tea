(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('FanpageDialogController', FanpageDialogController);

    FanpageDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fanpage'];

    function FanpageDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fanpage) {
        var vm = this;

        vm.fanpage = entity;
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
            if (vm.fanpage.id !== null) {
                Fanpage.update(vm.fanpage, onSaveSuccess, onSaveError);
            } else {
                Fanpage.save(vm.fanpage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:fanpageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
