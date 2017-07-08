(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('HtmlTemplateDeleteController',HtmlTemplateDeleteController);

    HtmlTemplateDeleteController.$inject = ['$uibModalInstance', 'entity', 'HtmlTemplate'];

    function HtmlTemplateDeleteController($uibModalInstance, entity, HtmlTemplate) {
        var vm = this;

        vm.htmlTemplate = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            HtmlTemplate.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
