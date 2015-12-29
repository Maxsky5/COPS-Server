'use strict';

angular.module('copsApp')
    .controller('CopDetailController', function ($scope, $rootScope, $stateParams, entity) {
        $scope.cop = entity;

        $rootScope.$on('copsApp:copUpdate', function(event, result) {
            $scope.classroom = result;
        });
    });
