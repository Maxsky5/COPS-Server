'use strict';

angular.module('copsApp')
    .controller('StudentDetailController', function ($scope, $rootScope, $stateParams, entity) {
        $scope.student = entity;

        $rootScope.$on('copsApp:studentUpdate', function(event, result) {
            $scope.student = result;
        });
    });
