<#-- @ftlvariable name="$uri" type="java.lang.String" -->
<#-- @ftlvariable name="$user" type="uk.co.virtual1.model.persist.User" -->

<#if $user??>
<div class="page-header">
    <div class="nav-block">
        <div class="nav-item"><a <#if "/dashboard" == $uri>class="active"</#if> href="/dashboard">Dashboard</a></div>
        <div class="nav-item"><a <#if "/configuration" == $uri>class="active"</#if> href="/configuration">Configuration</a></div>
    </div>
    <div class="nav-block">
        <div class="nav-item"><a <#if "/account" == $uri>class="active"</#if> href="/account"><span title="${$user.userName}" class="glyphicon glyphicon-user"></span> </a></div>
        <div class="nav-item"><a href="/logout"><span title="Sign out" class="glyphicon glyphicon-log-out"></span></a></div>
    </div>
</div>
</#if>
