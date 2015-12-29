'use strict';

angular.module('copsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('cop', {
                parent: 'entity',
                url: '/cops',
                data: {
                    pageTitle: 'Cops'
                },
                views: {
                    'content@': {
                        templateUrl : 'scripts/app/entities/cop/cops.html',
                        controller  : 'CopController'
                    }
                }
            })
            .state('cop.detail', {
                parent: 'entity',
                url: '/cop/{id}',
                data: {
                    pageTitle: 'Cop'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/cop/cop-detail.html',
                        controller: 'CopDetailController'
                    }
                },
                resolve: {
                    entity: function($stateParams, Cop) {
                        return Cop.get({id : $stateParams.id});
                    }
                }
            })
            .state('cop.new', {
                parent: 'cop',
                url: '/new',
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/cop/cop-dialog.html',
                        controller: 'CopDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return { name: null, id: null, macAddress: null };
                            }
                        }
                    }).result.then(function() {
                            $state.go('cop', null, { reload: true });
                        }, function() {
                            $state.go('cop');
                        })
                }
            })
            .state('cop.edit', {
                parent: 'cop',
                url: '/{id}/edit',
                data: {
                },
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/cop/cop-dialog.html',
                        controller: 'CopDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function(Cop) {
                                return Cop.get({id : $stateParams.id});
                            }
                        }
                    }).result.then(function() {
                            $state.go('cop', null, { reload: true });
                        }, function() {
                            $state.go('^');
                        })
                }
            });
    });

