(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventHotDialogController', EventHotDialogController);

    EventHotDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Event', 'Question', 'Article', 'Product'];

    function EventHotDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Event, Question, Article, Product) {
        var vm = this;

        vm.amplifyTypes = ["SHARE", "SPONSOR", "INJECT"];
        vm.event = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        //vm.questions = Question.query({filter: 'event-is-null'});
        //vm.changeAmplifyType = changeAmplifyType;
        vm.loadAllProduct = loadAllProduct;
        vm.changeProducts = changeProducts;
    	vm.products = [];
    	
    	vm.showLpForm = false;
    	vm.showFanpageForm = false;
    	vm.showMailForm = false;
        
        loadAllProduct();
        $timeout(initTabContent, 500);
        
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
        
        function changeProducts() {
        	var lstProducts = [];
        	
        	angular.forEach(vm.products, function (val, key) {
        		if ($('#field_product_' + val.id).is(":checked")) {
        			lstProducts.push(val);
            	}
            });
        	
        	vm.event.products = lstProducts;
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
            	console.log('product data:' + JSON.stringify(data));
                vm.products = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
//            if (vm.event.id !== null) {
//                Event.update(vm.event, onSaveSuccess, onSaveError);
//            } else {
//            	vm.event.name = 'Hot event';
//            	vm.event.article.title = 'Hot event';
                Event.saveHotEvent(vm.event, onSaveSuccess, onSaveError);
//            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:eventUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.schedule = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
