angular
    .module('app')
    .component("splitRight",{
        template: '<div class="splitright">\n' +
            '    <div class="centered2">\n' +
            '        <div class="tablebox">\n' +
            '            <div class="tablecontent">\n' +
            '{{vm.content}}' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>',
    bindings: { content: '@' }
    });