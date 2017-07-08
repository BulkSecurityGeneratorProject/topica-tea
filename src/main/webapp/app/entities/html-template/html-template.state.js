(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('html-template', {
            parent: 'entity',
            url: '/html-template?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.htmlTemplate.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/html-template/html-templates.html',
                    controller: 'HtmlTemplateController',
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
                    $translatePartialLoader.addPart('htmlTemplate');
                    $translatePartialLoader.addPart('layoutType');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('html-template-detail', {
            parent: 'html-template',
            url: '/html-template/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'topicaEventAmplifyApp.htmlTemplate.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/html-template/html-template-detail.html',
                    controller: 'HtmlTemplateDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('htmlTemplate');
                    $translatePartialLoader.addPart('layoutType');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'HtmlTemplate', function($stateParams, HtmlTemplate) {
                    return HtmlTemplate.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'html-template',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('html-template-detail.edit', {
            parent: 'html-template-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/html-template/html-template-dialog.html',
                    controller: 'HtmlTemplateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['HtmlTemplate', function(HtmlTemplate) {
                            return HtmlTemplate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('html-template.new', {
            parent: 'html-template',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/html-template/html-template-dialog.html',
                    controller: 'HtmlTemplateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                layoutType: null,
                                cssContent: null,
                                templateContent: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('html-template', null, { reload: 'html-template' });
                }, function() {
                    $state.go('html-template');
                });
            }]
        })
        .state('html-template.edit', {
            parent: 'html-template',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/html-template/html-template-dialog.html',
                    controller: 'HtmlTemplateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['HtmlTemplate', function(HtmlTemplate) {
                            return HtmlTemplate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('html-template', null, { reload: 'html-template' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('html-template.delete', {
            parent: 'html-template',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/html-template/html-template-delete-dialog.html',
                    controller: 'HtmlTemplateDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['HtmlTemplate', function(HtmlTemplate) {
                            return HtmlTemplate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('html-template', null, { reload: 'html-template' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('html-template.preview', {
            parent: 'html-template',
            url: '/{id}/preview',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/html-template/html-template-preview-dialog.html',
                    controller: 'HtmlTemplatePreviewController',
                    controllerAs: 'vm',
                    size: 'lg',
                    resolve: {
                        entity: ['HtmlTemplate', function(HtmlTemplate) {
                            return HtmlTemplate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('html-template', null, { reload: 'html-template' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
