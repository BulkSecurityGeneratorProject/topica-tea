(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ChannelProductWidgetController',ChannelProductWidgetController);

    ChannelProductWidgetController.$inject = ['$location', '$uibModalInstance', 'entity', 'ProductHtmlTemplate', 'ChannelProduct'];

    function ChannelProductWidgetController($location, $uibModalInstance, entity, ProductHtmlTemplate, ChannelProduct) {
        var vm = this;

        vm.channelProduct = entity;
        vm.clear = clear;
        vm.widgetCode = "";
        
        // Init
        generateWidgetCode();
        
        function generateWidgetCode() {
        	var host = $location.host();
        	var port = $location.port();

        	// Get channel product
            //var channelProduct = ChannelProduct.get({id : entity.channelProductId}).$promise;
            var code;
            if (vm.channelProduct.adsTypeId == 1) {
            	code = "<script id=\"topica-widget-script\" src=\"http://" + host + ":" + port + "/content/widget/inject-event.js\" " +
				"data-channelProductId=\"" +  vm.channelProduct.id + "\" type=\"text/javascript\">" +
				"</script>\n"
				+ "<div id=\"topica-widget-container\"></div>";
            } else {
            	code = "http://" + host + ":" + port + "/api/inject-event?channelProductId=" +  vm.channelProduct.id;
            }
        	
        	vm.widgetCode = code;
        }
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
