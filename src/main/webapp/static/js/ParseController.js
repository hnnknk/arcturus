'use strict';

angular.module('myApp').controller('ParseController', ['$scope', 'ParseService', function($scope, ParseService) {
    var self = this;
    self.query = {url:'', urlSuffix:'', dateTag:'', dateName:'', dateTextTag:'', titleTag:'',
        titleName:'', titleTextTag:'', isFullLinkToBody:'', bodyTag:'', bodyName:'', bodyTextTag:''};
    self.rssQuery = {url:'', bodyTag:'', bodyName:'', bodyTextTag:''};
    self.article = {title:'', date:'', body:''};
    self.articles = [];

    self.submit = submit;
    self.rssSubmit = rssSubmit;
    self.kinopoiskSubmit = kinopoiskSubmit;
    self.sportSubmit = sportSubmit;
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

    function createRssQuery(query){
        ParseService.createRssQuery(query);
    }

    function kinopoiskSubmit() {
        var kino = {url:'https://st.kp.yandex.net/rss/news.rss', bodyTag:'class', bodyName:'article__content newsContent error_report_area', bodyTextTag:''}
        createRssQuery(kino);
    }

    function sportSubmit() {
        var sport = {url:'https://www.sports.ru/rss/rubric.xml?s=208', bodyTag:'class', bodyName:'https://www.sports.ru/rss/rubric.xml?s=208', bodyTextTag:''}
        createRssQuery(sport)
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

    function rssSubmit() {

        createRssQuery(self.rssQuery);
        reset();
    }


    function reset(){
        self.query = {url:'', urlSuffix:'', dateTag:'', dateName:'', dateTextTag:'', titleTag:'',
            titleName:'', titleTextTag:'', isFullLinkToBody:false, bodyTag:'', bodyName:'', bodyTextTag:''};
        self.rssQuery = {url:'', bodyTag:'', bodyName:'', bodyTextTag:''};
        $scope.myForm.$setPristine();
    }

}]);