'use strict';

angular.module('copsApp')
    .controller('GradeDetailController', function ($scope, $rootScope, $stateParams, Grade, Offender) {

        $scope.grade = null;
        $scope.students = [];

        $scope.load = function (id) {
            Grade.get({id: id}, function(result) {
                $scope.grade = result;
            });
        };

        $scope.loadStudents = function() {
            Offender.getStudentsFromGrade({id: $stateParams.id}, function(result) {
                $scope.students = result;
            });
        };

        $scope.load($stateParams.id);
        $scope.loadStudents();

        $rootScope.$on('copsApp:gradeUpdate', function(event, result) {
            $scope.grade = result;
        });
    });