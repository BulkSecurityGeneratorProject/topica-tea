(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('brandkey', {
            parent: 'entity',
            url: '/brandkey?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.brandkey.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/brandkey/brandkeys.html',
                    controller: 'BrandkeyController',
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
                    $translatePartialLoader.addPart('brandkey');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('brandkey-detail', {
            parent: 'brandkey',
            url: '/brandkey/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.brandkey.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/brandkey/brandkey-detail.html',
                    controller: 'BrandkeyDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('brandkey');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Brandkey', function($stateParams, Brandkey) {
                    return Brandkey.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'brandkey',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('brandkey-detail.edit', {
            parent: 'brandkey-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/brandkey/brandkey-dialog.html',
                    controller: 'BrandkeyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Brandkey', function(Brandkey) {
                            return Brandkey.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('brandkey.new', {
            parent: 'brandkey',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/brandkey/brandkey-dialog.html',
                    controller: 'BrandkeyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                brandkeyName: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('brandkey', null, { reload: 'brandkey' });
                }, function() {
                    $state.go('brandkey');
                });
            }]
        })
        .state('brandkey.edit', {
            parent: 'brandkey',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/brandkey/brandkey-dialog.html',
                    controller: 'BrandkeyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Brandkey', function(Brandkey) {
                            return Brandkey.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('brandkey', null, { reload: 'brandkey' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('brandkey.delete', {
            parent: 'brandkey',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/brandkey/brandkey-delete-dialog.html',
                    controller: 'BrandkeyDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Brandkey', function(Brandkey) {
                            return Brandkey.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('brandkey', null, { reload: 'brandkey' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
