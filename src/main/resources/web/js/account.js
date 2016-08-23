angular.module('account', ['ng-ext-plugins', 'ui.bootstrap'])
    .controller('accountController', ['$scope', 'accountService', AccountController])
    .service('accountService', ['$http', AccountService]);


function AccountController(scope, accountService) {
    scope.user = application.currentUser();
    scope.changeMail = changeMail;
    scope.changePassword = changePassword;
    scope.chMailFm = new ChangeMailForm();
    scope.chPassFm = new ChangePasswordForm();

    function changeMail() {
        var cache = angular.copy(scope.chMailFm.mail);
        var user = new User(scope.user.id, scope.chMailFm.mail);
        accountService.changeMail(user).then(function (response) {
            onMailChangeCallback(response);
            scope.user.userName = cache;
        }, function (response) {
            onMailChangeCallback(null);
        });
    }

    function changePassword() {
        var user = new User(scope.user.id, scope.user.userName, scope.chPassFm.confirmNewPassword);
        accountService.changePassword(user).then(function (response) {
            onPasswordChangeCallback(response);
        }, function (response) {
            onPasswordChangeCallback(null);
        });
    }

    function onMailChangeCallback(response) {
        if (response && response.data.status === 'ok') {
            scope.chMailFm.message = new Message().success('Email changed successfully');
        } else {
            scope.chMailFm.message = new Message().error('Email changing failed');
        }
        clearForm(scope.f1, scope.chMailFm);
    }

    function onPasswordChangeCallback(response) {
        if (response && response.data.status === 'ok') {
            scope.chPassFm.message = new Message().success('Password changed successfully');
        } else {
            scope.chPassFm.message = new Message().error('Password changing failed');
        }
        clearForm(scope.f2, scope.chPassFm);
    }

    function clearForm(form, formModel) {
        form.$setPristine();
        form.$setUntouched();
        formModel.clear();
        setTimeout(function () {
            scope.$apply(function () {
                formModel.message.clear();
            });
        }, 3000);
    }
}

function AccountService(http) {
    this.changePassword = changePassword;
    this.changeMail = changeMail;

    function changePassword(user) {
        return http.post('/changePassword', user);
    }

    function changeMail(user) {
        return http.post('/changeUserName', user);
    }

}

function UserForm() {
    this.message = new Message();
    this.clear = function () {

    }
}

function ChangeMailForm() {
    this.message = new Message();
    this.mail = null;
    this.clear = function () {
        this.mail = null;
    }
}
ChangeMailForm.prototype = new UserForm();

function ChangePasswordForm() {
    this.message = new Message();
    this.currentPassword = null;
    this.newPassword = null;
    this.confirmNewPassword = null;
    this.clear = function () {
        this.currentPassword = null;
        this.newPassword = null;
        this.confirmNewPassword = null;
    }
}
ChangePasswordForm.prototype = new UserForm();