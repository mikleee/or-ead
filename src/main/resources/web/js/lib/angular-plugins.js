(function () {
    var util = {
        fm: {
            resolveBooleanAsyncValidation: function (response, deferred) {
                var data = response.data;
                if (data.status == 'ok' && data.body === true) {
                    deferred.resolve();
                } else {
                    deferred.reject();
                }
            }
        }
    };

    function HighlightedArea() {
        return {
            restrict: 'A',
            link: link
        };

        function link(scope, element, attrs) {
            var modelKey = attrs['highlight'];
            scope.$parent.$watch(modelKey, function (n) {
                element.text(n);
                hljs.highlightBlock(element[0]);
            });
        }
    }

    function Spinner() {
        return {
            restrict: 'E',
            template: '<span><img src="../../img/spin.gif"/></span>'
        };
    }

    function ConfirmPassword() {
        return {
            require: '?ngModel',
            link: function (scope, elm, attrs, ctrl) {
                ctrl.$validators.match = function (v) {
                    return ctrl.$$parentForm.newPassword.$modelValue === v;
                };
            }
        };
    }

    function UserPassword(http, $q) {
        return {
            restrict: 'A',
            require: '?ngModel',
            link: link
        };

        //noinspection JSUnusedLocalSymbols
        function link(scope, elm, attrs, ctrl) {
            ctrl.$asyncValidators.userPassword = function (v) {
                var def = $q.defer();
                if (ctrl.$isEmpty(v) || (ctrl.$invalid === true && !ctrl.$error.userPassword)) {
                    def.resolve();
                } else {
                    //noinspection JSUnresolvedVariable
                    var user = {id: ctrl.$$parentForm.userId.$modelValue, password: v};
                    http.post('/checkPassword', user).then(function (response) {
                        util.fm.resolveBooleanAsyncValidation(response, def);
                    }, def.reject);
                }

                return def.promise;
            };
        }
    }


    function UserName(http, $q) {
        return {
            restrict: 'A',
            require: '?ngModel',
            link: link
        };

        //noinspection JSUnusedLocalSymbols
        function link(scope, elm, attrs, ctrl) {
            ctrl.$asyncValidators.userName = function (v) {
                var def = $q.defer();
                if (ctrl.$isEmpty(v) || (ctrl.$invalid === true && !ctrl.$error.userName)) {
                    def.resolve();
                } else {
                    //noinspection JSUnresolvedVariable
                    var id = ctrl.$$parentForm.userId ? ctrl.$$parentForm.userId.$modelValue : null;
                    http.post('/public/checkUserName', {id: id, userName: v}).then(function (response) {
                        util.fm.resolveBooleanAsyncValidation(response, def);
                    }, def.reject);
                }

                return def.promise;
            };
        }
    }

    function FmErrorTooltip() {
        return {
            restrict: 'E',
            require: '?ngModel',
            template: template
        };

        //noinspection JSUnusedLocalSymbols
        function template(e, attrs) {
            var result = angular
                .element('<span class="ng-invalid glyphicon glyphicon-question-sign" popover-trigger="mouseenter" popover-placement="right">')
                .attr('uib-popover', attrs['message']);
            return result[0].outerHTML;
        }
    }

    angular.module('directives', [])
        .directive('highlight', [HighlightedArea])
        .directive('confirmPassword', ['$http', ConfirmPassword])
        .directive('userPassword', ['$http', '$q', UserPassword])
        .directive('userName', ['$http', '$q', UserName])
        .directive('spinner', [Spinner])
        .directive('fmErrorTooltip', [FmErrorTooltip]);

})();


