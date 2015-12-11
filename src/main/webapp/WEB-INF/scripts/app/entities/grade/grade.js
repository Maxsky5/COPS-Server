'use strict';

angular.module('copsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('grade', {
                parent: 'entity',
                url: '/grades',
                data: {
                    pageTitle: 'Promotions'
                },
                views: {
                    'content@': {
                        templateUrl : 'scripts/app/entities/grade/grades.html',
                        controller  : 'GradeController'
                    }
                }
            })
            .state('grade.detail', {
                parent: 'entity',
                url: '/grade/{id}',
                data: {
                    pageTitle: 'Promotion'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/grade/grade-detail.html',
                        controller: 'GradeDetailController'
                    }
                }
            })
            .state('grade.new', {
                parent: 'grade',
                url: '/new',
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/grade/grade-dialog.html',
                        controller: 'GradeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return { id: null, name: null, dateStart: null, dateEnd: null    };
                            }
                        }
                    }).result.then(function() {
                            $state.go('grade', null, { reload: true });
                        }, function() {
                            $state.go('grade');
                        })
                }]
            })
            .state('grade.edit', {
                parent: 'grade',
                url: '/{id}/edit',
                data: {
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/grade/grade-dialog.html',
                        controller: 'GradeDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Grade', function(Grade) {
                                return Grade.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                            $state.go('grade', null, { reload: true });
                        }, function() {
                            $state.go('^');
                        })
                }]
            });
    });

