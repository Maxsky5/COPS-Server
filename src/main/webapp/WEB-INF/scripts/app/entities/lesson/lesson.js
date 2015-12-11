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
                    entity: ['$stateParams', 'Lesson', function($stateParams, Lesson) {
                        return Lesson.get({id : $stateParams.id});
                    }]
                }
            })
            .state('lesson.new', {
                parent: 'lesson',
                url: '/new',
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/lesson/lesson-dialog.html',
                        controller: 'LessonDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {name: null, id: null, nbPlace: null};
                            }
                        }
                    }).result.then(function() {
                            $state.go('lesson', null, { reload: true });
                        }, function() {
                            $state.go('lesson');
                        })
                }]
            })
            .state('lesson.edit', {
                parent: 'lesson',
                url: '/{id}/edit',
                data: {
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/lesson/lesson-dialog.html',
                        controller: 'LessonDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Lesson', function(Lesson) {
                                return Lesson.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                            $state.go('lesson', null, { reload: true });
                        }, function() {
                            $state.go('^');
                        })
                }]
            });
    });

