'use strict';

angular.module('copsApp')
    .controller('StudentDetailController', function ($scope, $rootScope, $stateParams, entity, Student) {
        $scope.student = entity;
        $scope.load = function (id) {
            Student.get({id: id}, function(result) {
                $scope.student = result;
            });
        };
        $rootScope.$on('copsApp:studentUpdate', function(event, result) {
            $scope.student = result;
        });
    });
