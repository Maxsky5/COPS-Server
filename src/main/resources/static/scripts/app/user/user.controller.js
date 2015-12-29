'use strict';

angular.module('copsApp')
    .controller('UserController', function ($scope, User) {
        $scope.users = [];
        $scope.loadAll = function () {
            User.getAll({}, function (result) {
                $scope.users = result;
            });
        };

        $scope.reset = function () {
            $scope.users = [];
            $scope.loadAll();
        };

        $scope.loadAll();

        $scope.delete = function (id) {
            $scope.userToDeleteId = id;
            $('#deleteUserConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            User.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteUserConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.userToDeleteId = null;
        };
    });
