(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('event-level-priority-group', {
            parent: 'entity',
            url: '/event-level-priority-group?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.eventLevelPriorityGroup.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/event-level-priority-group/event-level-priority-groups.html',
                    controller: 'EventLevelPriorityGroupController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('eventLevelPriorityGroup');
                    $translatePartialLoader.addPart('eventLevel');
                    $translatePartialLoader.addPart('priorityGroup');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('event-level-priority-group-detail', {
            parent: 'event-level-priority-group',
            url: '/event-level-priority-group/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.eventLevelPriorityGroup.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/event-level-priority-group/event-level-priority-group-detail.html',
                    controller: 'EventLevelPriorityGroupDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('eventLevelPriorityGroup');
                    $translatePartialLoader.addPart('eventLevel');
                    $translatePartialLoader.addPart('priorityGroup');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'EventLevelPriorityGroup', function($stateParams, EventLevelPriorityGroup) {
                    return EventLevelPriorityGroup.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'event-level-priority-group',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('event-level-priority-group-detail.edit', {
            parent: 'event-level-priority-group-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event-level-priority-group/event-level-priority-group-dialog.html',
                    controller: 'EventLevelPriorityGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EventLevelPriorityGroup', function(EventLevelPriorityGroup) {
                            return EventLevelPriorityGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event-level-priority-group.new', {
            parent: 'event-level-priority-group',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event-level-priority-group/event-level-priority-group-dialog.html',
                    controller: 'EventLevelPriorityGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                isMeatContent: null,
                                eventLevel: null,
                                priorityGroup: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('event-level-priority-group', null, { reload: 'event-level-priority-group' });
                }, function() {
                    $state.go('event-level-priority-group');
                });
            }]
        })
        .state('event-level-priority-group.edit', {
            parent: 'event-level-priority-group',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event-level-priority-group/event-level-priority-group-dialog.html',
                    controller: 'EventLevelPriorityGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EventLevelPriorityGroup', function(EventLevelPriorityGroup) {
                            return EventLevelPriorityGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event-level-priority-group', null, { reload: 'event-level-priority-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event-level-priority-group.delete', {
            parent: 'event-level-priority-group',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event-level-priority-group/event-level-priority-group-delete-dialog.html',
                    controller: 'EventLevelPriorityGroupDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['EventLevelPriorityGroup', function(EventLevelPriorityGroup) {
                            return EventLevelPriorityGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event-level-priority-group', null, { reload: 'event-level-priority-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
