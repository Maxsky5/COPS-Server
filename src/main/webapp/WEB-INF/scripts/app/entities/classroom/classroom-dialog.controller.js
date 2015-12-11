'use strict';

angular.module('copsApp')
    .controller('ClassroomDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Classroom',
        function($scope, $stateParams, $modalInstance, entity, Classroom) {

        $scope.classroom = entity;
        $scope.load = function(id) {
            Classroom.get({id : id}, function(result) {
                $scope.classroom = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('copsApp:classroomUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.classroom.id != null) {
                Classroom.update($scope.classroom, onSaveFinished);
            } else {
                Classroom.save($scope.classroom, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
