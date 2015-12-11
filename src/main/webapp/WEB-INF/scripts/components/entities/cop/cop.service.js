'use strict';

angular.module('copsApp')
    .factory('Cop', function ($resource) {
        return $resource('api/cops/:id', {}, {
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
