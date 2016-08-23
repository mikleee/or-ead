const
    RESPONSE_OK = 'ok',
    RESPONSE_ERROR = 'error';

(function () {
    if (!window.application) {
        window.application = {};

        window.application.sequence = new Sequence();

        function Sequence() {
            var cursor = Math.ceil(Math.random() * 1000 + 1000);
            this.getUnique = function () {
                return cursor++;
            }
        }

    }
})();


/**
 * @constructor
 */
function BaseModel() {
    this.id = null;
    this.lastUpdate = new Date();
    this.hash = 0;

    this.fromObject = function (o) {
        for (var k in this) {
            var valueToSet = o[k];

            if (valueToSet == null) continue;

            if (this[k] instanceof Date) {
                this[k] = new Date(valueToSet);
            } else if (this[k] instanceof BaseModel) {
                this[k] = this[k].fromObject(valueToSet);
            } else {
                this[k] = valueToSet;
            }
        }
        return this;
    };

    this.equals = function (o) {
        if (o == null) return false;
        if (this == o) return true;
        return typeof this == typeof o && this.hash == o.hash;
    }
}

/**
 * @constructor
 */
function ViewSate() {
    var me = this;

    this.failed = false;
    this.success = false;
    this.inProgress = false;
    this.internalError = false;
    this.message = null;

    this.setSuccess = function () {
        me.failed = false;
        me.success = true;
        me.inProgress = false;
        me.internalError = false;

    };
    this.setFail = function () {
        me.failed = true;
        me.success = false;
        me.inProgress = false;
        me.internalError = false;
        return me;
    };
    this.setInProgress = function () {
        me.failed = false;
        me.success = false;
        me.inProgress = true;
        me.internalError = false;
        return me;
    };
    this.setInternalError = function () {
        me.failed = false;
        me.success = false;
        me.inProgress = false;
        me.internalError = true;
        return me;
    };
    this.fromResponse = function (response) {
        switch (response.data.status) {
            case RESPONSE_OK:
                return me.setSuccess();
            case RESPONSE_ERROR:
                me.message = response.data.body;
                return me.setFail();
            default:
                return me
        }
    };

}

/**
 * @constructor
 */
function KeyValue(k, v) {
    this.key = k;
    this.value = v;
}

/**
 * @param tabs {String[]}
 * @constructor
 */
function TabService(tabs) {
    var active = tabs[0];
    var checkTab = function (tab) {
        for (var i = 0; i < tabs.length; i++) {
            if (tabs[i] == tab) {
                return;
            }
        }
        throw new Error('tab ' + tab + ' is incorrect');
    };

    this.setActiveTab = function (tab) {
        checkTab(tab);
        active = tab;
    };
    this.isActiveTab = function (tab) {
        checkTab(tab);
        return active == tab;
    };
    this.getActiveTab = function () {
        return active;
    }

}

function Message() {
    const me = this;
    this.message = null;
    this.cssClass = '';

    this.success = function (m) {
        me.cssClass = 'alert alert-success alert-xs';
        me.message = m;
        return me;
    };
    this.error = function (m) {
        me.cssClass = 'alert alert-danger alert-xs';
        me.message = m;
        return me;
    };
    this.clear = function () {
        me.message = null;
        return me;
    };

}

function User(id, username, password) {
    this.id = id;
    this.userName = username;
    this.password = password;
    this.viewState = new ViewSate();
}