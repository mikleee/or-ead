<#-- @ftlvariable name="environment" type="java.util.Map<java.lang.String, java.lang.String>" -->
<html>
<#include "head.ftl">
<body ng-app="configuration">

<script src="../js/configuration.js"></script>
<link href="../css/configuration.css" rel="stylesheet"/>

<#include "./templates/navigation.ftl">

<div class="page-content section configuration" ng-app="configuration" ng-controller="environmentController">
    <div class="section-header">Configuration</div>
    <div class="section-body">

        <div class="section">
            <div class="section-header">Environment</div>
            <div class="section-body">
                <h2 ng-if="staticEnvironment.viewState.inProgress" class="centered"><spinner></spinner></h2>
                <table class="table table-condensed" ng-if="staticEnvironment.viewState.success">
                    <thead>
                        <tr>
                            <th>Key</th>
                            <th>Value</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="(key, value) in staticEnvironment.data">
                            <td><b>{{key}}</b></td>
                            <td><i>{{value}}</i></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="section">
            <div class="section-header">Dynamic configuration</div>
            <div class="section-body">
                <h2 ng-if="dynamicConfiguration.viewState.inProgress" class="centered"><spinner></spinner></h2>
                <form name="fm1" ng-if="dynamicConfiguration.viewState.success">
                    <table class="table table-condensed">
                        <thead>
                            <tr>
                                <th>Key</th>
                                <th>Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="d in dynamicConfiguration.data">
                                <td><label for="{{d.key}}">{{d.key}}</label></td>
                                <td><i><input id="{{d.key}}" name="{{d.key}}" type="{{d.type}}" ng-model="d.value"/></i></td>
                            </tr>
                        </tbody>
                    </table>
                    <button class="btn btn-primary" ng-click="updateConfiguration(fm1)" ng-disabled="fm1.$submitted">Update</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
