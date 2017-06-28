(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('channel-group', {
            parent: 'entity',
            url: '/channel-group?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.channelGroup.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/channel-group/channel-groups.html',
                    controller: 'ChannelGroupController',
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
                    $translatePartialLoader.addPart('channelGroup');
                    $translatePartialLoader.addPart('channelGroupType');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('channel-group-detail', {
            parent: 'channel-group',
            url: '/channel-group/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.channelGroup.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/channel-group/channel-group-detail.html',
                    controller: 'ChannelGroupDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('channelGroup');
                    $translatePartialLoader.addPart('channelGroupType');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ChannelGroup', function($stateParams, ChannelGroup) {
                    return ChannelGroup.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'channel-group',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('channel-group-detail.edit', {
            parent: 'channel-group-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/channel-group/channel-group-dialog.html',
                    controller: 'ChannelGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ChannelGroup', function(ChannelGroup) {
                            return ChannelGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('channel-group.new', {
            parent: 'channel-group',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/channel-group/channel-group-dialog.html',
                    controller: 'ChannelGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                channelGroupType: null,
                                name: null,
                                point: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('channel-group', null, { reload: 'channel-group' });
                }, function() {
                    $state.go('channel-group');
                });
            }]
        })
        .state('channel-group.edit', {
            parent: 'channel-group',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/channel-group/channel-group-dialog.html',
                    controller: 'ChannelGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ChannelGroup', function(ChannelGroup) {
                            return ChannelGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('channel-group', null, { reload: 'channel-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('channel-group.delete', {
            parent: 'channel-group',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/channel-group/channel-group-delete-dialog.html',
                    controller: 'ChannelGroupDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ChannelGroup', function(ChannelGroup) {
                            return ChannelGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('channel-group', null, { reload: 'channel-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
