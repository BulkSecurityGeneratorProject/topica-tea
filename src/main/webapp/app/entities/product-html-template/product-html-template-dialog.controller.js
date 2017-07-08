(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ProductHtmlTemplateDialogController', ProductHtmlTemplateDialogController);

    ProductHtmlTemplateDialogController.$inject = ['$timeout', 'Product', 'HtmlTemplate', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProductHtmlTemplate'];

    function ProductHtmlTemplateDialogController ($timeout, Product, HtmlTemplate, $scope, $stateParams, $uibModalInstance, entity, ProductHtmlTemplate) {
        var vm = this;

        vm.productHtmlTemplate = entity;
        vm.clear = clear;
        vm.save = save;
        vm.products = [];
        vm.htmlTemplates = [];
        
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        loadAllProduct();
        loadAllHtmlTemplate();
        
        function loadAllHtmlTemplate() {
        	HtmlTemplate.query({
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.htmlTemplates = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function loadAllProduct() {
            Product.query({
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.products = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.productHtmlTemplate.id !== null) {
                ProductHtmlTemplate.update(vm.productHtmlTemplate, onSaveSuccess, onSaveError);
            } else {
                ProductHtmlTemplate.save(vm.productHtmlTemplate, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:productHtmlTemplateUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
