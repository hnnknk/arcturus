'use strict';

angular.module('myApp').controller('ParseController', ['$scope', 'ParseService', function($scope, ParseService) {
    var self = this;
    self.query = {name:'', titleArg:'', bodyArg:''};
    self.article = {title:'', body:''};
    self.articles = [];

    self.submit = submit;
    self.reset = reset;

    fetchAll();

    function fetchAll(){
        ParseService.fetchAll()
            .then(
                function(d) {
                    self.articles = d;
                },
                function(errResponse){
                    console.error('Error while fetching');
                }
            );
    }

    function createQuery(query){
        ParseService.createQuery(query);
    }

    function submit() {
        createQuery(createQuery(self.query));
        reset();
    }


    function reset(){
        self.query = {name:'', titleArg:'', bodyArg:''};
        $scope.myForm.$setPristine();
    }

}]);