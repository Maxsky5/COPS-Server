'use strict';

angular.module('copsApp')
    .controller('CopDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Cop', 'Classroom',
        function($scope, $stateParams, $modalInstance, entity, Cop, Classroom) {

        console.log(entity);
        $scope.cop = entity;
        $scope.classrooms = [];

        $scope.loadClassrooms = function() {
            Classroom.getAll({}, function(result) {
                $scope.classrooms = result;
            });
        };

        $scope.loadClassrooms();

        $scope.load = function(id) {
            Cop.get({id : id}, function(result) {
                $scope.cop = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('copsApp:copUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.cop.id != null) {
                Cop.update($scope.cop, onSaveFinished);
            } else {
                Cop.save($scope.cop, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
