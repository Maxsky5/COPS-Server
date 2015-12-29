'use strict';

angular.module('copsApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('login', {
                url: '/login',
                data: {
                    pageTitle: 'Connexion'
                },
                views: {
                    'login@': {
                        templateUrl: 'scripts/app/login/login.html',
                        controller: 'LoginController'
                    }
                }
            });
    });

