(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('BrandkeyDeleteController',BrandkeyDeleteController);

    BrandkeyDeleteController.$inject = ['$uibModalInstance', 'entity', 'Brandkey'];

    function BrandkeyDeleteController($uibModalInstance, entity, Brandkey) {
        var vm = this;

        vm.brandkey = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Brandkey.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
