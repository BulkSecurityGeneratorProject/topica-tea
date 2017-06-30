(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('BrandkeyProductDialogController', BrandkeyProductDialogController);

    BrandkeyProductDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'BrandkeyProduct'];

    function BrandkeyProductDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, BrandkeyProduct) {
        var vm = this;

        vm.brandkeyProduct = entity;
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
            if (vm.brandkeyProduct.id !== null) {
                BrandkeyProduct.update(vm.brandkeyProduct, onSaveSuccess, onSaveError);
            } else {
                BrandkeyProduct.save(vm.brandkeyProduct, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:brandkeyProductUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
