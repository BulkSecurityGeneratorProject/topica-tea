(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('BrandkeyDialogController', BrandkeyDialogController);

    BrandkeyDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Brandkey'];

    function BrandkeyDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Brandkey) {
        var vm = this;

        vm.brandkey = entity;
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
            if (vm.brandkey.id !== null) {
                Brandkey.update(vm.brandkey, onSaveSuccess, onSaveError);
            } else {
                Brandkey.save(vm.brandkey, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:brandkeyUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
