'use strict';

angular.module('copsApp')
    .controller('DashboardController', function ($scope, Dashboard) {

        $scope.dashboard = null;

        $scope.load = function () {
            Dashboard.get().success(function (result) {
                $scope.dashboard = result;
            });
        };

        $scope.refresh = function () {
            $scope.dashboard = null;
            $scope.load();
        };

        $scope.load();
    });
