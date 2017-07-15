(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventInitHotDialogController', EventInitHotDialogController);

    EventInitHotDialogController.$inject = ['AlertService', '$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Event', 'Question', 'Article', 'Product', 'AdsType', 'ChannelProduct'];

    function EventInitHotDialogController (AlertService, $timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Event, Question, Article, Product, AdsType, ChannelProduct) {
        var vm = this;

        vm.amplifyTypes = ["SHARE", "SPONSOR", "INJECT"];
        vm.event = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
//        vm.questions = Question.query({filter: 'event-is-null'});
//        vm.changeAmplifyType = changeAmplifyType;
        vm.loadAllProduct = loadAllProduct;
//        vm.changeProducts = changeProducts;
        vm.changeChannelProduct = changeChannelProduct;
        vm.changeAllChannelProduct = changeAllChannelProduct;
    	vm.products = [];
    	vm.adsTypes = [];
    	vm.channelProducts = [];
        
        loadAllProduct();
        loadAllAdsType();
        loadAllChannelProduct();
        
        $timeout(initData, 1000);
        
        function initData() {
        	if (vm.event.id == null) {
        		return;
        	}
        	
        	angular.forEach(vm.event.channelProducts, function (val, key) {
        		$('#chk_channel_' + val.id).prop('checked', true);
            });
        }
        
//        function changeProducts() {
//        	var lstProducts = [];
//        	
//        	angular.forEach(vm.products, function (val, key) {
//        		if ($('#field_product_' + val.id).is(":checked")) {
//        			lstProducts.push(val);
//            	}
//            });
//        	
//        	vm.event.products = lstProducts;
//        }
        
        function changeAllChannelProduct(id) {
        	if ($('#chk_product_all_' + id).is(":checked")) {
        		angular.forEach(vm.channelProducts, function (val,key) {
            		if (val.productId == id) {
            			$('#chk_channel_' + val.id).prop('checked', true);
            		}
            	});
        	} else {
        		angular.forEach(vm.channelProducts, function (val,key) {
            		if (val.productId == id) {
            			$('#chk_channel_' + val.id).prop('checked', false);
            		}
            	});
        	}
        	
        	// Trigger
        	changeChannelProduct();
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
        
        
        function loadAllChannelProduct() {
        	console.log('loadAllChannelProduct');
        	ChannelProduct.queryAll({
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.channelProducts = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function loadAllAdsType() {
        	AdsType.query({
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.adsTypes = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
//        $q.all([vm.event.$promise, vm.questions.$promise]).then(function() {
//            if (!vm.event.questionId) {
//                return $q.reject();
//            }
//            return Question.get({id : vm.event.questionId}).$promise;
//        }).then(function(question) {
//            vm.questions.push(question);
//        });
//        vm.articles = Article.query({filter: 'event-is-null'});
//        $q.all([vm.event.$promise, vm.articles.$promise]).then(function() {
//            if (!vm.event.articleId) {
//                return $q.reject();
//            }
//            return Article.get({id : vm.event.articleId}).$promise;
//        }).then(function(article) {
//            vm.articles.push(article);
//        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });
        
//        function changeAmplifyType() {
//        	vm.event.amplifyType = [];
//        	if ($('#field_amplifyType_SHARE').is(":checked")) {
//        		vm.event.amplifyType.push('SHARE');
//        	}
//        	
//        	if ($('#field_amplifyType_SPONSOR').is(":checked")) {
//        		vm.event.amplifyType.push('SPONSOR');
//        	}
//        	
//        	if ($('#field_amplifyType_INJECT').is(":checked")) {
//        		vm.event.amplifyType.push('INJECT');
//        	}
//        }
        
        function changeChannelProduct() {
        	vm.event.channelProducts = [];
        	
        	angular.forEach(vm.channelProducts, function (val,key) {
        		if ($('#chk_channel_' + val.id).is(":checked")) {
        			vm.event.channelProducts.push(val);
        		}
        	});
        }

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.event.id !== null) {
                Event.update(vm.event, onSaveSuccess, onSaveError);
            } else {
            	//vm.event.name = 'Hot event';
                Event.saveInitHotEvent(vm.event, onSaveSuccess, onSaveError);
            }
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
