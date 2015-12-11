'use strict';

angular.module('copsApp')
    .factory('Student', function ($resource) {
        return $resource('api/students/:id', {}, {
            getAll: { method: 'GET', isArray: true},
            getFromGrade: {
                url: 'api/students/grade/:id',
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
            update: { method:'PUT' }
        });
    });
