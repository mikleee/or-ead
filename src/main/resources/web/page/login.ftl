<html>
<#include "head.ftl">
<body ng-app="login">
<link rel="stylesheet" href="../css/login.css">
<script src="../js/sign-up-popup.js"></script>
<script src="../js/login.js"></script>

<div class="login-form">
    <div class="page-content section" ng-controller="loginController">
        <div class="section-header"> Login</div>
        <div class="section-body">
            <form>
                <table>
                    <tbody>
                        <tr>
                            <td><label for="username" ng-class="{'ng-invalid' : user.viewState.failed}"><span class="glyphicon glyphicon-user"></span></label></td>
                            <td><input class="form-control" type="email" name="$username" id="username" placeholder="username" autofocus ng-model="user.userName"/></span></td>
                        </tr>
                        <tr>
                            <td><label for="password" ng-class="{'ng-invalid' : user.viewState.failed}"><span class="glyphicon glyphicon-lock"></span></label></td>
                            <td><input class="form-control" type="password" name="$password" id="password" placeholder="password" ng-model="user.password"/></td>
                        </tr>
                        <tr>
                            <td><spinner ng-show="user.viewState.inProgress"></spinner></td>
                            <td>
                                <button class="btn btn-primary btn-sm" ng-click="login()" ng-disabled="user.viewState.inProgress">Sign in</button>
                            </td>
                        </tr>
                        <tr class="sign-up">
                            <td></td>
                            <td>
                                <span ng-click="openSignUpForm()">Sign up</span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

</body>
</html>