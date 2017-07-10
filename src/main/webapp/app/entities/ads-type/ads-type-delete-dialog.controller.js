(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('AdsTypeDeleteController',AdsTypeDeleteController);

    AdsTypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'AdsType'];

    function AdsTypeDeleteController($uibModalInstance, entity, AdsType) {
        var vm = this;

        vm.adsType = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AdsType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
