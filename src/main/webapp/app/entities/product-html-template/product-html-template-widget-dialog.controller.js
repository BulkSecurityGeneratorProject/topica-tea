(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ProductHtmlTemplateWidgetController',ProductHtmlTemplateWidgetController);

    ProductHtmlTemplateWidgetController.$inject = ['$location', '$uibModalInstance', 'entity', 'ProductHtmlTemplate'];

    function ProductHtmlTemplateWidgetController($location, $uibModalInstance, entity, ProductHtmlTemplate) {
        var vm = this;

        vm.productHtmlTemplate = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        vm.widgetCode = "";
        
        // Init
        generateWidgetCode();
        
        function generateWidgetCode() {
        	var host = $location.host();
        	var port = $location.port();
        	var code = "<script id=\"topica-widget-script\" src=\"http://" + host + ":" + port + "/content/widget/inject-event.js\" " +
        				"data-channelProductId=\"" + entity.channelProductId + "\" data-templateId=\"" + entity.htmlTemplateId + "\" type=\"text/javascript\">" +
        				"</script>\n"
        				+ "<div id=\"topica-widget-container\"></div>";
        	vm.widgetCode = code;
        }
        
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
