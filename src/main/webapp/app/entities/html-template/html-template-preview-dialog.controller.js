(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('HtmlTemplatePreviewController',HtmlTemplatePreviewController);

    HtmlTemplatePreviewController.$inject = ['$uibModalInstance', 'entity', 'HtmlTemplate'];

    function HtmlTemplatePreviewController($uibModalInstance, entity, HtmlTemplate) {
        var vm = this;

        vm.htmlTemplate = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        vm.previewContent = "";

        loadPreviewContent();
        
        function loadPreviewContent() {
        	var css = "<script>" + entity.cssContent + "</script>";
        	var html = entity.templateContent;
        	
        	// replace
        	html = html.replace('@@imageLink1@@', 'http://via.placeholder.com/200x150');
        	html = html.replace('@@imageLink2@@', 'http://via.placeholder.com/200x150');
        	html = html.replace('@@imageLink3@@', 'http://via.placeholder.com/200x150');
        	
        	vm.previewContent = css + html;
        }

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
