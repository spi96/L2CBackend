var app = angular.module('myApp', ['ngRoute']);

app.config(
    function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'regController'
            })
            .when('/login', {
                templateUrl: 'login/login.html'
            })
            .otherwise({
                redirectTo: '/login'
            });
        //$locationProvider.hashPrefix('');
        $locationProvider.html5Mode(true);
    });

/*'use strict';

angular.module('myApp', [
    'ngRoute'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');
}]);
*/