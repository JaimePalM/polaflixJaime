var polaflixMod = angular.module('navbar', []);

polaflixMod.component('navbar', {
  templateUrl: 'navbar/navbar.component.html',
  controller: function NavbarController($scope, $http, $location) {

    $scope.isActive = function (route) {
      return route === $location.path();
    };

    this.$onInit = function () {
      $http.get(this.polaflixUrl + "/users?email=" + this.userEmail).then(function (response) {
        $scope.username = response.data.username;
      });
    };

  },
  bindings: {
    polaflixUrl: '@',
    userEmail: '@'
  }
});