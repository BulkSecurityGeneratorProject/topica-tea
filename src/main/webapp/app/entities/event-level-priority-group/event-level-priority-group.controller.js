(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventLevelPriorityGroupController', EventLevelPriorityGroupController);

    EventLevelPriorityGroupController.$inject = ['$state', '$timeout', 'EventLevelPriorityGroup', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function EventLevelPriorityGroupController($state, $timeout, EventLevelPriorityGroup, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;

        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.saveAll = saveAll;

        vm.priorityChannels = [
        	{name : 'K0A'}
        	, {name: 'K0B'}
        	, {name: 'K1'}
        	, {name: 'K2'}
        	, {name: 'K3'}
        	, {name: 'K4'}
        ];
        
        vm.eventLevels = [
        	{name : 'E1'}
        	, {name: 'E2'}
        	, {name: 'E3'}
        	, {name: 'E4'}
        ];
        
        loadAll();
        
        $timeout(displayOnGrid, 1000);
        
        function saveAll() {
        	var data = [];
        	angular.forEach(vm.eventLevels, function(levelVal, levelKey) {
        		angular.forEach(vm.priorityChannels, function(prioVal, prioKey) {
        			// Check XT
        			if($('#chk_xt_' + levelVal.name + '_' + prioVal.name).is(':checked')) {
        				var item = {isMeatContent: true, 'eventLevel': levelVal.name, 'priorityGroup' : prioVal.name};
        				data.push(item);
        			}
        			// Check Ko XT
        			if($('#chk_koxt_' + levelVal.name + '_' + prioVal.name).is(':checked')) {
        				var item = {isMeatContent: false, 'eventLevel': levelVal.name, 'priorityGroup' : prioVal.name};
        				data.push(item);
        			}
        		})
    		});
        	
        	console.log(data);
        	
        	if (data.length == 0) {
        		AlertService.error("Cần cấu hình trước khi lưu!");
        		return;
        	}
        	
        	EventLevelPriorityGroup.saveAll(data, onSuccess, onError);
            function onSuccess(data, headers) {
            	console.log('Data after save:' + angular.toJson(data));
                vm.eventLevelPriorityGroups = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function displayOnGrid() {
        	angular.forEach(vm.eventLevelPriorityGroups, function(value, key) {
        		var isMeatContent = '';
        		if (value.isMeatContent) {
        			isMeatContent = 'xt';
        		} else {
        			isMeatContent = 'koxt';
        		}
        		// <input type="checkbox" name="chk_koxt_{{level.name}}_{{channel.name}}" id="chk_koxt_{{level.name}}_{{channel.name}}">
        		$('#chk_' + isMeatContent + '_' + value.eventLevel + '_' + value.priorityGroup).prop('checked', true);
    		});
        }
        
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
