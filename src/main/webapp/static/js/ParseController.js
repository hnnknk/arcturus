'use strict';

angular.module('myApp').controller('ParseController', ['$scope', 'ParseService', function($scope, ParseService) {
    var self = this;
    self.query = {url:'', urlSuffix:'', dateTag:'', dateName:'', dateTextTag:'', titleTag:'',
        titleName:'', titleTextTag:'', isFullLinkToBody:'', bodyTag:'', bodyName:'', bodyTextTag:''};
    self.article = {title:'', date:'', body:''};
    self.articles = [];

    self.submit = submit;
    self.citySubmit = citySubmit;
    self.shazooSubmit = shazooSubmit;
    self.kremlinSubmit = kremlinSubmit;
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

    function citySubmit() {
        var city = {url:'https://city-n.ru', urlSuffix:'', dateTag:'class', dateName:'news_date', dateTextTag:'a', titleTag:'class',
            titleName:'news_title', titleTextTag:'a', isFullLinkToBody:'false', bodyTag:'class', bodyName:'news_text', bodyTextTag:''};
        createQuery(city);
    }
    function shazooSubmit() {
        var shazoo = {url:'https://shazoo.ru', urlSuffix:'/news', dateTag:'class', dateName:'entryDate', dateTextTag:'', titleTag:'class',
            titleName:'entryTitle', titleTextTag:'a', isFullLinkToBody:'true', bodyTag:'class', bodyName:'body', bodyTextTag:''};
        createQuery(shazoo);
    }
    function kremlinSubmit() {
        var kremlin = {url:'http://kremlin.ru', urlSuffix:'/events/president/news', dateTag:'class', dateName:'published dt-published', dateTextTag:'', titleTag:'class',
            titleName:'hentry__title hentry__title_special', titleTextTag:'span', isFullLinkToBody:'false', bodyTag:'class', bodyName:'entry-content e-content read__internal_content', bodyTextTag:''};
        createQuery(kremlin);
    }

    function submit() {

        createQuery(createQuery(self.query));
        reset();
    }


    function reset(){
        self.query = {url:'', urlSuffix:'', dateTag:'', dateName:'', dateTextTag:'', titleTag:'',
            titleName:'', titleTextTag:'', isFullLinkToBody:false, bodyTag:'', bodyName:'', bodyTextTag:''};
        $scope.myForm.$setPristine();
    }

}]);