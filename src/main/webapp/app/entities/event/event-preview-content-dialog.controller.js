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

    EventPreviewContentController.$inject = ['$scope', '$timeout', '$uibModalInstance', 'entity', 'Event'];

    function EventPreviewContentController($scope, $timeout, $uibModalInstance, entity, Event) {
        var vm = this;

        vm.event = entity;
        vm.clear = clear;
        vm.previewFanpage = {};
        
        vm.showLpForm = false;
    	vm.showFanpageForm = false;
    	vm.showMailForm = false;
    	vm.isLoadling = true;
    	
        $timeout(initTabContent, 500);
        $timeout(initPreview, 1000);
        
        function initTabContent() {
        	angular.forEach(vm.event.channelProducts, function (val, key) {
        		// Landingpage or Mail
        		if (val.adsTypeId == 1 || val.adsTypeId == 2) {
        			vm.showLpForm = true;
            	}
        		// Fanpage
        		if (val.adsTypeId == 3) {
        			vm.showFanpageForm = true;
            	}
            });
        	
        	// active fanpage tab
        	if (vm.showLpForm == false) {
        		$('#landingPageTab').removeClass('active');
        		$('#fanpageTab').addClass('active');
        		
        		$('#landingpage').removeClass('active');
        		$('#fanpage').addClass('active');
        	}
        }
        
        function initPreview() {
        	vm.isLoadling = true;
        	
        	if (vm.event.article.fanpageContent != "" && vm.event.article.fanpageContent != null) {
        		vm.previewFanpage.message = vm.event.article.fanpageContent; 
        	}
        	
        	if (vm.event.article.fanpageLink != "" && vm.event.article.fanpageLink != null) {
        		var target = vm.event.article.fanpageLink;
        		var key    = "596f04288ae9102c29fad5f164d62227212fbb95e02f1";

        		$.ajax({
        			url: "http://api.linkpreview.net",
        			dataType: 'jsonp',
        			data: {q: target, key: key},
        			success: function (response) {
        				console.log(response);
        				// description
        				// image
        				// title
        				// url
            		    vm.previewFanpage.description = response.description;
            		    vm.previewFanpage.image = response.image;
            		    vm.previewFanpage.title = response.title;
            		    vm.isLoadling = false;
            		    $scope.$digest();
        			}
        		});
        		
//        		$.get("https://cors-anywhere.herokuapp.com/" + vm.event.article.fanpageLink, function(data) {
//        		    var metaDescription = $(data).filter("meta[property='og:description']").attr("content");
//        		    var metaImage = $(data).filter("meta[property='og:image']").attr("content");
//        		    vm.previewFanpage.description = metaDescription;
//        		    vm.previewFanpage.image = metaImage;
//        		    console.log(metaDescription);
//        		    console.log(metaImage);
//        		    $scope.$digest()
//        		}); 
        	}
        }

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

    }
})();
