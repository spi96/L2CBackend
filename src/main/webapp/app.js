var app = angular.module('myApp', ['ngRoute']);

app.config(
    function ($routeProvider) {
        $routeProvider
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'regController'
            })
            .when('/', {
                templateUrl: 'login/login.html'
            })
            .otherwise({
                redirectTo: '/'
            });
    });

/*'use strict';

angular.module('myApp', [
    'ngRoute'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');
}]);
*/