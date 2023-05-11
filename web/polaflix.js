var polaflixMod = angular.module('polaflix', ['ngRoute', 'ngDialog']);
var polaflixUrl = "http://localhost:8000";
var userEmail = "paco23@polaflix.com";
var userId = 1;

// Get the navbar component
polaflixMod.component('navbar', {
    templateUrl: 'navbar/navbar.component.html',
    controller: function NavbarController($scope, $http, $location) {
        $http.get(polaflixUrl + "/users?email=" + userEmail).then(function (response) {
            $scope.username = response.data.username;
        });
        $scope.isActive = function (route) {
            return route === $location.path();
        };
    }
});

// Get the footer component
polaflixMod.component('polaflixFooter', {
    templateUrl: 'footer/polaflix-footer.component.html',
    controller: function FooterController($scope, $http) {

    }
});

polaflixMod.controller('loginCtrl', function ($scope, $http, $location) {
    $scope.login = function (username, password) {
        $http.get(polaflixUrl + "/users?email=" + username).then(function (response) {
            console.log(response.data);
            if (response.data.length > 0) {
                $location.path("/home");
            } else {
                $http.get(polaflixUrl + "/users?email=" + userEmail).then(function (response) {
                    $scope.username = response.data.username;
                    $location.path("/home");
                });
            }
        });
    };
});


polaflixMod.controller('getUserSeries', function ($scope, $http) {
    $http.get(polaflixUrl + "/users/" + userId + "/started-series").then(function (response) {
        $scope.startedSeries = response.data;
    });
    $http.get(polaflixUrl + "/users/1/pending-series").then(function (response) {
        $scope.pendingSeries = response.data;
    });
    $http.get(polaflixUrl + "/users/1/finished-series").then(function (response) {
        $scope.finishedSeries = response.data;
    });

    $scope.watchSerie = function (serie) {
        $scope.selectedSerie = serie;
    }
});

polaflixMod.controller('watchSerie', function ($scope, $http, $routeParams) {

    $scope.serieId = $routeParams.serieId;

    $http.get(polaflixUrl + "/series/" + $scope.serieId).then(function (response) {
        $scope.serie = response.data;
        $scope.currentSeason = 1;
    });

    $http.get(polaflixUrl + "/users/" + userId + "/views/2").then(function (response) {
        $scope.views = response.data;
    });

    $scope.changeSeason = function (seasonNumber) {
        $scope.currentSeason = seasonNumber;
    };

    $scope.descriptionView = [];

    $scope.showDescription = function (chapter) {
        $scope.descriptionView[chapter.number - 1] = !$scope.descriptionView[chapter.number - 1];
    };

    $scope.viewChapter = function (serie, season, chapter) {
        $http.post(polaflixUrl + "/users/" + userId + "/mark-chapter-viewed/" + serie.id + "/" + season.number + "/" + chapter.number).then(function (response) {
            $scope.views = response.data;
        });
    };
});

polaflixMod.controller('addSerie', function ($scope, $http, ngDialog) {

    $scope.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    $scope.changeInitial = function (initial) {
        $scope.selectedInitial = initial;
        $http.get(polaflixUrl + "/series?initial=" + initial).then(function (response) {
            $scope.series = response.data;
        });
    };
    $scope.clearFilter = function () {
        $scope.selectedInitial = '';
        $http.get(polaflixUrl + "/series").then(function (response) {
            $scope.series = response.data;
        });
    };


    $http.get(polaflixUrl + "/series").then(function (response) {
        $scope.series = response.data;
        $http.get(polaflixUrl + "/users/" + userId + "/pending-series").then(function (response) {
            $scope.pendingSeries = response.data;
            $scope.series = $scope.series.filter(function (s) {
                return !$scope.pendingSeries.some(function (ps) {
                    return ps.id === s.id;
                });
            });
        });
    });

    $scope.search = function (searchText) {
        $http.get(polaflixUrl + "/series?initial=" + searchText.charAt(0)).then(function (response) {
            $scope.series = response.data;
        });
        $scope.searchText = searchText;
    }

    $scope.addPending = function (serie) {
        $http.post(polaflixUrl + "/users/" + userId + "/pending-series?serieId=" + serie.id).then(function (response) {
            $scope.pendingSeries = response.data;
            // No funciona
            ngDialog.open({
                template: '<h3>La serie se ha añadido con éxito.</h3>',
                className: 'ngdialog-theme-default',
                $scope: $scope.this,
                plain: true
            });
            // Remove pending series from the series list
            $scope.series = $scope.series.filter(function (s) {
                return s.id !== serie.id;
            });
        });
    };

    $scope.descriptionView = [];

    $scope.showDescription = function (serie) {
        // Put the selected description to true
        $scope.descriptionView[serie.id] = !$scope.descriptionView[serie.id];
        console.log($scope.descriptionView.size);
    };

});

polaflixMod.controller('viewBills', function ($scope, $http) {
    $http.get(polaflixUrl + "/users/" + userId + "/bills").then(function (response) {
        $scope.bills = response.data;
        // Set the firt day (yyyy-mm-dd) of current month
        $scope.currentMonth = new Date().toISOString().slice(0, 8) + "01";
        $scope.currentBill = $scope.bills[0];
    });

    $scope.previousMonthAvailable = function () {
        var previousMonth = new Date($scope.currentMonth);
        previousMonth.setMonth(previousMonth.getMonth() - 1);
        return $scope.bills.some(function (bill) {
            return bill.monthBilled === previousMonth.toISOString().slice(0, 7);
        });
    };

    $scope.nextMonthAvailable = function () {
        var nextMonth = new Date($scope.currentMonth);
        nextMonth.setMonth(nextMonth.getMonth() + 1);
        return $scope.bills.some(function (bill) {
            return bill.monthBilled === nextMonth.toISOString().slice(0, 7);
        });
    };

});
  

// Route configuration
polaflixMod.config(function ($routeProvider, $locationProvider) {

    $locationProvider.hashPrefix('');

    $routeProvider
        .when("/", {
            templateUrl: "login.html"
        })
        .when("/home", {
            templateUrl: "polaflix.html"
        })
        .when("/watchSerie/:serieId", {
            templateUrl: "watch.html"
        })
        .when("/addSerie", {
            templateUrl: "add-serie.html"
        })
        .when("/viewBills", {
            templateUrl: "view-bills.html"
        })
        .otherwise({
            templateUrl: "polaflix.html"
        });
});
