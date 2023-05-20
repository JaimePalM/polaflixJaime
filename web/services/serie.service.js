polaflixMod.service('SerieService', function ($http) {
    var polaflixUrl = "http://localhost:8000";

    this.getAllSeries = function () {
        return $http.get(polaflixUrl + "/series").then(function (response) {
            return response.data;
        });
    };

    this.getSerieById = function (serieId) {
        return $http.get(polaflixUrl + "/series/" + serieId).then(function (response) {
            return response.data;
        });
    };

    this.getSeriesByInitial = function (initial) {
        return $http.get(polaflixUrl + "/series?initial=" + initial).then(function (response) {
            return response.data;
        });
    };

    this.getPendingSeriesForUser = function (userId) {
        return $http.get(polaflixUrl + "/users/" + userId + "/pending-series").then(function (response) {
            return response.data;
        });
    };

    this.addPendingSerieForUser = function (userId, serieId) {
        return $http.put(polaflixUrl + "/users/" + userId + "/pending-series/" + serieId).then(function (response) {
            return response.data;
        });
    };
});
