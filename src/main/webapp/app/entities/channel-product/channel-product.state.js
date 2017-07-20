(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('channel-product', {
            parent: 'entity',
            url: '/channel-product?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.channelProduct.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/channel-product/channel-products.html',
                    controller: 'ChannelProductController',
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
                    $translatePartialLoader.addPart('channelProduct');
                    $translatePartialLoader.addPart('trafficType');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('channel-product-detail', {
            parent: 'channel-product',
            url: '/channel-product/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.channelProduct.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/channel-product/channel-product-detail.html',
                    controller: 'ChannelProductDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('channelProduct');
                    $translatePartialLoader.addPart('trafficType');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ChannelProduct', function($stateParams, ChannelProduct) {
                    return ChannelProduct.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'channel-product',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('channel-product-detail.edit', {
            parent: 'channel-product-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/channel-product/channel-product-dialog.html',
                    controller: 'ChannelProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ChannelProduct', function(ChannelProduct) {
                            return ChannelProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('channel-product.new', {
            parent: 'channel-product',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/channel-product/channel-product-dialog.html',
                    controller: 'ChannelProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                link: null,
                                traffic: null,
                                trafficType: null,
                                productId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('channel-product', null, { reload: 'channel-product' });
                }, function() {
                    $state.go('channel-product');
                });
            }]
        })
        .state('channel-product.edit', {
            parent: 'channel-product',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/channel-product/channel-product-dialog.html',
                    controller: 'ChannelProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ChannelProduct', function(ChannelProduct) {
                            return ChannelProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('channel-product', null, { reload: 'channel-product' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('channel-product.delete', {
            parent: 'channel-product',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/channel-product/channel-product-delete-dialog.html',
                    controller: 'ChannelProductDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ChannelProduct', function(ChannelProduct) {
                            return ChannelProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('channel-product', null, { reload: 'channel-product' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('channel-product.widget', {
            parent: 'channel-product',
            url: '/{id}/widget',
            data: {
                authorities: ['ROLE_ADMIN','ROLE_BOSS']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/channel-product/channel-product-widget-dialog.html',
                    controller: 'ChannelProductWidgetController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ChannelProduct', function(ChannelProduct) {
                            return ChannelProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('channel-product', null, { reload: 'channel-product' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
