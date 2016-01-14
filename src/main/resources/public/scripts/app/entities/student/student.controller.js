'use strict';

angular.module('copsApp')
    .controller('StudentController', function ($scope, Student) {
        $scope.students = [];
        $scope.loadAll = function() {
            Student.getAll({}, function(result) {
                $scope.students = result;
            });
        };

        $scope.reset = function() {
            $scope.students = [];
            $scope.loadAll();
        };

        $scope.loadAll();

        $scope.delete = function (id) {
            Student.get({id: id}, function(result) {
                $scope.student = result;
                $('#deleteStudentConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Student.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteStudentConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.student = { name: null, id: null, nbPlace: null };
        };
    });
