polaflixMod.service('UserService', function ($http) {
    var polaflixUrl = "http://localhost:8000";

    this.getUserByEmail = function (email) {
        return $http.get(polaflixUrl + "/users?email=" + email).then(function (response) {
            return response.data;
        });
    };

    this.getUserById = function (userId) {
        return $http.get(polaflixUrl + "/users/" + userId).then(function (response) {
            return response.data;
        });
    };

    this.getLastChapterViewed = function (userId, serieId) {
        return $http.get(polaflixUrl + "/users/" + userId + "/last-chapter-viewed/" + serieId).then(function (response) {
            return response.data;
        });
    }

    this.getViews = function (userId, serieId) {
        return $http.get(polaflixUrl + "/users/" + userId + "/views/" + serieId).then(function (response) {
            return response.data;
        });
    };

    this.viewChapter = function (userId, serieId, seasonNumber, chapterNumber) {
        return $http.put(polaflixUrl + "/users/" + userId + "/views/serie/" + serieId + "/season/" + seasonNumber + "/chapter/" + chapterNumber).then(function (response) {
            return response.data;
        });
    }
});
