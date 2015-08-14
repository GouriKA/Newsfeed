'use strict';

/**
 * @ngdoc function
 * @name newsfeed3App.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the newsfeed3App
 */
angular.module('newsfeed3App')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
