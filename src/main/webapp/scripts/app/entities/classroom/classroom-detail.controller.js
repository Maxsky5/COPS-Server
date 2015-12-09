'use strict';

angular.module('copsApp')
    .controller('ClassroomDetailController', function ($scope, $rootScope, $stateParams, entity, Classroom) {
        $scope.classroom = entity;
        $scope.load = function (id) {
            Classroom.get({id: id}, function(result) {
                $scope.classroom = result;
            });
        };
        $rootScope.$on('copsApp:classroomUpdate', function(event, result) {
            $scope.classroom = result;
        });
    });
