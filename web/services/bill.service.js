polaflixMod.service('BillService', function ($http) {
    var polaflixUrl = "http://localhost:8000";
    var userId = 1;

    this.getBillsForUser = function () {
        return $http.get(polaflixUrl + "/users/" + userId + "/bills").then(function (response) {
            return response.data;
        });
    }
});
