(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('brandkey-product', {
            parent: 'entity',
            url: '/brandkey-product?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.brandkeyProduct.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/brandkey-product/brandkey-products.html',
                    controller: 'BrandkeyProductController',
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
                    $translatePartialLoader.addPart('brandkeyProduct');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('brandkey-product-detail', {
            parent: 'brandkey-product',
            url: '/brandkey-product/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.brandkeyProduct.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/brandkey-product/brandkey-product-detail.html',
                    controller: 'BrandkeyProductDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('brandkeyProduct');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'BrandkeyProduct', function($stateParams, BrandkeyProduct) {
                    return BrandkeyProduct.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'brandkey-product',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('brandkey-product-detail.edit', {
            parent: 'brandkey-product-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/brandkey-product/brandkey-product-dialog.html',
                    controller: 'BrandkeyProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BrandkeyProduct', function(BrandkeyProduct) {
                            return BrandkeyProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('brandkey-product.new', {
            parent: 'brandkey-product',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/brandkey-product/brandkey-product-dialog.html',
                    controller: 'BrandkeyProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('brandkey-product', null, { reload: 'brandkey-product' });
                }, function() {
                    $state.go('brandkey-product');
                });
            }]
        })
        .state('brandkey-product.edit', {
            parent: 'brandkey-product',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/brandkey-product/brandkey-product-dialog.html',
                    controller: 'BrandkeyProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BrandkeyProduct', function(BrandkeyProduct) {
                            return BrandkeyProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('brandkey-product', null, { reload: 'brandkey-product' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('brandkey-product.delete', {
            parent: 'brandkey-product',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/brandkey-product/brandkey-product-delete-dialog.html',
                    controller: 'BrandkeyProductDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['BrandkeyProduct', function(BrandkeyProduct) {
                            return BrandkeyProduct.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('brandkey-product', null, { reload: 'brandkey-product' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
