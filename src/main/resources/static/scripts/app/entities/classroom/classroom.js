'use strict';

angular.module('copsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('classroom', {
                parent: 'entity',
                url: '/classrooms',
                data: {
                    pageTitle: 'Salles de cours'
                },
                views: {
                    'content@': {
                        templateUrl : 'scripts/app/entities/classroom/classrooms.html',
                        controller  : 'ClassroomController'
                    }
                }
            })
            .state('classroom.detail', {
                parent: 'entity',
                url: '/classroom/{id}',
                data: {
                    pageTitle: 'Salle de cours'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/classroom/classroom-detail.html',
                        controller: 'ClassroomDetailController'
                    }
                },
                resolve: {
                    entity: function($stateParams, Classroom) {
                        return Classroom.get({id : $stateParams.id});
                    }
                }
            })
            .state('classroom.new', {
                parent: 'classroom',
                url: '/new',
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/classroom/classroom-dialog.html',
                        controller: 'ClassroomDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {name: null, id: null, nbPlace: null};
                            }
                        }
                    }).result.then(function() {
                            $state.go('classroom', null, { reload: true });
                        }, function() {
                            $state.go('classroom');
                        })
                }
            })
            .state('classroom.edit', {
                parent: 'classroom',
                url: '/{id}/edit',
                data: {
                },
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/classroom/classroom-dialog.html',
                        controller: 'ClassroomDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function(Classroom) {
                                return Classroom.get({id : $stateParams.id});
                            }
                        }
                    }).result.then(function(result) {
                            $state.go('classroom', null, { reload: true });
                        }, function() {
                            $state.go('^');
                        })
                }
            });
    });

