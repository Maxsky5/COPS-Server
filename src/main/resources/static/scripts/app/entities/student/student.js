'use strict';

angular.module('copsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student', {
                parent: 'entity',
                url: '/students',
                data: {
                    pageTitle: 'Salles de cours'
                },
                views: {
                    'content@': {
                        templateUrl : 'scripts/app/entities/student/students.html',
                        controller  : 'StudentController'
                    }
                }
            })
            .state('student.detail', {
                parent: 'entity',
                url: '/student/{id}',
                data: {
                    pageTitle: 'Salle de cours'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/student/student-detail.html',
                        controller: 'StudentDetailController'
                    }
                },
                resolve: {
                    entity: function($stateParams, Student) {
                        return Student.get({id : $stateParams.id});
                    }
                }
            })
            .state('student.new', {
                parent: 'student',
                url: '/new',
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/student/student-dialog.html',
                        controller: 'StudentDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {name: null, id: null, nbPlace: null};
                            }
                        }
                    }).result.then(function() {
                            $state.go('student', null, { reload: true });
                        }, function() {
                            $state.go('student');
                        })
                }
            })
            .state('student.edit', {
                parent: 'student',
                url: '/{id}/edit',
                data: {
                },
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/student/student-dialog.html',
                        controller: 'StudentDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function(Student) {
                                return Student.get({id : $stateParams.id});
                            }
                        }
                    }).result.then(function(result) {
                            $state.go('student', null, { reload: true });
                        }, function() {
                            $state.go('^');
                        })
                }
            });
    });

