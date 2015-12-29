angular.module('copsApp')
    .controller('NavbarController', function ($rootScope, $scope, $http, $location, AuthService) {

        $scope.logout = function () {
            AuthService.logout();
        }

    });
