'use strict';

angular.module('copsApp')
    .controller('StudentDialogController', function ($scope, $stateParams, $modalInstance, entity, Student, Grade) {

        $scope.student = entity;
        $scope.grades = [];

        $scope.loadGrades = function () {
            Grade.getAll({}, function (result) {
                $scope.grades = result;
            });
        };

        $scope.loadGrades();

        $scope.load = function (id) {
            Student.get({id: id}, function (result) {
                $scope.student = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('copsApp:studentUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.student.id != null) {
                Student.update($scope.student, onSaveFinished);
            } else {
                Student.save($scope.student, onSaveFinished);
            }
        };

        $scope.clear = function () {
            $modalInstance.dismiss('cancel');
        };
    });
