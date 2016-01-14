'use strict';

angular.module('copsApp')
    .controller('ClassroomDetailController', function ($scope, $rootScope, $stateParams, entity) {
        $scope.classroom = entity;

        $rootScope.$on('copsApp:classroomUpdate', function(event, result) {
            $scope.classroom = result;
        });
    });
