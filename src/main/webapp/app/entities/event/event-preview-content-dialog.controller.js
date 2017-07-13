(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventPreviewContentController',EventPreviewContentController)
        .filter('trusted', ['$sce', function ($sce) {
	        return function(url) {
	        		var video_id = url.split('v=')[1].split('&')[0];
	            return $sce.trustAsResourceUrl("https://www.youtube.com/embed/" + video_id);
	        };
	    }]);

    EventPreviewContentController.$inject = ['$uibModalInstance', 'entity', 'Event'];

    function EventPreviewContentController($uibModalInstance, entity, Event) {
        var vm = this;

        vm.event = entity;
        vm.clear = clear;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();
