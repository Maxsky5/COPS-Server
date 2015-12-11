'use strict';

angular.module('copsApp')
    .controller('GradeDialogController', function ($scope, $stateParams, $modalInstance, entity, Grade) {

        $scope.grade = entity;

        $scope.load = function (id) {
            Grade.get({id: id}, function (result) {
                $scope.grade = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('copsApp:gradeUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.grade.id != null) {
                Grade.update($scope.grade, onSaveFinished);
            } else {
                Grade.save($scope.grade, onSaveFinished);
            }
        };

        $scope.clear = function () {
            $modalInstance.dismiss('cancel');
        };
    });
