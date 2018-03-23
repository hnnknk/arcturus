'use strict';

var App = angular.module('myApp',['ngCookies','ngRoute','ngResource']);

App.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('errorInterceptor');
}]);

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
        .when('/error',{

            templateUrl: 'static/view/error.html'

        })
        .otherwise(

            { redirectTo: '/'}

        );

});
