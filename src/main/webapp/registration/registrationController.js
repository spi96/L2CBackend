angular.module('myApp').controller("regController", ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $scope.customer = {};

    $scope.registerUser = function(){
        $http.post("/registration",$scope.customer).then(
            function successCallback(response){
                console.log("user created");
                alert("user created");
                document.getElementById("registration").reset();
                $scope.customer = {};
                $location.path('/login');
            },
            function errorCallback(response){
                console.log(response.data.ex);
                alert(response.data.ex);
            });
        //alert("registration success");
    };

   /*$scope.checkPasswordMatch = function(){

    }

    /*$scope.passwordHash(){}*/
}]);