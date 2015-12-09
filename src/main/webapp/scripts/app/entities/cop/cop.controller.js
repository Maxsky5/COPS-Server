'use strict';

angular.module('copsApp')
    .controller('CopController', function ($scope, Cop) {
        $scope.cops = [];
        $scope.loadAll = function() {
            Cop.getAll({}, function(result) {
                $scope.cops = result;
            });
        };

        $scope.reset = function() {
            $scope.cops = [];
            $scope.loadAll();
        };

        $scope.loadAll();

        $scope.delete = function (id) {
            Cop.get({id: id}, function(result) {
                $scope.cop = result;
                $('#deleteCopConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Cop.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteCopConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.cop = { name: null, id: null, macAddress: null };
        };
    });
