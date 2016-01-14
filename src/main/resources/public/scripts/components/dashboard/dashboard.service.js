'use strict';

angular.module('copsApp')
    .factory('Dashboard', function ($http) {
        return {
            get: function () {
                return $http.get('api/dashboard');
            }
        };
    });
