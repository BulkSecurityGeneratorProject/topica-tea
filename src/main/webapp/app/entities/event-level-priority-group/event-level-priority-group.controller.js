(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventLevelPriorityGroupController', EventLevelPriorityGroupController);

    EventLevelPriorityGroupController.$inject = ['$state', 'Product', 'Brandkey', 'EventLevelPriorityGroup', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function EventLevelPriorityGroupController($state, Product, Brandkey, EventLevelPriorityGroup, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;

        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.brandkeys = [];
        vm.products = [];

        loadAllProduct();
        
        loadAllBrandkey();
        
        loadAll();
        
        function loadAll () {
            EventLevelPriorityGroup.query({
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
                vm.eventLevelPriorityGroups = data;
                vm.page = pagingParams.page;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
            
            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.eventLevelPriorityGroups = data;
                vm.page = pagingParams.page;
                displayOnGrid();
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
            function displayOnGrid() {
            	//$('#chk_1_1').prop('checked', true);
            	angular.forEach(vm.eventLevelPriorityGroups, function(value, key) {
            		console.log(value);
        		});
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
