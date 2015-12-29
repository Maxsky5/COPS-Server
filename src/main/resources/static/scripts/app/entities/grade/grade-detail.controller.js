'use strict';

angular.module('copsApp')
    .controller('GradeDetailController', function ($scope, $rootScope, $stateParams, entity, Grade, Student, Lesson) {

        $scope.grade = entity;

        $scope.students = [];
        $scope.nextLessons = [];

        $scope.loadStudents = function() {
            Student.getFromGrade({id: $stateParams.id}, function(result) {
                $scope.students = result;
            });
        };

        $scope.loadNextLessons = function() {
            Lesson.getNextGradeLessons({id: $stateParams.id}, function(result) {
                $scope.lessons = result;
            });
        };

        $scope.loadStudents();
        $scope.loadNextLessons();

        $rootScope.$on('copsApp:gradeUpdate', function(event, result) {
            $scope.grade = result;
        });
    });
