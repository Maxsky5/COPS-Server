'use strict';

angular.module('copsApp', ['LocalStorageModule', 'ui.bootstrap', 'ngResource', 'ui.router', 'ngCacheBuster', 'ui.select', 'ngSanitize'])

    .run(function ($rootScope, $http, $location, $window, $state, AuthService) {

        AuthService.getUser(function () {
            if (!$rootScope.authenticated) {
                if ($location.path() != '/login') {
                    $rootScope.requestedPath = $location.path();
                }
                $location.path('/login');
            } else {
                $rootScope.requestedPath = null;
            }
        });

        $rootScope.$state = $state;
        $rootScope.loading = true;

        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            if (!$rootScope.authenticated && !$rootScope.loading) {
                if ($location.path() != '/login') {
                    $rootScope.requestedPath = $location.path();
                }

                $location.path('/login');
            }

            $rootScope.loading = false;

            var titleKey = 'Cops';

            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;

            // Set the page title key to the one configured in state or use default one
            if (toState.data.pageTitle) {
                titleKey = toState.data.pageTitle;
            }
            $window.document.title = titleKey;
        });

    })
    .config(function (httpRequestInterceptorCacheBusterProvider, $stateProvider, $urlRouterProvider, $httpProvider) {

        $urlRouterProvider.otherwise("/dashboard");

        $stateProvider
            .state('site', {
                'abstract': true,
                views: {
                    'navbar@': {
                        templateUrl: 'scripts/components/navbar/navbar.html',
                        controller: 'NavbarController'
                    },
                    'sidebar@': {
                        templateUrl: 'scripts/components/sidebar/sidebar.html'
                    }
                }
            });

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

        //Cache everything except rest api requests
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);

        $httpProvider.interceptors.push('httpInterceptor');
    });