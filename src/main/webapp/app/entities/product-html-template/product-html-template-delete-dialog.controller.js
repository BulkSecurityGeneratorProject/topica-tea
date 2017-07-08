(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ProductHtmlTemplateDeleteController',ProductHtmlTemplateDeleteController);

    ProductHtmlTemplateDeleteController.$inject = ['$uibModalInstance', 'entity', 'ProductHtmlTemplate'];

    function ProductHtmlTemplateDeleteController($uibModalInstance, entity, ProductHtmlTemplate) {
        var vm = this;

        vm.productHtmlTemplate = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ProductHtmlTemplate.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
