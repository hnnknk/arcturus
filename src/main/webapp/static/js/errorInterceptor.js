angular.module('myApp')
    .factory('errorInterceptor', ['$q', function($q) {
        return {
            'responseError': function(rejection){

                var defer = $q.defer();

                if(rejection.status === 409){
                    window.location='#!error'
                }

                defer.reject(rejection);

                return defer.promise;

            }
        };
    }]);