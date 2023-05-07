var polaflixMod = angular.module('navbar', ['ngRoute']);

polaflixMod.component('navbar', {
  templateUrl: 'navbar/navbar.component.html',
  controller: function NavbarController($scope, $http) {
    $http.get("http://localhost:8000/users/1").then(function (response) {
      $scope.username = response.data.username;
    });
  }
});