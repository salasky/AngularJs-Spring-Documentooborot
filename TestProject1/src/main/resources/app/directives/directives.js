angular
    .module('app').directive('compileHtml', ['$sce', '$parse', '$compile',
    function ($sce, $parse, $compile) {
        return {
            restrict: 'A',
            compile: function ngBindHtmlCompile(tElement, tAttrs) {
                var ngBindHtmlGetter = $parse(tAttrs.compileHtml);
                var ngBindHtmlWatch = $parse(tAttrs.compileHtml, function getStringValue(value) {
                    return (value || '').toString();
                });
                $compile.$$addBindingClass(tElement);

                return function ngBindHtmlLink(scope, element, attr) {
                    $compile.$$addBindingInfo(element, attr.compileHtml);

                    scope.$watch(ngBindHtmlWatch, function ngBindHtmlWatchAction() {

                        element.html($sce.trustAsHtml(ngBindHtmlGetter(scope)) || '');
                        $compile(element.contents())(scope);
                    });
                };
            }
        };
    }
])