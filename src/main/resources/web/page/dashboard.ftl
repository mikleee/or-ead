<html>
<#include "head.ftl">
<body>

<link rel="stylesheet" href="../css/dashboard.css">
<script src="../js/conversations.js"></script>
<script src="../js/lost-state-updates.js"></script>
<script src="../js/address-appointment-tools.js"></script>
<script src="../js/dashboard.js"></script>

<#include "./templates/navigation.ftl">

<div class="page-content section" ng-app="dashboard">
    <div class="section-header">
        <div class="nav-block" ng-controller="dashboardTabsController as dtc" ng-cloak>
            <div class="nav-item">
                <a ng-class="{'active' : dtc.tabs.isActiveTab('conversations')}" href="#/conversations" ng-click="dtc.tabs.setActiveTab('conversations')">Conversations</a>
            </div>
            <div class="nav-item">
                <a ng-class="{'active' : dtc.tabs.isActiveTab('lost-state-updates')}" href="#/lost-state-updates" ng-click="dtc.tabs.setActiveTab('lost-state-updates')">Lost state update notifications</a>
            </div>
            <div class="nav-item">
                <a ng-class="{'active' : dtc.tabs.isActiveTab('address-appointment-tools')}" href="#/address-appointment-tools" ng-click="dtc.tabs.setActiveTab('address-appointment-tools')">Address and Appointment tools</a>
            </div>
        </div>
    </div>
    <div ng-view class="section-body"></div>
</div>

</body>
</html>