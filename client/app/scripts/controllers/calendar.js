'use strict';

/**
 * Controller of the wepakalenteriApp
 */
angular.module('wepakalenteriApp')
  .controller('CalendarController', ['$scope', 'APIService', '$http', function ($scope, APIService, $http) {

  	$scope.weeks = [
  		{days: [
  			{date: '1.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]},
  			{date: '2.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]},
  			{date: '3.1.2017', events: []},
  			{date: '4.1.2017', events: [{title: "Lounas",time: "12.00"}]},
  			{date: '5.1.2017', events: [{title: "Käy puntilla"},{title: "Illallinen", time: "18.00"}]},
  			{date: '6.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]},
  			{date: '7.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]}
  		]}, 
  		{days: [
  			{date: '8.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]},
  			{date: '9.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]},
  			{date: '10.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]},
  			{date: '11.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]},
  			{date: '12.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]},
  			{date: '13.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]},
  			{date: '14.1.2017', events: [{title: "Lounas",time: "12.00"},{title: "Illallinen", time: "18.00"}]}
  		]}
  	];

  	console.log($scope.weeks);
/*
  	var self = this;
  	$http.get('http://localhost:8080/api').then(function(response) {
	    self.greeting = response.data;
	})


  	APIService.calendarEvent.list({}, function(data) {
  		$scope.test = data;
  		console.log($scope.test);
  	});
*/
 }]);
