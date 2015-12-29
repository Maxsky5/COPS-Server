'use strict';

angular.module('copsApp')
    .controller('UserDialogController', function ($scope, $stateParams, $modalInstance, entity, User, Grade) {

        $scope.user = entity;
        $scope.grades = [];

        $scope.loadGrades = function () {
            Grade.getAll({}, function (result) {
                $scope.grades = result;
            });
        };

        $scope.loadGrades();

        $scope.load = function (id) {
            User.get({id: id}, function (result) {
                $scope.user = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('copsApp:userUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.user.id != null) {
                User.update($scope.user, onSaveFinished);
            } else {
                User.save($scope.user, onSaveFinished);
            }
        };

        $scope.clear = function () {
            $modalInstance.dismiss('cancel');
        };
    });
