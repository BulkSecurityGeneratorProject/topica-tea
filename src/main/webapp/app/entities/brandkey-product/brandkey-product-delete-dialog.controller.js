(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('BrandkeyProductDeleteController',BrandkeyProductDeleteController);

    BrandkeyProductDeleteController.$inject = ['$uibModalInstance', 'entity', 'BrandkeyProduct'];

    function BrandkeyProductDeleteController($uibModalInstance, entity, BrandkeyProduct) {
        var vm = this;

        vm.brandkeyProduct = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            BrandkeyProduct.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
