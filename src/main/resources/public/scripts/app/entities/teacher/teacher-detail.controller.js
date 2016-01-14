'use strict';

angular.module('copsApp')
    .controller('TeacherDetailController', function ($scope, $rootScope, $stateParams, entity) {
        $scope.teacher = entity;

        $rootScope.$on('copsApp:teacherUpdate', function(event, result) {
            $scope.teacher = result;
        });
    });
