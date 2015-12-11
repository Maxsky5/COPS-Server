'use strict';

angular.module('copsApp')
    .controller('CopDetailController', function ($scope, $rootScope, $stateParams, entity, Cop) {
        $scope.cop = entity;
        $scope.load = function (id) {
            Cop.get({id: id}, function(result) {
                $scope.cop = result;
            });
        };
        $rootScope.$on('copsApp:copUpdate', function(event, result) {
            $scope.classroom = result;
        });
    });
