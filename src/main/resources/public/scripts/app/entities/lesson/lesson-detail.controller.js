'use strict';

angular.module('copsApp')
    .controller('LessonDetailController', function ($scope, $rootScope, $stateParams, entity, Lesson) {
        $scope.lesson = entity;

        $scope.checks = [];
        $scope.noCheck = [];

        $scope.loadChecks = function () {
            Lesson.getChecksForLesson({id: $stateParams.id}, function(result) {
                $scope.checks = result;
            });
        };

        $scope.loadNoChecks = function () {
            Lesson.getUsersNoCheck({id: $stateParams.id}, function(result) {
                $scope.noCheck = result;
            });
        };

        $scope.loadChecks();
        $scope.loadNoChecks();

        $rootScope.$on('copsApp:lessonUpdate', function (event, result) {
            $scope.lesson = result;
        });
    });
