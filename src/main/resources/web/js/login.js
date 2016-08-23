angular.module('login', ['ng-ext-plugins', 'ui.bootstrap', 'signUp'])
    .controller('loginController', ['$scope', '$uibModal', 'signUpService', 'loginService', LoginController])
    .service('loginService', ['$http', LoginService]);


function LoginController(scope, uibModal, signUpService, loginService) {
    scope.user = new User();
    scope.openSignUpForm = openSignUpForm;
    scope.login = login;
    

    scope.$watchGroup(['user.userName', 'user.password'], scope.user.viewState.setSuccess);


    function openSignUpForm() {
        var popup = uibModal.open({
            templateUrl: '/public/directive-template/sign-up-popup',
            backdrop: 'static'
        });
        signUpService.setSignUpPopup(popup);
    }

    function login() {
        scope.user.viewState.setInProgress();
        loginService.login(scope.user).then(function (response) {
            if (response.data.status === 'ok') {
                location.href = response.data.body;
            } else {
                scope.user.viewState.setFail();
            }
        })
    }
}

function LoginService(http) {
    this.login = login;

    function login(user) {
        return http({
            method: 'POST',
            url: '/$login?$username=' + user.userName + '&$password=' + user.password,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    }
}