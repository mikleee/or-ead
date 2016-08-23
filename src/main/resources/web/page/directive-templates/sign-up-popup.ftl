<div ng-switch="page" ng-controller="signUpController" class="sign-up-popup">

    <div class="modal-header">
        <div class="modal-title">
            <button type="button" class="close" ng-click="closePopup()"><span aria-hidden="true">&times;</span></button>
            <b>User registration</b>
        </div>
    </div>

    <div ng-switch-when="1">
        <div class="modal-body">
            <p>Enter the API key to continue the account registration</p>
            <form name="f1" autocomplete="off">
                <div class="form-group form-inline">
                    <label for="apiKey">API Key</label>
                    <input type="text" class="form-control" name="apiKey" id="apiKey" ng-model="apiKey.key">
                    <fm-error-tooltip ng-if="apiKey.viewState.failed" message="Api key is incorrect"></fm-error-tooltip>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-sm btn-primary" ng-disabled="apiKey.viewState.inProgress" ng-click="submitApiKey()">Next ></button>
        </div>
    </div>

    <div ng-switch-when="2">
        <div class="modal-body">
            <p>Define your credentials</p>
            <form name="f2" autocomplete="off" novalidate class="user-form enable-validation">
                <div class="form-group form-inline">
                    <label for="userName">Email</label>
                    <input type="email" class="form-control" name="userName" id="userName" ng-model="user.userName" user-name>
                    <fm-error-tooltip ng-if="f2.userName.$error.userName" message="This mail has been already registered"></fm-error-tooltip>
                    <fm-error-tooltip ng-if="f2.userName.$error.email" message="Enter a correct email"></fm-error-tooltip>
                </div>
                <div class="form-group form-inline">
                    <label for="newPassword">Password</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" ng-model="user.password" ng-required ng-minlength="5">
                    <fm-error-tooltip ng-if="f2.newPassword.$error.minlength" message="New password must contain at least 5 symbols"></fm-error-tooltip>
                </div>
                <div class="form-group form-inline">
                    <label for="confirmNewPassword">Confirm new password</label>
                    <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" ng-model="user.confirmNewPassword" confirm-password ng-required>
                    <fm-error-tooltip ng-if="f2.confirmNewPassword.$error.match" message="Password doesn't match"></fm-error-tooltip>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-sm btn-primary" ng-disabled="user.viewState.inProgress || f2.$pending" ng-click="register()">Sign up</button>
        </div>
    </div>

    <div ng-switch-when="3">
        <div class="modal-body ">
            <p>Registration completed</p>
            <form class="user-form alert-success">
                <div>
                    <label for="yourUserName">Email</label>
                    <span id="yourUserName" ng-bind="user.userName"></span>
                </div>
                <div class="form-group form-inline">
                    <label for="yourPassword">Password</label>
                    <span id="yourPassword" ng-bind="user.password"></span>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-sm btn-default" ng-click="closePopup()">Close</button>
        </div>
    </div>

</div>
