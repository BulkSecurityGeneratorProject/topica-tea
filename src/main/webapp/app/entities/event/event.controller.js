(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventController', EventController);

    EventController.$inject = ['$scope', '$state', 'Event', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams'];

    function EventController($scope, $state, Event, ParseLinks, AlertService, paginationConstants, pagingParams) {

        var vm = this;

        //vm.sendToApproval = sendToApproval;
        vm.orderEditor = orderEditor;
        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;

        loadAll();
        
//        function sendToApproval(id) {
//            Event.updateStatus({id : id, status: 'WAIT_BOSS_APPROVE', type: 0}, onSaveSuccess, onSaveError);
//            
//            function onSaveSuccess (result) {
//                $scope.$emit('topicaEventAmplifyApp:eventUpdate', result);
//                vm.isSaving = false;
//                $state.go('event', null, { reload: 'event' });
//            }
//
//            function onSaveError () {
//                vm.isSaving = false;
//            }
//        }
        
        function orderEditor(id) {
            Event.updateStatus({id : id, status: 'EDITOR', type: 0}, onSaveSuccess, onSaveError);
            
            function onSaveSuccess (result) {
                $scope.$emit('topicaEventAmplifyApp:eventUpdate', result);
                vm.isSaving = false;
                $state.go('event', null, { reload: 'event' });
            }

            function onSaveError () {
                vm.isSaving = false;
            }
        }

        
        
        function loadAll () {
            Event.query({
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
                vm.events = data;
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
