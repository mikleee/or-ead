<#-- @ftlvariable name="appointmentTypes" type="java.util.List<uk.co.opalonline.webservices.appointingservice.AppointmentTypeEnum>" -->
<#-- @ftlvariable name="serviceTypes" type="java.util.List<uk.co.opalonline.webservices.llu.linecharacteristicsws.ServiceTypeEnum>" -->
<link rel="stylesheet" href="../../css/address-appointment-tools.css">
<div>
    <div class="address-tools section" ng-controller="addressController">
        <div class="section-header">Address tools</div>
        <div class="section-body">
            <div>
                <form name="f1" class="form-inline">
                    <div class="form-group">
                        <label for="postCode">Post code</label>
                        <input id="postCode" class="form-control" type="text" ng-model="addressDetails.postCode"/>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary btn-sm" ng-disabled="addressDetails.viewState.inProgress" ng-click="getAvailableAddresses()">Search for addresses</button>
                        <spinner ng-show="addressDetails.viewState.inProgress"></spinner>
                    </div>
                </form>
            </div>
            <div ng-if="addressDetails.viewState.failed" class="alert alert-danger">{{addressDetails.viewState.message}}</div>
            <div ng-if="addressDetails.viewState.success">
                <div class="addresses" ng-if="paginator.getTotalCount() > 0">
                    <table class="table table-hover table-condensed table-clickable">
                        <caption>Gold addresses <span class="badge">{{paginator.getTotalCount()}}</span></caption>
                        <tbody>
                            <tr ng-repeat="a in paginator.getPageContent()" class="address" ng-click="setActiveAddress(a)">
                                <td class="address">
                                    <div class="address-identifier">
                                        <span><span>Address reference</span>: <span>{{a.addressReference}}, </span></span>
                                        <span><span>Css district code</span>: <span>{{a.cssDistrictCode}}, </span></span>
                                        <span><span>Exchange code</span>: <span>{{a.exchangeCode}} </span></span>
                                    </div>
                                    <div class="address-details">
                                        <span ng-show="a.county"><span>County</span>: <span>{{a.county}}, </span></span>
                                        <span ng-show="a.town"><span>Town</span>: <span>{{a.town}}, </span></span>
                                        <span ng-show="a.locality"><span>Locality</span>: <span>{{a.locality}}, </span></span>
                                        <span ng-show="a.street"><span>Street</span>: <span>{{a.street}}, </span></span>
                                        <span ng-show="a.dependantThoroughfare"><span>Dependant thoroughfare</span>: <span>{{a.dependantThoroughfare}}, </span></span>
                                        <span ng-show="a.buildingNumber"><span>Building number</span>: <span>{{a.buildingNumber}}, </span></span>
                                        <span ng-show="a.buildingName"><span>Building name</span>: <span>{{a.buildingName}}, </span></span>
                                        <span ng-show="a.subBuilding"><span>Sub building</span>: <span>{{a.subBuilding}}, </span></span>
                                        <span ng-show="a.organisationName"><span>Organisation name</span>: <span>{{a.organisationName}} </span></span>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>
                                    <paginator paginator-service="paginator"></paginator>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div ng-if="paginator.getTotalCount() == 0" class="alert alert-warning">No available addresses found</div>
            </div>
        </div>
    </div>
    <div class="appointment-tools section" ng-controller="appointmentController">
        <div class="section-header">Appointment tools</div>
        <div class="section-body">
            <div class="alert alert-danger">not implemented yet</div>
            <div>
                <form class="appointment-form">
                    <div class="form-group form-inline">
                        <label for="addressReference">Address reference</label>
                        <input id="addressReference" class="form-control" type="text" ng-model="addressService.activeAddress.addressReference"/>
                    </div>
                    <div class="form-group form-inline">
                        <label for="cssDistrictCode">Css district code</label>
                        <input id="cssDistrictCode" class="form-control" type="text" ng-model="addressService.activeAddress.cssDistrictCode"/>
                    </div>
                    <br/>
                    <div class="form-group form-inline">
                        <label for="appointmentType">Appointment type</label>
                        <select id="appointmentType" class="form-control">
                        <#list appointmentTypes as t>
                            <option value="${t.name()}">${t.value()}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="form-group form-inline">
                        <label for="serviceType">Service type</label>
                        <select id="serviceType" class="form-control">
                        <#list serviceTypes as t>
                            <option value="${t.name()}">${t.value()}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="form-group form-inline">
                        <label for="appointmentRequiredDate">Required date</label>
                        <input id="appointmentRequiredDate" class="form-control" type="text" uib-datepicker-popup ng-model="aaa" close-text="Close" is-open="adp.opened" ng-click="adp.opened = true"/>
                    </div>

                    <button class="btn btn-primary btn-sm">Search for available appointments</button>
                </form>
            </div>
        </div>
    </div>
</div>
