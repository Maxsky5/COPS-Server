'use strict';

angular.module('copsApp')
    .controller('LoginController', function ($scope, AuthService) {

        $scope.credentials = {};

        $scope.login = function() {
            AuthService.login($scope.credentials);
        };

    });
