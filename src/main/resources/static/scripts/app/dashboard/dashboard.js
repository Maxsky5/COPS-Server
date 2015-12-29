'use strict';

angular.module('copsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dashboard', {
                parent: 'site',
                url: '/dashboard',
                data: {
                    pageTitle: 'Tableau de bord'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/dashboard/dashboard.html',
                        controller: 'DashboardController'
                    }
                }
            });
    });

