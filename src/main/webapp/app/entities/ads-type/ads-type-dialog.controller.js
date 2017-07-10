(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('AdsTypeDialogController', AdsTypeDialogController);

    AdsTypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AdsType'];

    function AdsTypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AdsType) {
        var vm = this;

        vm.adsType = entity;
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
            if (vm.adsType.id !== null) {
                AdsType.update(vm.adsType, onSaveSuccess, onSaveError);
            } else {
                AdsType.save(vm.adsType, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:adsTypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
