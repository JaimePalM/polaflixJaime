angular.module('polaflix').config(function ($routeProvider, $locationProvider) {

    $locationProvider.hashPrefix('');

    $routeProvider
        .when("/", {
            templateUrl: "app/login.html"
            //templateUrl: "app/polaflix.html"
        })
        .when("/home", {
            templateUrl: "app/polaflix.html"
        })
        .when("/watchSerie/:serieId", {
            templateUrl: "app/watch.html"
        })
        .when("/addSerie", {
            templateUrl: "app/add-serie.html"
        })
        .when("/viewBills", {
            templateUrl: "app/view-bills.html"
        })
        .otherwise({
            templateUrl: "app/polaflix.html"
        });
});
