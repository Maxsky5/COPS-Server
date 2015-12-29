'use strict';

angular.module('copsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher', {
                parent: 'entity',
                url: '/teachers',
                data: {
                    pageTitle: 'Salles de cours'
                },
                views: {
                    'content@': {
                        templateUrl : 'scripts/app/entities/teacher/teachers.html',
                        controller  : 'TeacherController'
                    }
                }
            })
            .state('teacher.detail', {
                parent: 'entity',
                url: '/teacher/{id}',
                data: {
                    pageTitle: 'Salle de cours'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/teacher/teacher-detail.html',
                        controller: 'TeacherDetailController'
                    }
                },
                resolve: {
                    entity: function($stateParams, Teacher) {
                        return Teacher.get({id : $stateParams.id});
                    }
                }
            })
            .state('teacher.new', {
                parent: 'teacher',
                url: '/new',
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/teacher/teacher-dialog.html',
                        controller: 'TeacherDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {name: null, id: null, nbPlace: null};
                            }
                        }
                    }).result.then(function() {
                            $state.go('teacher', null, { reload: true });
                        }, function() {
                            $state.go('teacher');
                        })
                }
            })
            .state('teacher.edit', {
                parent: 'teacher',
                url: '/{id}/edit',
                data: {
                },
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/teacher/teacher-dialog.html',
                        controller: 'TeacherDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function(Teacher) {
                                return Teacher.get({id : $stateParams.id});
                            }
                        }
                    }).result.then(function(result) {
                            $state.go('teacher', null, { reload: true });
                        }, function() {
                            $state.go('^');
                        })
                }
            });
    });

