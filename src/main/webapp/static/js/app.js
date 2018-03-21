'use strict';

var App = angular.module('myApp',['ngCookies','ngRoute','ngResource']);

App.config(function($routeProvider){
    $routeProvider
        .when('/parse',{

            templateUrl: 'static/view/parse.html'

        })
        .when('/results',{

            templateUrl: 'static/view/results.html'

        })
        .otherwise(

            { redirectTo: '/'}

        );

});