(function () {
    angular.module('pagination', ['ui.bootstrap'])
        .controller('paginatorController', ['$scope', PaginatorController])
        .service('paginatorServiceFactory', ['$http', PaginatorServiceFactory])
        .directive('paginator', [Paginator])

        .controller('sorterController', ['$scope', SorterController])
        .directive('sorter', [Sorter]);

    const comparator = new ComparatorService();


    function PaginatorDelegate() {
        var pageSizes = [5, 10, 20, 75];

        this.prepareData = prepareData;
        this.isActual = isActual;
        this.getApplicablePageSizes = getApplicablePageSizes;
        this.getDefaultPageSize = getDefaultPageSize;


        function prepareData(d) {
            angular.forEach(d, function (v) {
                if (v['_pid'] == null) {
                    v['_pid'] = application.sequence.getUnique();
                }
            });
            return d;
        }

        function isActual(totalCount) {
            return isPageSizeApplicable(totalCount, pageSizes[0]);
        }

        function getApplicablePageSizes(totalCount) {
            var result = [];
            for (var i = 0; i < pageSizes.length; i++) {
                var pSize = pageSizes[i];
                if (isPageSizeApplicable(totalCount, pSize)) {
                    result.push(pSize);
                } else {
                    break;
                }
            }
            if (totalCount > result[result.length - 1]) {
                result.push(totalCount);
            }
            return result;
        }

        function getDefaultPageSize() {
            return pageSizes[0];
        }

        function isPageSizeApplicable(totalCount, pSize) {
            return totalCount / pSize > 1;
        }

    }

    /**
     * @abstract
     */
    function AbstractPaginatorService() {
        this.pageSize = null;
        this.pageNo = null;
        this.sortOptions = null;

        this.isActual = notImplemented;
        this.getApplicablePageSizes = notImplemented;
        this.initPaginator = notImplemented;
        this.getPageContent = notImplemented;
        this.isPagesLineActual = notImplemented;
        this.setPageSize = notImplemented;
        this.getPageSize = notImplemented;
        this.getTotalCount = notImplemented;
        this.sort = notImplemented;
        this.onPageChange = notImplemented;

        function notImplemented() {
            throw new Error('Not implemented');
        }

    }

    /**
     * @extends {AbstractPaginatorService}
     * @constructor
     */
    function StaticPaginationService() {
        const me = this, sorter = new StaticSorterService(), delegate = new PaginatorDelegate();
        var matrix = [], data = [];

        this.pageSize = delegate.getDefaultPageSize();
        this.pageNo = 1;
        this.sortOptions = new SortOptions();

        this.initPaginator = initPaginator;
        this.getTotalCount = getTotalCount;
        this.isActual = isActual;
        this.getApplicablePageSizes = getApplicablePageSizes;
        this.getPageContent = getPageContent;
        this.isPagesLineActual = isPagesLineActual;
        this.setPageSize = setPageSize;
        this.getPageSize = getPageSize;
        this.focus = focus;
        this.remove = remove;
        this.sort = sort;
        this.onPageChange = onPageChange;


        function initPaginator(d) {
            if (!angular.isArray(d)) throw new Error('The argument must be array type');

            data = delegate.prepareData(d);
            matrix = arrayToMatrix(data);
        }

        function getTotalCount() {
            return data.length;
        }

        function isActual() {
            return delegate.isActual(getTotalCount());
        }

        function getApplicablePageSizes() {
            return delegate.getApplicablePageSizes(getTotalCount());
        }

        function getPageContent() {
            var pIndex = me.pageNo - 1;
            return matrix.length < pIndex ? [] : matrix[pIndex];
        }

        function isPagesLineActual() {
            return matrix.length > 1;
        }

        function setPageSize(pSize) {
            me.pageNo = 1;
            me.pageSize = pSize;
            matrix = arrayToMatrix(data);
        }

        function getPageSize() {
            return me.pageSize;
        }

        function focus(item, comparator) {
            function isMatch(input, inner) {
                if (comparator) {
                    return comparator(input, inner);
                } else {
                    return input['_pid'] == inner['_pid'];
                }
            }

            for (var page = 0; page < matrix.length; page++) {
                for (var i = 0; i < matrix[page].length; i++) {
                    if (isMatch(item, matrix[page][i])) {
                        me.pageNo = page + 1;
                        return matrix[page][i];
                    }
                }
            }
            return null;
        }

        function remove(o) {
            for (var i = 0; i < data.length; i++) {
                if (data[i]['_pid'] == o['_pid']) {
                    data.splice(i, 1);
                    break;
                }
            }
            matrix = arrayToMatrix(data);
        }

        function sort(sortOptions) {
            if (data) {
                me.sortOptions = sortOptions;
                var d = sorter.sort(data, me.sortOptions);
                initPaginator(d);
            }
        }

        function onPageChange() {
        }

        function arrayToMatrix(arr) {
            var result = [];
            for (var i = 0; i < arr.length;) {
                var sub = [];
                for (var j = 0; j < me.pageSize && i < arr.length; j++, i++) {
                    sub.push(arr[i]);
                }
                result.push(sub);
            }
            return result;
        }
    }

    StaticPaginationService.prototype = new AbstractPaginatorService();

    /**
     * @extends {AbstractPaginatorService}
     * @constructor
     */
    function DynamicPaginationService(http) {
        const me = this, delegate = new PaginatorDelegate();

        var callback = {success: null, error: null, beforeRequest: null};
        var state = {totalCount: 0, pageContent: []};
        var requestUrl = null;


        this.pageSize = delegate.getDefaultPageSize();
        this.pageNo = 1;
        this.sortOptions = new SortOptions();

        this.initPaginator = initPaginator;
        this.getTotalCount = getTotalCount;
        this.isActual = isActual;
        this.getApplicablePageSizes = getApplicablePageSizes;
        this.getPageContent = getPageContent;
        this.isPagesLineActual = isPagesLineActual;
        this.setPageSize = setPageSize;
        this.getPageSize = getPageSize;
        this.sort = sort;
        this.onPageChange = onPageChange;


        function initPaginator(url, config) {
            if (!url) throw new Error('Url must be specified');
            if (!config.success) throw new Error('Success callback must be specified');

            requestUrl = url;
            callback = config;
            sendRequest();
        }

        function getTotalCount() {
            return state.totalCount;
        }

        function isActual() {
            return delegate.isActual(getTotalCount());
        }

        function getApplicablePageSizes() {
            return delegate.getApplicablePageSizes(getTotalCount());
        }

        function getPageContent() {
            return state.pageContent;
        }

        function isPagesLineActual() {
            return getTotalCount() / me.pageSize >= 2;
        }

        function setPageSize(pSize) {
            me.pageNo = 1;
            me.pageSize = pSize;
            sendRequest();
        }

        function getPageSize() {
            return me.pageSize;
        }

        function sort(sortOptions) {
            me.sortOptions = sortOptions;
            sendRequest();
        }

        function onPageChange() {
            sendRequest();
        }


        function sendRequest() {
            if (callback.beforeRequest) {
                callback.beforeRequest();
            }

            var request = {
                dir: me.sortOptions.dir,
                field: me.sortOptions.key,
                page: me.pageNo,
                size: me.pageSize
            };

            http.post(requestUrl, request).then(function (response) {
                state = callback.success(response);
                state.pageContent = delegate.prepareData(state.pageContent);
            }, function (response) {
                if (callback.error) {
                    callback.error(response)
                }
            });
        }

    }

    DynamicPaginationService.prototype = new AbstractPaginatorService();


    function StaticSorterService() {
        this.sort = sort;

        function sort(collection, sortOptions) {
            if (collection.length > 0) {
                var fn = getComparator(collection[0][sortOptions.key]);
                var result = collection.sort(function (a, b) {
                    return fn(a[sortOptions.key], b[sortOptions.key]);
                });

                if (sortOptions.dir == 'desc') {
                    return result.reverse();
                } else {
                    return result;
                }
            }
        }

        function getComparator(v) {
            if (angular.isString(v)) {
                return comparator.compareStrings;
            } else if (angular.isNumber(v)) {
                return comparator.compareNumbers;
            } else if (angular.isDate(v)) {
                return comparator.compareDates
            } else {
                throw new Error(typeof v + ' is not supported to sort');
            }
        }
    }

    function ComparatorService() {
        this.compareStrings = compareStrings;
        this.compareNumbers = compareNumbers;
        this.compareDates = compareDates;


        function compareStrings(a, b) {
            return a > b ? 1 : a == b ? 0 : -1;
        }

        function compareNumbers(a, b) {
            return a - b;
        }

        function compareDates(a, b) {
            return compareNumbers(a.getTime(), b.getTime());
        }
    }

    function PaginatorServiceFactory(http) {
        this.staticPaginator = function () {
            return new StaticPaginationService();
        };
        this.dynamicPaginator = function () {
            return new DynamicPaginationService(http);
        }
    }

    function PaginatorController(scope) {

    }

    function Paginator() {
        return {
            controller: 'paginatorController',
            restrict: 'E',
            scope: {
                paginator: '=paginatorService'
            },
            templateUrl: '/directive-template/paginator'
        };
    }

    function Sorter() {
        return {
            controller: 'sorterController',
            scope: {
                sortKey: '@field',
                paginator: '=paginatorService'
            },
            restrict: 'E',
            template: '<span class="sorter glyphicon" ng-click="sort()" ng-class="styleClass()"></span>'
        };
    }


    function SorterController(scope) {
        scope.sortOptions = new SortOptions(scope.sortKey);
        scope.isActive = isActive;
        scope.sort = sort;
        scope.styleClass = styleClass;


        function sort() {
            if (isActive()) {
                scope.sortOptions.toggle();
            } else {
                scope.sortOptions.dir = 'asc'
            }

            scope.paginator.sort(scope.sortOptions);
        }

        function isActive() {
            return scope.paginator && scope.paginator.sortOptions && scope.paginator.sortOptions.key == scope.sortOptions.key;
        }

        function styleClass() {
            if (isActive() && scope.sortOptions.dir == 'asc') {
                return 'glyphicon-sort-by-attributes active-sorter';
            } else if (isActive() && scope.sortOptions.dir == 'desc') {
                return 'glyphicon-sort-by-attributes-alt active-sorter'
            } else if (!isActive() || !scope.sortOptions.dir) {
                return 'glyphicon-sort'
            } else {
                return ''
            }
        }
    }

    function SortOptions(key) {
        const me = this;
        this.dir = null;
        this.key = key;

        this.toggle = function () {
            if (me.dir == 'asc') {
                me.dir = 'desc';
            } else if (me.dir == 'desc') {
                me.dir = 'asc';
            }
        }
    }


})();

(function () {
    angular.module('ng-ext-plugins', ['directives', 'pagination'])
})();


