'use strict';

/**
 * Service of the wepakalenteriApp
 */
angular.module('wepakalenteriApp')
  .service('APIService', ['ENV', '$http', '$window', '$rootScope', function (ENV, $http, $window, $rootScope) {

  	var api = function(url, method, cb, params, data, errorcb) {
      errorcb = errorcb || function(error) { console.log(error); };
      data = data || {};
      params = params || {};
      url = ENV.API_ENDPOINT + url;
      console.log(url);
      var req = {
        method: method,
        url: url,
        data: data,
        login: false,
        params: params,
        paramSerializer: '$httpParamSerializerJQLike'
      };
      $http(req).then(
        function(response)  {
          console.log("This API call took "+ response.config.procTime/1000+" seconds");
          cb(response.data);
        },
        function(error) {
          console.log("API error: " + error.status);
          if (error.status === 401)  {
            delete $window.localStorage.token;
            delete $window.localStorage.userId;
            delete $window.localStorage.customerId;
            $window.location.href="/#!/";
          }
          else {
            console.log('warning' + error.data.message);
            errorcb(error);
          }
        }
      );
    };
  	
  	this.calendarEvent = {
      'list' : function(parameters, cb) {
        api('/events', 'GET', cb, parameters);
      }
    };

  }]);
