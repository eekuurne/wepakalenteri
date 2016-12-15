'use strict';

/**
 * @ngdoc overview
 * @name wepakalenteriApp
 * @description
 * # wepakalenteriApp
 *
 * Main module of the application.
 */
angular
  .module('wepakalenteriApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'config'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/calendar.html',
        controller: 'CalendarController'
      })
      .when('/profile', {
        templateUrl: 'views/profile.html',
        controller: 'ProfileController'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginController'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
