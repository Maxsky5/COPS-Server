'use strict';

angular.module('copsApp')
    .factory('Grade', function ($resource) {
        return $resource('api/grades/:id', {}, {
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
