'use strict';

angular.module('copsApp')
    .factory('AuthService', function ($http, $rootScope, $location) {
        return {
            authenticate: function (callback) {
                var headers = $rootScope.credentials ? {
                    authorization: "Basic " + btoa($rootScope.credentials.email + ":" + $rootScope.credentials.password)
                } : {};

                $http.get('api/user', {
                    headers: headers
                }).success(function (data) {
                    if (data.authenticated) {
                        $rootScope.authenticated = true;
                        $rootScope.user = data;
                    } else {
                        $rootScope.authenticated = false;
                    }
                    callback && callback();
                }).error(function () {
                    $rootScope.authenticated = false;
                    callback && callback();
                });
            },
            login: function (_credentials) {
                var as = this;
                $http.post('login', $.param(_credentials), {
                    headers: {
                        "content-type": "applicat/x-www-form-urlencoded"
                    }
                }).success(function () {
                    $rootScope.credentials = _credentials;
                    as.authenticate(function () {
                        if ($rootScope.authenticated) {
                            $location.path("/dashboard");
                            $rootScope.error = false;
                        } else {
                            $location.path("/login");
                            $rootScope.error = true;
                        }
                    });
                })
            },
            logout: function () {
                $rootScope.credentials = {};
                $http.post('logout', {}).success(function () {
                    $rootScope.user = {};
                    $rootScope.credentials = {};
                    $rootScope.authenticated = false;
                    $location.path("/login");
                }).error(function () {
                    $rootScope.user = {};
                    $rootScope.credentials = {};
                    $rootScope.authenticated = false;
                    $location.path("/login");
                });
            }
        }
    });