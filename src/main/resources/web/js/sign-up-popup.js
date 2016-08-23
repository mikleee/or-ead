angular.module('signUp', ['ng-ext-plugins', 'ui.bootstrap', 'ngAnimate'])
    .controller('signUpController', ['$scope', 'signUpService', SignUpController])
    .service('signUpService', ['$http', SignUpService]);

function SignUpController(scope, signUpService) {
    scope.page = 1;
    scope.user = new User();
    scope.apiKey = new ApiKey();
    scope.submitApiKey = submitApiKey;
    scope.register = register;
    scope.closePopup = closePopup;

    scope.$watch('apiKey.key', scope.apiKey.viewState.setSuccess);


    function submitApiKey() {
        scope.apiKey.viewState.setInProgress();
        signUpService.checkApiKey(scope.apiKey.key).then(handleSubmitApiKeyResponse, scope.apiKey.viewState.setInternalError);
    }

    function register() {
        scope.user.viewState.setInProgress();
        signUpService.register(scope.user).then(handleRegisterResponse, scope.user.viewState.setInternalError)
    }

    function handleSubmitApiKeyResponse(response) {
        if (response.data.status === 'error') {
            scope.apiKey.viewState.setInternalError();
        } else if (response.data.body === true) {
            scope.apiKey.viewState.setSuccess();
            next();
        } else {
            scope.apiKey.viewState.setFail();
        }
    }

    function handleRegisterResponse(response) {
        if (response.data.status === 'error') {
            scope.user.viewState.setInternalError();
        } else {
            scope.user.viewState.setSuccess();
            next();
        }
    }

    function next() {
        scope.page++;
    }

    function closePopup() {
        signUpService.closeSignUpPopup();
    }

}

function SignUpService(http) {
    var signUpPopup = null;

    this.checkApiKey = checkApiKey;
    this.register = register;
    this.setSignUpPopup = setSignUpPopup;
    this.closeSignUpPopup = closeSignUpPopup;

    function checkApiKey(apiKey) {
        return http.post('/api/json/checkApiKey', {apiKey: apiKey});
    }

    function register(user) {
        return http.post('/public/registerUser', user);
    }

    function setSignUpPopup(p) {
        signUpPopup = p;
        signUpPopup.result.then(function () {

        }, function () {

        });
    }

    function closeSignUpPopup() {
        signUpPopup.close()
    }
}

function ApiKey() {
    this.key = null;
    this.viewState = new ViewSate();
}