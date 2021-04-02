var app = angular.module("RBGModusManager", []);

app.controller("RBGModusManagerController", function($scope, $http) {

    $scope.modusList = [];

    _refreshPageData();

    function _refreshPageData() {
        $http({
            method: 'GET',
            url: '/rgb-stripe'
        }).then(function successCallback(response) {
            $scope.modusList = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    $scope.runModus = function() {

        var data = {
            "mode": this.modus
        };

        $http({
            method: 'PUT',
            url: '/rgb-stripe/mode',
            data: angular.toJson(data),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            function successCallback(response) {
                Toasty01();
            },
            function errorCallback(response) {
                Toasty02();
                if (response != null) {
                    alert(response.data.message || response.statusText);
                    console.log(response.statusText);
                } else {
                    alert("Offline?");
                }
            });
    };
});