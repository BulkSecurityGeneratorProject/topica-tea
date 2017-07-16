(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('event', {
            parent: 'entity',
            url: '/event?page&sort&search',
            data: {
                authorities: ['ROLE_USER','ROLE_DIRECTOR','ROLE_COORDINATOR','ROLE_WRITER','ROLE_APPROVAL','ROLE_MANAGER'],
                pageTitle: 'topicaEventAmplifyApp.event.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/event/events.html',
                    controller: 'EventController',
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
                    $translatePartialLoader.addPart('event');
                    $translatePartialLoader.addPart('eventStatus');
                    $translatePartialLoader.addPart('eventLevel');
                    $translatePartialLoader.addPart('amplifyType');
                    $translatePartialLoader.addPart('priorityGroup');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('event-detail', {
            parent: 'event',
            url: '/event/{id}',
            data: {
                authorities: ['ROLE_USER','ROLE_DIRECTOR','ROLE_COORDINATOR','ROLE_WRITER','ROLE_APPROVAL','ROLE_MANAGER'],
                pageTitle: 'topicaEventAmplifyApp.event.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/event/event-detail.html',
                    controller: 'EventDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('event');
                    $translatePartialLoader.addPart('eventStatus');
                    $translatePartialLoader.addPart('eventLevel');
                    $translatePartialLoader.addPart('amplifyType');
                    $translatePartialLoader.addPart('priorityGroup');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Event', function($stateParams, Event) {
                    return Event.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'event',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('event-detail.edit', {
            parent: 'event-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER','ROLE_DIRECTOR','ROLE_COORDINATOR','ROLE_WRITER','ROLE_APPROVAL','ROLE_MANAGER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-dialog.html',
                    controller: 'EventDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event.new', {
            parent: 'event',
            url: '/new',
            data: {
                authorities: ['ROLE_USER','ROLE_DIRECTOR','ROLE_COORDINATOR','ROLE_WRITER','ROLE_APPROVAL','ROLE_MANAGER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-dialog.html',
                    controller: 'EventDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                content: null,
                                eventStatus: null,
                                eventLevel: null,
                                amplifyType: null,
                                priorityGroup: null,
                                schedule: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('event');
                });
            }]
        })
        .state('event.edit', {
            parent: 'event',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER','ROLE_DIRECTOR','ROLE_COORDINATOR','ROLE_WRITER','ROLE_APPROVAL','ROLE_MANAGER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-dialog.html',
                    controller: 'EventDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event.delete', {
            parent: 'event',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER','ROLE_DIRECTOR','ROLE_COORDINATOR','ROLE_WRITER','ROLE_APPROVAL','ROLE_MANAGER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-delete-dialog.html',
                    controller: 'EventDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event.confirmDialog', {
            parent: 'event',
            url: '/{id}/confirmDialog?type',
            data: {
                authorities: ['ROLE_BOSS','ROLE_SUPER_WRITER','ROLE_APPROVAL','ROLE_MANAGER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/dialog/event-confirm-dialog.html',
                    controller: 'EventConfirmDialogController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event.distribute', {
            parent: 'event',
            url: '/{id}/distribute',
            data: {
                authorities: ['ROLE_COORDINATOR']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-distribute-dialog.html',
                    controller: 'EventDistributeController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event.order', {
            parent: 'event',
            url: '/{id}/order',
            data: {
                authorities: ['ROLE_BOSS']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/dialog/event-order-dialog.html',
                    controller: 'EventOrderController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event.editor', {
            parent: 'event',
            url: '/editor/{id}',
            data: {
                authorities: ['ROLE_WRITER'],
                pageTitle: 'topicaEventAmplifyApp.event.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/event/event-editor.html',
                    controller: 'EventEditorController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('event');
                    $translatePartialLoader.addPart('eventStatus');
                    $translatePartialLoader.addPart('eventLevel');
                    $translatePartialLoader.addPart('amplifyType');
                    $translatePartialLoader.addPart('priorityGroup');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Event', function($stateParams, Event) {
                    return Event.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'event',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('event.publish', {
            parent: 'event',
            url: '/{id}/publish',
            data: {
                authorities: ['ROLE_MANAGER','ROLE_BOSS']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-publish-dialog.html',
                    controller: 'EventPublishController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event.cancelPublish', {
            parent: 'event',
            url: '/{id}/cancelPublish',
            data: {
                authorities: ['ROLE_BOSS']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-init-hot-cancel-publish-dialog.html',
                    controller: 'EventInitHotCancelPublishController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event.hot', {
            parent: 'event',
            url: '/{id}/hot',
            data: {
                authorities: ['ROLE_BOSS','ROLE_SUPER_WRITER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-hot-dialog.html',
                    controller: 'EventHotDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                    	entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('event');
                });
            }]
        })
        .state('event.inithot', {
            parent: 'event',
            url: '/inithot',
            data: {
                authorities: ['ROLE_BOSS']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-init-hot-dialog.html',
                    controller: 'EventInitHotDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                content: null,
                                eventStatus: null,
                                eventLevel: null,
                                amplifyType: null,
                                priorityGroup: null,
                                schedule: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('event');
                });
            }]
        })
        .state('event.inithotview', {
            parent: 'event',
            url: '/{id}/inithot-view',
            data: {
                authorities: ['ROLE_BOSS','ROLE_SUPER_WRITER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-init-hot-view-dialog.html',
                    controller: 'EventInitHotViewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                    	entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('event');
                });
            }]
        })
        .state('event.inithotedit', {
            parent: 'event',
            url: '/{id}/inithot-edit',
            data: {
                authorities: ['ROLE_BOSS']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-init-hot-dialog.html',
                    controller: 'EventInitHotDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                    	entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('event');
                });
            }]
        })
        .state('event.material', {
            parent: 'event',
            url: '/{id}/material',
            data: {
                authorities: ['ROLE_BOSS','ROLE_SUPER_WRITER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-init-hot-material-dialog.html',
                    controller: 'EventInitHotMaterialController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('event.previewContent', {
            parent: 'event',
            url: '/{id}/previewContent',
            data: {
                authorities: ['ROLE_BOSS','ROLE_SUPER_WRITER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/event/event-preview-content-dialog.html',
                    controller: 'EventPreviewContentController',
                    controllerAs: 'vm',
                    size: 'max',
                    resolve: {
                        entity: ['Event', function(Event) {
                            return Event.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('event', null, { reload: 'event' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
