'use strict';

angular.module('copsApp', ['LocalStorageModule', 'ui.bootstrap',
    'ngResource', 'ui.router', 'ngCookies', 'ngCacheBuster', 'ui.select', 'ngSanitize'])

    .run(function ($rootScope, $location, $window) {

        $rootScope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams) {
            var titleKey = 'Cops' ;

            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;

            // Set the page title key to the one configured in state or use default one
            if (toState.data.pageTitle) {
                titleKey = toState.data.pageTitle;
            }
            $window.document.title = titleKey;
        });
    })
    .config(function (httpRequestInterceptorCacheBusterProvider, $stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/dashboard");

        $stateProvider
            .state('site', {
                'abstract': true,
                views: {
                    'navbar@': {
                        templateUrl: 'scripts/components/navbar/navbar.html'
                    },
                    'sidebar@': {
                        templateUrl: 'scripts/components/sidebar/sidebar.html'
                    }
                }
            });

        //Cache everything except rest api requests
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);
    });