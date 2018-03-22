'use strict';

var App = angular.module('myApp',['ngCookies','ngRoute','ngResource']);

App.config(function($routeProvider){
    $routeProvider
        .when('/parse',{

            templateUrl: 'static/view/parse.html'

        })
        .when('/rssparse',{

            templateUrl: 'static/view/rssParse.html'

        })
        .when('/result',{

            templateUrl: 'static/view/result.html'

        })
        .otherwise(

            { redirectTo: '/'}

        );

});
