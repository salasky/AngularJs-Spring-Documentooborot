let app = angular.module('app');
app.component('splitRight', {
    transclude: true,
    template: '<div class="splitright">\n' +
        '    <div class="centered2">\n' +
        '        <div class="tablebox">\n' +
        '            <div class="tablecontent">\n' +
        '                <ng-transclude></ng-transclude>' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>'
})
    .component('splitLeft', {
        transclude: true,
        template: '<div class="splitleft">\n' +
            '        <div class="centered">' +
            '           <ng-transclude></ng-transclude>' +
            '        </div>' +
            '       </div>'
    });
