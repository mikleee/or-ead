function mainTabsController(scope, location) {
    var me = this;
    this.tabs = new TabService(['conversations', 'lost-state-updates', 'address-appointment-tools']);

    {   // init
        var path = location.path();
        if (path == '/conversations') {
            me.tabs.setActiveTab('conversations');
        } else if (path == '/lost-state-updates') {
            me.tabs.setActiveTab('lost-state-updates');
        } else if (path == '/address-appointment-tools') {
            me.tabs.setActiveTab('address-appointment-tools');
        } else {
            me.tabs.setActiveTab('conversations');
        }
    }
}

angular.module('dashboard', ['ngRoute', 'conversations', 'lostStateUpdates', 'addressAppointmentTools'])
    .controller('dashboardTabsController', ['$scope', '$location', mainTabsController])
    .config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/conversations', {
                templateUrl: '/conversations'
            })
            .when('/lost-state-updates', {
                templateUrl: '/lost-state-updates'
            })
            .when('/address-appointment-tools', {
                templateUrl: '/address-appointment-tools'
            })
            .otherwise({
                templateUrl: '/conversations'
            });
    }]);