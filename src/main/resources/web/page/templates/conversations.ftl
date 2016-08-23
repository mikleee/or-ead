<#-- @ftlvariable name="sfSiteUrl" type="java.lang.String" -->
<link rel="stylesheet" href="../../css/conversations.css">

<div class="section" ng-controller="responseCodesController">
    <div class="section-header">Response codes library</div>
    <div class="section-body">
        <p>Talk Talk response codes for LLU commands</p>
        <form class="form-inline">
            <div class="form-group">
                <label for="responseCode">Response code</label>
                <input id="responseCode" class="form-control" type="text" ng-model="responseCode.code"/>
            </div>
            <button class="btn btn-primary btn-sm" ng-click="loadResponseCode()" ng-disabled="!responseCode.code">Find</button>
            <span class="alert alert-info alert-xs" ng-show="!responseCode.viewState.inProgress && responseCode.description" ng-bind="responseCode.description"></span>
            <span spinner ng-show="responseCode.viewState.inProgress"></span>
        </form>
    </div>
</div>

<div class="section conversations" ng-controller="conversationsController">
    <div class="section-header">Orders are currently in progress</span></div>
    <div class="section-body">
        <h2 ng-show="viewState.inProgress" class="centered"><spinner></spinner></h2>
        <div class="conversation-list cell" ng-if="!viewState.inProgress">
            <div ng-if="conversationsPaginator.getTotalCount() == 0" class="alert alert-warning">No conversations found</div>
            <div ng-if="conversationsPaginator.getTotalCount() > 0">
                <table class="table table-hover table-condensed table-clickable">
                    <caption><span>Conversations </span><span class="badge">{{conversationsPaginator.getTotalCount()}}</caption>
                    <thead>
                        <tr>
                            <th><span>Id</span></th>
                            <th><span>Last update </span><sorter field="lastUpdate" paginator-service="conversationsPaginator"></sorter></th>
                            <th><span>Type </span> <sorter field="typeDescription" paginator-service="conversationsPaginator"></sorter></th>
                            <th><span>TTB state </span><sorter field="ttbState" paginator-service="conversationsPaginator"></sorter></th>
                            <th><span>TTB command id </span></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="c in conversationsPaginator.getPageContent() track by c.id" ng-click="selectConversation(c)" ng-class="{'info': c.equals(conversation)}">
                            <td onclick="event.stopPropagation()">
                                <a target="_blank" href="${sfSiteUrl}/{{c.caseId}}"
                                   popover-trigger="mouseenter" popover-placement="right" uib-popover="Show the the linked case {{c.caseId}} in salesforce">{{c.id}}</a>
                            </td>
                            <td>{{c.lastUpdate | date:'yyyy-MM-dd HH:mm'}}</td>
                            <td>
                                <span ng-if="c.targetConversation" onclick="event.stopPropagation()">
                                    <a href="javascript:void(0)" ng-click="focusTargetConversation(c)"
                                       popover-trigger="mouseenter" popover-placement="top" uib-popover="Jump to the target conversation">{{c.typeDescription}}</a>
                                </span>
                                <span ng-if="!c.targetConversation">{{c.typeDescription}}</span>
                            </td>
                            <td>{{c.ttbState}}</td>
                            <td>{{c.ttbCommandId}}</td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="6">
                                <paginator paginator-service="conversationsPaginator"></paginator>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <div class="messages-list cell" ng-if="conversation">
            <h2 ng-show="conversation.viewState.inProgress" class="centered"><spinner></spinner></h2>
            <div ng-if="!conversation.viewState.inProgress">
                <div ng-if="messagesPaginator.getTotalCount() == 0" class="alert alert-danger"> No messages found for conversation {{conversation.id}}</div>
                <div ng-if="messagesPaginator.getTotalCount() > 0">
                    <table class="table table-hover table-condensed table-clickable">
                        <caption><span>Messages for {{conversation.id}} conversation </span><span class="badge">{{messagesPaginator.getTotalCount()}}</span></caption>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th><span>Last update</span><sorter field="lastUpdate" paginator-service="messagesPaginator"></sorter></th>
                                <th><span>Status</span><sorter field="status" paginator-service="messagesPaginator"></sorter></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="m in messagesPaginator.getPageContent() track by m.id" ng-click="selectMessage(m)" ng-class="{'info': m.equals(message)}">
                                <td>{{m.id}}</td>
                                <td>{{m.lastUpdate | date:'yyyy-MM-dd HH:mm'}}</td>
                                <td>{{m.status}}</td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="5">
                                    <paginator paginator-service="messagesPaginator"></paginator>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div ng-if="conversation && message && message.body">
        <h4>Message content</a></h4>
        <pre highlight="message.body" class="xml message-body"></pre>
    </div>
</div>