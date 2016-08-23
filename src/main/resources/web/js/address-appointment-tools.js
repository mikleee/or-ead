angular.module('addressAppointmentTools', ['ng-ext-plugins', 'ui.bootstrap'])
    .controller('addressController', ['$scope', 'addressService', 'paginatorServiceFactory', AddressController])
    .controller('appointmentController', ['$scope', 'addressService', 'paginatorServiceFactory', AppointmentController])
    .service('addressService', ['$http', AddressService]);

/**
 * @constructor
 */
function AddressController(scope, addressService, paginatorServiceFactory) {
    scope.addressDetails = new AddressDetails();
    scope.paginator = paginatorServiceFactory.staticPaginator();
    scope.getAvailableAddresses = getAvailableAddresses;
    scope.setActiveAddress = setActiveAddress;


    function getAvailableAddresses() {
        addressService.getAvailableAddresses(scope.addressDetails).then(function (response) {
            if (response.data.status == RESPONSE_OK) {
                var result = addressService.retrieveAddresses(response);
                scope.paginator.initPaginator(result);
            }
            scope.addressDetails.viewState.fromResponse(response);
        });
        scope.addressDetails.viewState.setInProgress();
    }

    function setActiveAddress(a) {
        addressService.activeAddress = a;
    }

}

/**
 * @constructor
 */
function AppointmentController(scope, addressService, paginatorServiceFactory) {
    scope.addressService = addressService;
    scope.paginator = paginatorServiceFactory.staticPaginator();
    scope.aaa = null;

}

/**
 * @constructor
 */
function AddressService(http) {
    var me = this;
    this.activeAddress = null;
    this.getAvailableAddresses = getAvailableAddresses;
    this.retrieveAddresses = retrieveAddresses;


    function getAvailableAddresses(a) {
        return http.post('/availableAddresses', a);
    }


    function retrieveAddresses(response) {
        var result = [];
        angular.forEach(response.data.body, function (v) {
            if (v.qualifier == 'GOLD') {
                result.push(new AddressDetails().fromObject(v));
            }
        });
        return result;
    }
}

/**
 * @constructor
 */
function AddressDetails() {
    this.organisationName = null;
    this.subBuilding = null;
    this.buildingName = null;
    this.buildingNumber = null;
    this.dependantThoroughfare = null;
    this.street = null;
    this.locality = null;
    this.town = null;
    this.county = null;
    this.postCode = null;
    this.addressReference = null;
    this.cssDistrictCode = null;
    this.exchangeCode = null;
    this.qualifier = null;
    this.viewState = new ViewSate();
}
AddressDetails.prototype = new BaseModel();

function AppointmentRequest() {
    this.addressDetail = null;

}