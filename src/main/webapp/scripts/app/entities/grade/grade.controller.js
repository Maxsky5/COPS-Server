'use strict';

angular.module('copsApp')
    .controller('GradeController', function ($scope, Grade) {
        $scope.grades = [];
        $scope.loadAll = function() {
            Grade.getAll({}, function(result) {
                $scope.grades = result;
            });
        };

        $scope.reset = function() {
            $scope.grades = [];
            $scope.loadAll();
        };

        $scope.loadAll();

        $scope.delete = function (id) {
            Grade.get({id: id}, function(result) {
                $scope.grade = result;
                $('#deleteGradeConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Grade.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteGradeConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.grade = { name: null, id: null };
        };
    });
