'use strict';

angular.module('copsApp')
    .controller('LessonDetailController', function($scope, $rootScope, $stateParams, entity) {
        $scope.lesson = entity;

        $rootScope.$on('copsApp:lessonUpdate', function(event, result) {
            $scope.lesson = result;
        });
    });
