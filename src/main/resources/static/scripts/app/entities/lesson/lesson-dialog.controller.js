'use strict';

angular.module('copsApp')
    .controller('LessonDialogController', function($scope, $stateParams, $modalInstance, entity, Lesson, Classroom, Teacher, Grade) {

        $scope.lesson = entity;
        $scope.classrooms = [];
        $scope.teachers = [];
        $scope.grades = [];

        $scope.loadClassrooms = function() {
            Classroom.getAll({}, function(result) {
                $scope.classrooms = result;
            });
        };

        $scope.loadTeachers= function() {
            Teacher.getAll({}, function(result) {
                $scope.teachers = result;
            });
        };

        $scope.loadGrades = function() {
            Grade.getAll({}, function(result) {
                $scope.grades = result;
            });
        };

        $scope.loadClassrooms();
        $scope.loadTeachers();
        $scope.loadGrades();

        $scope.load = function(id) {
            Lesson.get({id : id}, function(result) {
                $scope.lesson = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('copsApp:lessonUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.lesson.id != null) {
                Lesson.update($scope.lesson, onSaveFinished);
            } else {
                Lesson.save($scope.lesson, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
    });
