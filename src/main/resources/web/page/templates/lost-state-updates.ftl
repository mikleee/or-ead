<link rel="stylesheet" href="../../css/lost-state-updates.css">

<div class="state-updates section" ng-controller="lostStateUpdatesController">
    <div class="section-header">Lost status updates</div>
    <div class="section-body">
        <h2 ng-show="viewState.inProgress" class="centered"><spinner></spinner></h2>
        <div class="state-updates-list cell" ng-if="!viewState.inProgress">
            <div ng-if="paginator.getTotalCount() == 0" class="alert alert-success"> No lost notifications found</div>
            <div ng-if="paginator.getTotalCount() > 0">
                <table class="table table-hover table-condensed table-clickable">
                    <caption> Updates <span class="badge">{{paginator.getTotalCount()}}</span></caption>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th><span>Last update</span><sorter field="lastUpdate" paginator-service="paginator"></sorter></th>
                            <th><span>Conversation</span><sorter field="conversationId" paginator-service="paginator"></sorter></th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="u in paginator.getPageContent() track by u.id" ng-click="selectStateUpdate(u)" ng-class="{'v-state-success' : u.viewState.success}">
                            <td>{{u.id}}</td>
                            <td>{{u.lastUpdate | date:'yyyy-MM-dd HH:mm'}}</td>
                            <td>{{u.conversationId}}</td>
                            <td>{{u.status}}</td>
                            <td onclick="event.stopPropagation()">
                                <div class="btn-group" ng-class="{'info': u.equals(update), 'v-state-success' : u.viewState.success, 'v-state-fail' : u.viewState.failed,'v-state-in-progress' : u.viewState.inProgress}">
                                    <button type="button" class="btn btn-xs btn-success" ng-disabled="u.viewState.inProgress" ng-click="resend(u)">
                                        <span class="glyphicon glyphicon-envelope"></span>
                                    </button>
                                    <button type="button" class="btn btn-xs btn-danger" ng-disabled="u.viewState.inProgress" ng-click="remove(u)">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="5">
                                <paginator paginator-service="paginator"></paginator>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <div ng-if="update" class="sf-notification-details cell">
            <div class="alert alert-info"><b>Received state:</b> <i>{{update.ttbState}}</i></div>
            <div ng-show="update.sendingFailedReason" class="alert alert-danger"><b>Sending failed reason:</b> <i>{{update.sendingFailedReason}}</i></div>
            <div ng-show="update.ttbErrorCode" class="alert alert-warning"><b>Response error:</b> <i>{{update.ttbErrorCode}} - {{update.ttbErrorDescription}}</i></div>
        </div>
    </div>
</div>