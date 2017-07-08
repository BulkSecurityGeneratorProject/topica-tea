(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('product-html-template', {
            parent: 'entity',
            url: '/product-html-template?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.productHtmlTemplate.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/product-html-template/product-html-templates.html',
                    controller: 'ProductHtmlTemplateController',
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
                    $translatePartialLoader.addPart('productHtmlTemplate');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('product-html-template-detail', {
            parent: 'product-html-template',
            url: '/product-html-template/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.productHtmlTemplate.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/product-html-template/product-html-template-detail.html',
                    controller: 'ProductHtmlTemplateDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('productHtmlTemplate');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ProductHtmlTemplate', function($stateParams, ProductHtmlTemplate) {
                    return ProductHtmlTemplate.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'product-html-template',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('product-html-template-detail.edit', {
            parent: 'product-html-template-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/product-html-template/product-html-template-dialog.html',
                    controller: 'ProductHtmlTemplateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProductHtmlTemplate', function(ProductHtmlTemplate) {
                            return ProductHtmlTemplate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('product-html-template.new', {
            parent: 'product-html-template',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/product-html-template/product-html-template-dialog.html',
                    controller: 'ProductHtmlTemplateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                productId: null,
                                htmlTemplateId: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('product-html-template', null, { reload: 'product-html-template' });
                }, function() {
                    $state.go('product-html-template');
                });
            }]
        })
        .state('product-html-template.edit', {
            parent: 'product-html-template',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/product-html-template/product-html-template-dialog.html',
                    controller: 'ProductHtmlTemplateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProductHtmlTemplate', function(ProductHtmlTemplate) {
                            return ProductHtmlTemplate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('product-html-template', null, { reload: 'product-html-template' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('product-html-template.delete', {
            parent: 'product-html-template',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/product-html-template/product-html-template-delete-dialog.html',
                    controller: 'ProductHtmlTemplateDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ProductHtmlTemplate', function(ProductHtmlTemplate) {
                            return ProductHtmlTemplate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('product-html-template', null, { reload: 'product-html-template' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('product-html-template.widget', {
            parent: 'product-html-template',
            url: '/{id}/widget',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/product-html-template/product-html-template-widget-dialog.html',
                    controller: 'ProductHtmlTemplateWidgetController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ProductHtmlTemplate', function(ProductHtmlTemplate) {
                            return ProductHtmlTemplate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('product-html-template', null, { reload: 'product-html-template' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
