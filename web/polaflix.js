var polaflixMod = angular.module('polaflix', ['ngRoute', 'ngDialog', 'navbar', 'polaflix-footer']);
var polaflixUrl = "http://localhost:8000";
var userEmail = "paco23@polaflix.com";
var userId = 1;


// Get the footer component
polaflixMod.component('polaflixFooter', {
    templateUrl: 'footer/polaflix-footer.component.html',
    controller: function FooterController($scope, $http) { }
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

    $http.get(polaflixUrl + "/users/" + userId).then(function (response) {
        $scope.startedSeries = response.data.startedSeries;
        $scope.pendingSeries = response.data.pendingSeries;
        $scope.finishedSeries = response.data.finishedSeries;
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
        // Current season is the season with the lastest chapter viewed
        $http.get(polaflixUrl + "/users/" + userId + "/last-chapter-viewed/" + $scope.serieId).then(function (response)
        {
            $scope.currentSeason = response.data.season.number;
        });
    });

    $http.get(polaflixUrl + "/users/" + userId + "/views/" + $scope.serieId).then(function (response) {
        $scope.views = response.data; // Variable reactiva
    });

    $scope.changeSeason = function (seasonNumber) {
        $scope.currentSeason = seasonNumber; // Variable reactiva
    };

    $scope.descriptionView = [];

    $scope.showDescription = function (chapter) {
        $scope.descriptionView[chapter.number - 1] = !$scope.descriptionView[chapter.number - 1]; // Variable reactiva
    };

    $scope.viewChapter = function (serie, season, chapter) {
        $http.post(polaflixUrl + "/users/" + userId + "/serie/" + serie.id +  "/season/" +  season.number + "/chapter/" + chapter.number + "/viewed").then(function (response) {
            $scope.views = response.data;
        });
    };
});

polaflixMod.controller('addSerie', function ($scope, $http, ngDialog) {

    $scope.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    $scope.changeInitial = function (initial) {
        $scope.selectedInitial = initial; // Variable reactiva
        $http.get(polaflixUrl + "/series?initial=" + initial).then(function (response) {
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
    };
    $scope.clearFilter = function () {
        $scope.selectedInitial = ''; // Variable reactiva
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
            $http.get(polaflixUrl + "/users/" + userId + "/pending-series").then(function (response) {
                $scope.pendingSeries = response.data;
                $scope.series = $scope.series.filter(function (s) {
                    return !$scope.pendingSeries.some(function (ps) {
                        return ps.id === s.id;
                    });
                });
            });
        });
        
        $scope.searchText = searchText; // Variable reactiva
    }

    $scope.addPending = function (serie) {
        $http.put(polaflixUrl + "/users/" + userId + "/pending-series/" + serie.id).then(function (response) {
            $scope.pendingSeries = response.data; // Variable reactiva
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
        $scope.descriptionView[serie.id] = !$scope.descriptionView[serie.id]; // Variable reactiva
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