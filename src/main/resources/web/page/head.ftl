<head>
    <title>Talk talk business provisioning</title>

    <script src="../js/lib/highlight.pack.js"></script>
    <script src="../js/lib/angular.min.js"></script>
    <script src="../js/lib/angular-animate.min.js"></script>
    <script src="../js/lib/angular-route.js"></script>
    <script src="../js/lib/ui-bootstrap-tpls-1.3.3.min.js"></script>
    <script src="../js/lib/angular-plugins.js"></script>
    <script src="../js/main.js"></script>
    <link rel="stylesheet" href="../css/lib/highlight.css">
    <link rel="stylesheet" href="../css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="../css/lib/bootstrap-ext.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/ng-directives.css">
</head>

<#-- @ftlvariable name="$user" type="uk.co.virtual1.model.persist.User" -->
<script>
    <#if $user??>
    application.currentUser = function () {
        return new User(${$user.id?c}, '${$user.userName}');
    };
    </#if>
</script>
<img src="../img/spin.gif" style="display: none">