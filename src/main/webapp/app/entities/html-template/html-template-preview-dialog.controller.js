(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('HtmlTemplatePreviewController',HtmlTemplatePreviewController);

    HtmlTemplatePreviewController.$inject = ['$scope','$compile', '$uibModalInstance', 'entity', 'HtmlTemplate'];

    function HtmlTemplatePreviewController($scope,$compile, $uibModalInstance, entity, HtmlTemplate) {
        var vm = this;

        vm.htmlTemplate = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        vm.previewContent = "";
        vm.cssContent = "";

        loadPreviewContent();
        
        function loadPreviewContent() {
        	var css = "<script type=\"text/css\">" + entity.cssContent + "</script>";
        	var html = entity.templateContent;
        	
        	// replace
        	//html = html.replace('@@imageLink1@@', 'http://via.placeholder.com/170x120');
        	
        	html = html.replace('@@imageLink1@@', 'http://via.placeholder.com/170x120');
        	html = html.replace('@@imageLink2@@', 'http://via.placeholder.com/170x120');
        	html = html.replace('@@imageLink3@@', 'http://via.placeholder.com/170x120');
        	
        	vm.cssContent = entity.cssContent;//entity.cssContent;
        	vm.previewContent = html;
        	
        	//debugger
        	//$("#preview-dialog-css").html(css);
        	
        	//$scope.$apply();
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
