'use strict';

angular.module('copsApp')
    .factory('Classroom', function ($resource) {
        return $resource('api/classrooms/:id', {}, {
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
