'use strict';

angular.module('copsApp')
    .controller('TeacherDetailController', function ($scope, $rootScope, $stateParams, entity, Teacher) {
        $scope.teacher = entity;
        $scope.load = function (id) {
            Teacher.get({id: id}, function(result) {
                $scope.teacher = result;
            });
        };
        $rootScope.$on('copsApp:teacherUpdate', function(event, result) {
            $scope.teacher = result;
        });
    });
