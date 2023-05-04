var polaflixMod = angular.module('polaflix', ['ngRoute']);
var userEmail = "paco23@polaflix.com";
var userId = 1;

// Get the navbar component
polaflixMod.component('navbar', {
    templateUrl: 'navbar/navbar.component.html',
    controller: function NavbarController($scope, $http) {
        $http.get("http://localhost:8000/users?email=" + userEmail).then(function (response) {
            $scope.username = response.data.username;
          });
    }
});

// Get user series component
polaflixMod.controller('getUserSeries', function ($scope, $http) {
    $http.get("http://localhost:8000/users/1/started-series").then(function (response) {
        $scope.startedSeries = response.data;
    });
    $http.get("http://localhost:8000/users/1/pending-series").then(function (response) {
        $scope.pendingSeries = response.data;
    });
    $http.get("http://localhost:8000/users/1/finished-series").then(function (response) {
        $scope.finishedSeries = response.data;
    });

    $scope.watchSerie = function (serie) {
        $scope.selectedSerie = serie;
        console.log($scope.selectedSerie);
    }
});

polaflixMod.controller('watchSerie', function ($scope, $http) {
    $http.get("http://localhost:8000/series/2").then(function (response) {
        $scope.serie = response.data;
        $scope.currentSeason = 1;
    });

    $http.get("http://localhost:8000/users/" + userId + "/views/2").then(function (response) {
        $scope.views = response.data;
    }); 

    $scope.changeSeason = function (seasonNumber) {
        $scope.currentSeason = seasonNumber;
    };

    $scope.descriptionView = [];

    $scope.showDescription = function (chapter) {
        // Put the selected description to true
        $scope.descriptionView[chapter.number - 1] = !$scope.descriptionView[chapter.number - 1];
        console.log($scope.descriptionView.size);
    };
});

polaflixMod.controller('addSerie', function ($scope, $http) {

    $scope.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    $scope.selectedInitial = "A";

    $scope.changeInitial = function (initial) {
        $scope.selectedInitial = initial;
        $http.get("http://localhost:8000/series?initial=" + initial).then(function (response) {
            $scope.series = response.data;
        });
    };

    $http.get("http://localhost:8000/series").then(function (response) {
        $scope.series = response.data;
    });

    $scope.addPending = function (serie) {
        $http.post("http://localhost:8000/users/" + userId + "/pending-series?serieId=" + serie.id).then(function (response) {
            // No funciona
            ngDialog.open({
                template: '<p>La serie se ha añadido con éxito.</p>',
                $scope: $scope.this,
                plain: true
              });
        });
    };

});

// Route configuration
polaflixMod.config(function ($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl: "polaflix.html"
    })
    .when("/home", {
        templateUrl: "polaflix.html"
    })
    .when("/watchSerie", {
        templateUrl: "watch.html"
    })
    .when("/addSerie", {
        templateUrl: "add-serie.html"
    })
});
