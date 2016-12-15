'use strict';

/**
 * Controller of the wepakalenteriApp
 */
angular.module('wepakalenteriApp')
  .controller('LoginController', ['$rootScope', '$location', '$http', function ($rootScope, $location, $http) {

  var self = this

  var authenticate = function(credentials, callback) {

    var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
    } : {};

    $http.get("http://localhost:8080/login", {headers : headers}).then(function(response) {
      if (response.data.name) {
        $rootScope.authenticated = true;
      } else {
        $rootScope.authenticated = false;
      }
      callback && callback();
    }, function() {
      $rootScope.authenticated = false;
      callback && callback();
    });

  }

  authenticate();
  self.credentials = {};
  self.login = function() {
      authenticate(self.credentials, function() {
        if ($rootScope.authenticated) {
          $location.path("/");
          self.error = false;
        } else {
          $location.path("http://localhost:8080/login");
          self.error = true;
        }
      });
  };
 }]);
