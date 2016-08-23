angular.module('conversations', ['ng-ext-plugins', 'ui.bootstrap'])
    .controller('conversationsController', ['$scope', 'conversationsService', 'paginatorServiceFactory', ConversationsController])
    .controller('responseCodesController', ['$scope', 'responseCodesService', ResponseCodesController])
    .service('conversationsService', ['$http', ConversationsService])
    .service('responseCodesService', ['$http', ResponseCodesService]);

/**
 * @constructor
 */
function ConversationsController(scope, conversationsService, paginatorServiceFactory) {
    scope.conversationsPaginator = paginatorServiceFactory.staticPaginator();
    scope.messagesPaginator = paginatorServiceFactory.staticPaginator();
    scope.conversation = null;
    scope.message = null;
    scope.viewState = new ViewSate().setInProgress();

    scope.selectConversation = selectConversation;
    scope.selectMessage = selectMessage;
    scope.focusTargetConversation = focusTargetConversation;


    conversationsService.loadConversations().then(function (response) {
        var cs = conversationsService.retrieveConversations(response);
        scope.conversationsPaginator.initPaginator(cs);
        scope.viewState.fromResponse(response);
    });


    function selectConversation(c) {
        scope.message = null;
        scope.conversation = c;
        loadMessages(c);
    }

    function selectMessage(m) {
        scope.conversationsPaginator.focus(scope.conversation);
        scope.message = m;
    }

    function focusTargetConversation(c) {
        var target = scope.conversationsPaginator.focus(c, function (c1, c2) {
            return c1.targetConversation == c2.id;
        });
        if (target) {
            selectConversation(target);
        }
    }

    function loadMessages(c) {
        conversationsService.loadMessages(c).then(function (response) {
            var ms = conversationsService.retrieveMessages(response);
            scope.messagesPaginator.initPaginator(ms);
            c.viewState.fromResponse(response);
        });
        c.viewState.setInProgress();
    }

}

/**
 * @constructor
 */
function ConversationsService(http) {
    this.loadConversations = loadConversations;
    this.loadMessages = loadMessages;
    this.retrieveConversations = retrieveConversations;
    this.retrieveMessages = retrieveMessages;


    function loadConversations() {
        return http.get('/loadConversations');
    }


    function loadMessages(c) {
        return http.get('/messages/' + c.id);
    }

    function retrieveConversations(response) {
        var result = [];
        angular.forEach(response.data.body, function (v) {
            result.push(new Conversation().fromObject(v));
        });
        return result;
    }

    function retrieveMessages(response) {
        var result = [];
        angular.forEach(response.data.body, function (v) {
            result.push(new TtbMessage().fromObject(v));
        });
        return result;
    }

}

/**
 * @constructor
 */
function ResponseCodesController(scope, responseCodesService) {
    scope.responseCode = {
        code: '',
        description: null,
        viewState: new ViewSate()
    };
    scope.loadResponseCode = loadResponseCode;

    scope.$watch('rcc.responseCode.code', onCodeChange);

    function loadResponseCode() {
        scope.responseCode.viewState.setInProgress();
        responseCodesService.loadResponseCode(scope.responseCode.code).then(function (response) {
            scope.responseCode.viewState.setSuccess();
            scope.responseCode.description = response.data.body;
        });
    }

    function onCodeChange(value) {
        scope.responseCode.description = null;
    }
}

/**
 * @constructor
 */
function ResponseCodesService(http) {
    this.loadResponseCode = function (code) {
        return http.get('/loadResponseCode/' + code);
    };
}


function Conversation() {
    this.type = null;
    this.typeDescription = null;
    this.caseId = null;
    this.ttbState = null;
    this.ttbCommandId = null;
    this.targetConversation = null;
    /**
     * @type {TtbMessage[]}
     */
    this.messages = [];
    this.viewState = new ViewSate();
}
Conversation.prototype = new BaseModel();

function TtbMessage() {
    this.status = null;
    this.body = null;
    this.conversationId = null;
}
TtbMessage.prototype = new BaseModel();

