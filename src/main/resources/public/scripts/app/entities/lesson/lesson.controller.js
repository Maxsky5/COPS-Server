'use strict';

angular.module('copsApp')
    .controller('LessonController', function ($scope, Lesson) {

        $scope.lessons = [];

        $scope.loadAll = function () {
            Lesson.getAll({}, function (result) {
                $scope.lessons = result;
            });
        };

        $scope.reset = function () {
            $scope.lessons = [];
            $scope.loadAll();
        };

        $scope.loadAll();

        $scope.delete = function (id) {
            Lesson.get({id: id}, function (result) {
                $scope.lesson = result;
                $('#deleteLessonConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Lesson.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteLessonConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.lesson = {name: null, id: null, nbPlace: null};
        };
    });
