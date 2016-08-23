
<html>
<#include "head.ftl">
<body>
<script src="../js/account.js"></script>

<#include "./templates/navigation.ftl">

<div class="page-content section" ng-app="account" ng-controller="accountController">
    <div class="section-header">
        <div class="section-header">Account</div>
    </div>
    <div class="section-body">
        <div class="section">
            <div class="section-header">Change email address</div>
            <div class="section-body">
                <form name="f1" autocomplete="off" novalidate class="user-form enable-validation">
                    <input type="hidden" name="userId" ng-model="user.id">
                    <div class="form-group form-inline">
                        <label for="currentEmail">Current email</label>
                        <span id="currentEmail">{{user.userName}}</span>
                    </div>
                    <div class="form-group form-inline">
                        <label for="email">New email</label>
                        <input type="email" class="form-control" name="email" id="email" ng-model="chMailFm.mail" user-name>
                        <fm-error-tooltip ng-if="f1.email.$error.userName" message="This mail has been already registered"></fm-error-tooltip>
                        <fm-error-tooltip ng-if="f1.email.$error.email" message="Enter a correct email"></fm-error-tooltip>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-sm" ng-click="changeMail()" ng-disabled="f1.$invalid || !f1.$dirty || f1.$pending">Change email</button>
                        <span ng-bind="chMailFm.message.message" ng-if="chMailFm.message.message" ng-class="chMailFm.message.cssClass"></span>
                    </div>
                </form>
            </div>
        </div>
        <div class="section">
            <div class="section-header">Change password</div>
            <div class="section-body">
                <form name="f2" autocomplete="off" novalidate class="user-form enable-validation">
                    <input type="hidden" name="userId" ng-model="user.id">
                    <div class="form-group form-inline">
                        <label for="currentPassword">Current password</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" ng-model="chPassFm.currentPassword" user-password ng-required ng-minlength="5">
                    </div>
                    <div class="form-group form-inline">
                        <label for="newPassword">New password</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" ng-model="chPassFm.newPassword" ng-required ng-minlength="5">
                        <fm-error-tooltip ng-if="f2.newPassword.$error.minlength" message="New password must contain at least 5 symbols"></fm-error-tooltip>
                    </div>
                    <div class="form-group form-inline">
                        <label for="confirmNewPassword">Confirm new password</label>
                        <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" ng-model="chPassFm.confirmNewPassword" confirm-password ng-required>
                        <fm-error-tooltip ng-if="f2.confirmNewPassword.$error.match" message="Password doesn't match"></fm-error-tooltip>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-sm" ng-click="changePassword()" ng-disabled="f2.$invalid || !f2.$dirty || f2.$pending">Change password</button>
                        <span ng-bind="chPassFm.message.message" ng-if="chPassFm.message.message" ng-class="chPassFm.message.cssClass"></span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
