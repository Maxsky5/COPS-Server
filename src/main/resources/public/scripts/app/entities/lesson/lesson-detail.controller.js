'use strict';

angular.module('copsApp')
    .controller('LessonDetailController', function($scope, $rootScope, $stateParams, entity, Lesson) {
        $scope.lesson = entity;
        $scope.load = function (id) {
            Lesson.get({id: id}, function(result) {
                $scope.lesson = result;
            });
        };
        $rootScope.$on('copsApp:lessonUpdate', function(event, result) {
            $scope.lesson = result;
        });
    });
