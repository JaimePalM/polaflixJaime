angular.module('polaflix').config(function ($routeProvider, $locationProvider) {

    $locationProvider.hashPrefix('');

    $routeProvider
        .when("/", {
            //templateUrl: "src/login.html"
            templateUrl: "src/polaflix.html"
        })
        .when("/home", {
            templateUrl: "src/polaflix.html"
        })
        .when("/watchSerie/:serieId", {
            templateUrl: "src/watch.html"
        })
        .when("/addSerie", {
            templateUrl: "src/add-serie.html"
        })
        .when("/viewBills", {
            templateUrl: "src/view-bills.html"
        })
        .otherwise({
            templateUrl: "src/polaflix.html"
        });
});
