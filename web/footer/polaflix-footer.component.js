var polaflixMod = angular.module('polaflix-footer', []);

polaflixMod.component('polaflix-footer', {
  templateUrl: 'footer/polaflix-footer.component.html',
  controller: function FooterController($scope, $http) {
    console.log("FooterController");
}
});