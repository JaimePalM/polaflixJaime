angular.module('navbar', []).component('navbar', {
  templateUrl: 'navbar/navbar.component.html',
  controller: function NavbarController($scope, $http) {
    console.log("Componente navbar cargado (PROPIO)");
    $http.get("http://localhost:8000/users/1").then(function (response) {
      $scope.username = response.data.username;
      console.log("Nombre de usuario: " + $scope.username);
    });
  }
});