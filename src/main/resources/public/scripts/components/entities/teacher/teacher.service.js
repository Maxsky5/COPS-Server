'use strict';

angular.module('copsApp')
    .factory('Teacher', function ($resource) {
        return $resource('api/teachers/:id', {}, {
            getAll: { method: 'GET', isArray: true},
            get: {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            update: { method:'PUT' }
        });
    });
