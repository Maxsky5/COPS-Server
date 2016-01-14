'use strict';

angular.module('copsApp')
    .factory('AuthService', function ($http, $rootScope, $location) {
        return {
            getUser: function (callback) {
                $http.get('user')
                    .success(function (data) {
                        if (data && data.authenticated) {
                            $rootScope.authenticated = true;
                            $rootScope.user = data;
                        } else {
                            $rootScope.authenticated = false;
                        }

                        callback && callback();
                    })
                    .error(function () {
                        $rootScope.authenticated = false;
                        callback && callback();
                    });
            },
            authenticate: function (credentials) {
                var headers = credentials ? {
                    authorization: "Basic " + btoa(credentials.email + ":" + credentials.password)
                } : {};

                $http.get('user', {headers: headers})
                    .success(function (data) {
                        if (data.authenticated) {
                            $rootScope.authenticated = true;
                            $rootScope.user = data;

                            if ($rootScope.requestedPath) {
                                $location.path($rootScope.requestedPath);
                                $rootScope.requestedPath = null;
                            } else {
                                $location.path("/dashboard");
                            }
                        } else {
                            $rootScope.authenticated = false;
                            $rootScope.error = true;
                            $location.path("/login");
                        }
                    })
                    .error(function () {
                        $rootScope.authenticated = false;
                        $rootScope.error = true;
                        $location.path("/login");
                    });
            },
            logout: function () {
                $http.post('logout').success(function () {
                    $rootScope.user = {};
                    $rootScope.authenticated = false;
                    $location.path("/login");
                }).error(function () {
                    $rootScope.user = {};
                    $rootScope.authenticated = false;
                    $location.path("/login");
                });
            }
        }
    });