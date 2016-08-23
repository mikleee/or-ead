<div class="table-paginator" ng-if="paginator.isActual()">
    <div class="page-size">
        <div class="btn-group" uib-dropdown>
            <button id="btn-append-to-body" type="button" class="btn btn-default btn-sm" uib-dropdown-toggle><span ng-bind="paginator.getPageSize()"></span> <span class="caret"></span></button>
            <ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body">
                <li role="menuitem" ng-repeat="ps in paginator.getApplicablePageSizes()" ng-click="paginator.setPageSize(ps)">
                    <a href="javascript:void(0)"><span ng-bind="ps"></span></a>
                </li>
            </ul>
        </div>
    </div>
    <div ng-if="paginator.isPagesLineActual()">
        <uib-pagination boundary-links="true" direction-links="false"
                        items-per-page="paginator.pageSize"
                        total-items="paginator.getTotalCount()"
                        ng-model="paginator.pageNo"
                        ng-change="paginator.onPageChange()"
                        max-size="8"
                        class="pagination-sm"
                        first-text="&laquo;" last-text="&raquo;">
        </uib-pagination>
    </div>
</div>