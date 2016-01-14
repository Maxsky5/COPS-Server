'use strict';

angular.module('copsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('user', {
                parent: 'entity',
                url: '/users',
                data: {
                    pageTitle: 'Utilisateurs'
                },
                views: {
                    'content@': {
                        templateUrl : 'scripts/app/user/users.html',
                        controller  : 'UserController'
                    }
                }
            })
            .state('user.new', {
                parent: 'user',
                url: '/new',
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/user/user-dialog.html',
                        controller: 'UserDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {};
                            }
                        }
                    }).result.then(function() {
                            $state.go('user', null, { reload: true });
                        }, function() {
                            $state.go('user');
                        })
                }
            })
            .state('user.edit', {
                parent: 'user',
                url: '/{id}/edit',
                data: {
                },
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/user/user-dialog.html',
                        controller: 'UserDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['User', function(User) {
                                return User.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                            $state.go('user', null, { reload: true });
                        }, function() {
                            $state.go('^');
                        })
                }
            });
    });

