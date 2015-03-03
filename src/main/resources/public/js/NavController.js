angular.module('Selfservice').controller('NavController', function ($scope, $location, $route, $http, modulesFactory) {

    $scope.modules = [];

    var modulesPromise = modulesFactory.getModules();

    modulesPromise.then(function(modules){
        $scope.modules = modules;
    });

    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };

    $scope.changeView = function(view){
        $scope.navCollapsed = true;
        if(!angular.equals($location.path(), view)){
            $location.path(view); // path not hash
        }
        $route.reload();
    };
});