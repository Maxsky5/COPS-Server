'use strict';

angular.module('copsApp')
    .controller('ClassroomController', function ($scope, Classroom) {
        $scope.classrooms = [];
        $scope.loadAll = function() {
            Classroom.getAll({}, function(result) {
                $scope.classrooms = result;
            });
        };

        $scope.reset = function() {
            $scope.classrooms = [];
            $scope.loadAll();
        };

        $scope.loadAll();

        $scope.delete = function (id) {
            Classroom.get({id: id}, function(result) {
                $scope.classroom = result;
                $('#deleteClassroomConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Classroom.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteClassroomConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.classroom = { name: null, id: null, nbPlace: null };
        };
    });
