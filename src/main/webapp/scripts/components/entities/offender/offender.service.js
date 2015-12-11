'use strict';

angular.module('copsApp')
    .factory('Offender', function ($resource) {
        return $resource('api/offenders/:id', {}, {
            getAll: {
                method: 'GET',
                isArray: true
            },
            getTeachers: {
                url: 'api/offenders/teachers',
                method: 'GET',
                isArray: true
            },
            getStudentsFromGrade: {
                url: 'api/offenders/students/:id',
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
