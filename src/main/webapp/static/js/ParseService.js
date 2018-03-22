'use strict';

angular.module('myApp').factory('ParseService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/parsing/parse/';
    var REST_SERVICE_RSS_URI = 'http://localhost:8080/parsing/rssparse/';
    var REST_SERVICE_URI_GET = 'http://localhost:8080/parsing/results/';

    var factory = {
        createQuery: createQuery,
        createRssQuery: createRssQuery,
        fetchAll: fetchAll
    };

    return factory;

    function fetchAll() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_GET)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createQuery(query) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, query)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error(errResponse);
                }
            );
        return deferred.promise;
    }

    function createRssQuery(query) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_RSS_URI, query)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error(errResponse);
                }
            );
        return deferred.promise;
    }

}]);