var polaflixMod = angular.module('polaflix', ['ngRoute', 'ngDialog', 'navbar', 'polaflix-footer']);
var polaflixUrl = "http://localhost:8000";
var userEmail = "paco23@polaflix.com";
var userId = 1;

// Get the footer component
polaflixMod.component('polaflixFooter', {
    templateUrl: 'footer/polaflix-footer.component.html',
    controller: function FooterController() { }
});

polaflixMod.controller('login', function ($scope, $location, $rootScope, LoginService) {
    $scope.login = function (username, password) {
        LoginService.login(username, password).then(function (response) {
            if (response.data.length > 0) {
                $rootScope.username = $scope.username;
                $location.path("/home");
            } else {
                $scope.error = "Usuario o contraseña incorrectos";
            }
        });
    };
});

polaflixMod.controller('getUserSeries', function ($scope, UserService) {

    UserService.getUserById(userId).then(function (response) {
        $scope.startedSeries = response.startedSeries;
        $scope.pendingSeries = response.pendingSeries;
        $scope.finishedSeries = response.finishedSeries;
    });

    $scope.watchSerie = function (serie) {
        $scope.selectedSerie = serie;
    }
});

polaflixMod.controller('watchSerie', function ($scope, $routeParams, SerieService, UserService) {

    $scope.serieId = $routeParams.serieId;
    $scope.userId = $routeParams.userId;

    SerieService.getSerieById($scope.serieId).then(function (response) {
        $scope.serie = response;
        $scope.currentSeason = 1;
        // Current season is the season with the lastest chapter viewed
        UserService.getLastChapterViewed(userId, $scope.serieId).then(function (response) {
            $scope.currentSeason = response.season.number;
        });
    });


    UserService.getViews(userId, $scope.serieId).then(function (response) {
        $scope.views = response; // Variable reactiva
    });

    $scope.changeSeason = function (seasonNumber) {
        $scope.currentSeason = seasonNumber; // Variable reactiva
    };

    $scope.descriptionView = [];

    $scope.showDescription = function (chapter) {
        $scope.descriptionView[chapter.number - 1] = !$scope.descriptionView[chapter.number - 1]; // Variable reactiva
    };

    $scope.viewChapter = function (serie, season, chapter) {
        UserService.viewChapter(userId, serie.id, season.number, chapter.number).then(function (response) {
            $scope.views = response;
        });
    };

});

polaflixMod.controller('addSerie', function ($scope, ngDialog, SerieService) {

    $scope.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    SerieService.getAllSeries().then(function (response) {
        $scope.series = response;
        SerieService.getPendingSeriesForUser(userId).then(function (response) {
            $scope.pendingSeries = response;
            $scope.series = $scope.series.filter(function (s) {
                return !$scope.pendingSeries.some(function (ps) {
                    return ps.id === s.id;
                });
            });
        });
    });

    $scope.changeInitial = function (initial) {
        $scope.selectedInitial = initial; // Variable reactiva
        SerieService.getSeriesByInitial(initial).then(function (response) {
            $scope.series = response;
            SerieService.getPendingSeriesForUser(userId).then(function (response) {
                $scope.pendingSeries = response;
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
        SerieService.getAllSeries().then(function (response) {
            $scope.series = response;
            SerieService.getPendingSeriesForUser(userId).then(function (response) {
                $scope.pendingSeries = response;
                $scope.series = $scope.series.filter(function (s) {
                    return !$scope.pendingSeries.some(function (ps) {
                        return ps.id === s.id;
                    });
                });
            });
        });
    };

    $scope.search = function (searchText) {
        SerieService.getSeriesByInitial(searchText.charAt(0)).then(function (response) {
            $scope.series = response;
            SerieService.getPendingSeriesForUser(userId).then(function (response) {
                $scope.pendingSeries = response;
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
        SerieService.addPendingSerieForUser(userId, serie.id).then(function (response) {
            console.log(response);
            $scope.pendingSeries = response; // Variable reactiva
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

polaflixMod.controller('viewBills', function ($scope, BillService) {

    BillService.getBillsForUser(userId).then(function (response) {
        $scope.bills = response;
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