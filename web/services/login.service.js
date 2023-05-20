polaflixMod.service('LoginService', function ($http) {
    var polaflixUrl = "http://localhost:8000";

    this.login = function (username, password) {
        return $http.get(polaflixUrl + "/users?email=" + username).then(function (response) {
            return response.data;
        });
    };
});
