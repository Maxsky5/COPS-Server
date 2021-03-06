'use strict';

angular.module('copsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('lesson', {
                parent: 'entity',
                url: '/lessons',
                data: {
                    pageTitle: 'Cours'
                },
                views: {
                    'content@': {
                        templateUrl : 'scripts/app/entities/lesson/lessons.html',
                        controller  : 'LessonController'
                    }
                }
            })
            .state('lesson.detail', {
                parent: 'entity',
                url: '/lesson/{id}',
                data: {
                    pageTitle: 'Cours'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/lesson/lesson-detail.html',
                        controller: 'LessonDetailController'
                    }
                },
                resolve: {
                    entity: function($stateParams, Lesson) {
                        return Lesson.get({id : $stateParams.id});
                    }
                }
            })
            .state('lesson.new', {
                parent: 'lesson',
                url: '/new',
                params: {
                    grade: {}
                },
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/lesson/lesson-dialog.html',
                        controller: 'LessonDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {grades : [$stateParams.grade]};
                            }
                        }
                    }).result.then(function() {
                            $state.go('lesson', null, { reload: true });
                        }, function() {
                            $state.go('lesson');
                        })
                }
            })
            .state('lesson.edit', {
                parent: 'lesson',
                url: '/{id}/edit',
                data: {
                },
                onEnter: function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/lesson/lesson-dialog.html',
                        controller: 'LessonDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function(Lesson) {
                                return Lesson.get({id : $stateParams.id});
                            }
                        }
                    }).result.then(function(result) {
                            $state.go('lesson', null, { reload: true });
                        }, function() {
                            $state.go('^');
                        })
                }
            });
    });

