(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('event-level-priority-channel', {
            parent: 'entity',
            url: '/event-level-priority-channel?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.eventLevelPriorityChannel.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/event-level-priority-channel/event-level-priority-channels.html',
                    controller: 'EventLevelPriorityChannelController',
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
                    $translatePartialLoader.addPart('eventLevelPriorityChannel');
                    $translatePartialLoader.addPart('eventLevel');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('event-level-priority-channel-detail', {
            parent: 'event-level-priority-channel',
            url: '/event-level-priority-channel/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.eventLevelPriorityChannel.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/event-level-priority-channel/event-level-priority-channel-detail.html',
                    controller: 'EventLevelPriorityChannelDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('eventLevelPriorityChannel');
                    $translatePartialLoader.addPart('eventLevel');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'EventLevelPriorityChannel', function($stateParams, EventLevelPriorityChannel) {
                    return EventLevelPriorityChannel.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'event-level-priority-channel',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('event-level-priority-channel-detail.edit', {
            parent: 'event-level-priority-channel-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event-level-priority-channel/event-level-priority-channel-dialog.html',
                    controller: 'EventLevelPriorityChannelDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EventLevelPriorityChannel', function(EventLevelPriorityChannel) {
                            return EventLevelPriorityChannel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event-level-priority-channel.new', {
            parent: 'event-level-priority-channel',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event-level-priority-channel/event-level-priority-channel-dialog.html',
                    controller: 'EventLevelPriorityChannelDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                isMeatContent: null,
                                eventLevel: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('event-level-priority-channel', null, { reload: 'event-level-priority-channel' });
                }, function() {
                    $state.go('event-level-priority-channel');
                });
            }]
        })
        .state('event-level-priority-channel.edit', {
            parent: 'event-level-priority-channel',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event-level-priority-channel/event-level-priority-channel-dialog.html',
                    controller: 'EventLevelPriorityChannelDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EventLevelPriorityChannel', function(EventLevelPriorityChannel) {
                            return EventLevelPriorityChannel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event-level-priority-channel', null, { reload: 'event-level-priority-channel' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event-level-priority-channel.delete', {
            parent: 'event-level-priority-channel',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event-level-priority-channel/event-level-priority-channel-delete-dialog.html',
                    controller: 'EventLevelPriorityChannelDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['EventLevelPriorityChannel', function(EventLevelPriorityChannel) {
                            return EventLevelPriorityChannel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event-level-priority-channel', null, { reload: 'event-level-priority-channel' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
