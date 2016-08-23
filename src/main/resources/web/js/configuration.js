angular.module('configuration', ['ng-ext-plugins'])
    .controller('environmentController', ['$scope', 'environmentService', EnvironmentController])
    .service('environmentService', ['$http', EnvironmentService]);

function EnvironmentController(scope, environmentService) {
    scope.staticEnvironment = new Environment();
    scope.dynamicConfiguration = new Environment();
    scope.updateConfiguration = updateConfiguration;


    environmentService.loadStaticEnvironment().then(function (response) {
        scope.staticEnvironment.setData(response.data.body);
    });

    environmentService.loadDynamicConfiguration().then(function (response) {
        var data = environmentService.retrieveDynamicConfiguration(response);
        scope.dynamicConfiguration.setData(data);
    });

    function updateConfiguration(fm) {
        environmentService.updateConfiguration(scope.dynamicConfiguration.data).then(function (response) {
            var data = environmentService.retrieveDynamicConfiguration(response);
            scope.dynamicConfiguration.setData(data);
            fm.$submitted = false;
        });
    }

}

function EnvironmentService(http) {
    var currentConfig = {};

    this.loadStaticEnvironment = loadStaticEnvironment;
    this.loadDynamicConfiguration = loadDynamicConfiguration;
    this.retrieveDynamicConfiguration = retrieveDynamicConfiguration;
    this.updateConfiguration = updateConfiguration;

    function loadStaticEnvironment() {
        return http.get('/loadStaticEnvironment');
    }

    function loadDynamicConfiguration() {
        var promise = http.get('/loadDynamicConfiguration');
        promise.then(initCurrentConfig);
        return promise;
    }

    function retrieveDynamicConfiguration(response) {
        var result = [];
        angular.forEach(response.data.body, function (v) {
            if (v.value === 'true' || v.value === 'false') {
                v.type = 'checkbox';
                v.value = v.value === 'true';
            } else {
                v.type = 'text';
            }
            result.push(v);
        });
        return result;
    }

    function updateConfiguration(data) {
        data = filterChangedConfigs(data);
        var promise = http.post('/updateConfiguration', {data: data});
        promise.then(initCurrentConfig);
        return promise;
    }

    function initCurrentConfig(response) {
        angular.forEach(response.data.body, function (v) {
            if (v.value === 'true' || v.value === 'false') {
                currentConfig[v.key] = v.value === 'true';
            } else {
                currentConfig[v.key] = v.value;
            }
        });
    }

    function filterChangedConfigs(data) {
        var result = [];
        angular.forEach(data, function (v) {
            if (currentConfig[v.key] !== v.value) {
                result.push(v);
            }
        });
        return result;
    }
}

function Environment() {
    var me = this;
    this.viewState = new ViewSate().setInProgress();
    this.data = [];

    this.setData = function (d) {
        me.data = d;
        me.viewState.setSuccess();
    }
}