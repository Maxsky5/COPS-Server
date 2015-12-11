'use strict';

angular.module('copsApp')
    .controller('LessonDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Lesson', 'Classroom', 'Offender', 'Grade',
        function($scope, $stateParams, $modalInstance, entity, Lesson, Classroom, Offender, Grade) {

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
            Offender.getTeachers({}, function(result) {
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
            console.log($scope.lesson);
            if ($scope.lesson.id != null) {
                Lesson.update($scope.lesson, onSaveFinished);
            } else {
                Lesson.save($scope.lesson, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
