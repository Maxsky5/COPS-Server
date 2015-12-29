'use strict';

angular.module('copsApp')
    .controller('TeacherController', function ($scope, Teacher) {
        $scope.teachers = [];
        $scope.loadAll = function() {
            Teacher.getAll({}, function(result) {
                $scope.teachers = result;
            });
        };

        $scope.reset = function() {
            $scope.teachers = [];
            $scope.loadAll();
        };

        $scope.loadAll();

        $scope.delete = function (id) {
            Teacher.get({id: id}, function(result) {
                $scope.teacher = result;
                $('#deleteTeacherConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Teacher.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteTeacherConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.teacher = { name: null, id: null, nbPlace: null };
        };
    });
