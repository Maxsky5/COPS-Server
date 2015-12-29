'use strict';

angular.module('copsApp')
    .factory('User', function ($resource) {
        return $resource('api/users/:id', {}, {
            getAll: {
                method: 'GET',
                isArray: true
            },
            get: {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            update: {
                method: 'PUT'
            }
        });
    });
