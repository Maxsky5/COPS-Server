'use strict';

angular.module('copsApp')
    .controller('TeacherDialogController', function ($scope, $stateParams, $modalInstance, entity, Teacher) {

        $scope.teacher = entity;

        $scope.load = function (id) {
            Teacher.get({id: id}, function (result) {
                $scope.teacher = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('copsApp:teacherUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.teacher.id != null) {
                Teacher.update($scope.teacher, onSaveFinished);
            } else {
                Teacher.save($scope.teacher, onSaveFinished);
            }
        };

        $scope.clear = function () {
            $modalInstance.dismiss('cancel');
        };
    });
