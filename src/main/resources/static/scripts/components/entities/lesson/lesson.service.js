'use strict';

angular.module('copsApp')
    .factory('Lesson', function ($resource) {
        return $resource('api/lessons/:id', {}, {
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
            },
            getNextGradeLessons: {
                method: 'GET',
                url: 'api/lessons/grade/:id',
                isArray: true
            }
        });
    });
