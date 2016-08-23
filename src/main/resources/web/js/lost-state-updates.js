angular.module('lostStateUpdates', ['ng-ext-plugins'])
    .controller('lostStateUpdatesController', ['$scope', 'lostStateUpdatesService', 'paginatorServiceFactory', LostStateUpdatesController])
    .service('lostStateUpdatesService', ['$http', LostStateUpdatesService]);

/**
 * @param scope
 * @param lostStateUpdatesService
 * @param paginatorServiceFactory
 * @constructor
 */
function LostStateUpdatesController(scope, lostStateUpdatesService, paginatorServiceFactory) {
    scope.update = null;
    scope.paginator = paginatorServiceFactory.staticPaginator();
    scope.viewState = new ViewSate().setInProgress();
    scope.selectStateUpdate = selectStateUpdate;
    scope.resend = resend;
    scope.remove = remove;


    lostStateUpdatesService.load().then(function (response) {
        var updates = lostStateUpdatesService.retrieveUpdates(response);
        scope.paginator.initPaginator(updates);
        scope.viewState.fromResponse(response);
    });

    function selectStateUpdate(u) {
        scope.update = u;
    }

    function resend(u) {
        u.viewState.setInProgress();
        lostStateUpdatesService.resend(u).then(function (response) {
            if (response.data.status == 'ok') {
                if (response.data.body.status == 'SENT') {
                    u.viewState.setSuccess();
                    scope.paginator.remove(u);
                } else {
                    u.fromObject(response.data.body);
                    u.viewState.setFail();
                }
            } else {
                u.viewState.setFail();
            }
        }, u.viewState.setFail);
    }

    function remove(u) {
        u.viewState.setInProgress();
        lostStateUpdatesService.remove(u).then(function (response) {
            if (response.data.status == 'ok') {
                u.viewState.setSuccess();
                scope.paginator.remove(u);
            } else {
                u.viewState.setFail();
            }
        }, u.viewState.setFail);
    }

}

function LostStateUpdatesService(http) {
    this.load = load;
    this.resend = resend;
    this.remove = remove;
    this.retrieveUpdates = retrieveUpdates;


    function load() {
        return http.get('/loadLostStateUpdates');
    }

    function resend(u) {
        return http.post('/resendLostStateUpdate', {id: u.id});
    }

    function remove(u) {
        return http.post('/removeLostStateUpdate', {id: u.id});
    }

    function retrieveUpdates(response) {
        var result = [];
        angular.forEach(response.data.body, function (v) {
            result.push(new StateUpdate().fromObject(v));
        });
        return result;
    }

}

function StateUpdate() {
    this.ttbState = null;
    this.ttbErrorCode = null;
    this.ttbErrorDescription = null;
    this.sendingFailedReason = null;
    this.conversationId = null;
    this.status = null;
    this.viewState = new ViewSate();
}
StateUpdate.prototype = new BaseModel();