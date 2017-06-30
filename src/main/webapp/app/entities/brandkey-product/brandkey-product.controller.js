(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('BrandkeyProductController', BrandkeyProductController);

    BrandkeyProductController.$inject = ['$state', '$timeout', 'Product', 'Brandkey', 'BrandkeyProduct', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function BrandkeyProductController($state, $timeout, Product, Brandkey, BrandkeyProduct, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;

        vm.saveAll = saveAll;
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = 40; // No page size
        vm.brandkeys = [];
        vm.products = [];
        
        loadAllProduct();
        
        loadAllBrandkey();
        
        loadAll();
        
        $timeout(displayOnGrid, 1000);
        
        function saveAll() {
        	var data = [];
        	angular.forEach(vm.brandkeys, function(bandVal, bandKey) {
        		angular.forEach(vm.products, function(proVal, proKey) {
        			if($('#chk_' + bandVal.id + '_' + proVal.id).is(':checked')) {
        				var item = {'brandkey_id': bandVal.id, 'product_id' : proVal.id};
        				data.push(item);
        			}
        		})
    		});
        	
        	console.log(data);
        	
        	if (data.length == 0) {
        		AlertService.error("Cần cấu hình trước khi lưu!");
        		return;
        	}
        	
        	BrandkeyProduct.saveAll(data, onSuccess, onError);
            function onSuccess(data, headers) {
            	console.log('Data after save:' + angular.toJson(data));
                vm.brandkeyProducts = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function loadAllBrandkey() {
        	Brandkey.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);
        	function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
            	console.log('brandkey data:' + JSON.stringify(data));
                vm.brandkeys = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function loadAllProduct() {
            Product.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
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
        
        function loadAll () {
            BrandkeyProduct.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.brandkeyProducts = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function displayOnGrid() {
        	angular.forEach(vm.brandkeyProducts, function(value, key) {
        		$('#chk_' + value.brandkey_id + '_' + value.product_id).prop('checked', true);
    		});
        }
        
        function loadPage(page) {
            vm.page = page;
            vm.transition();
        }

        function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
    }
})();
