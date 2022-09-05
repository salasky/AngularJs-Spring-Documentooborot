angular
    .module('app')
    .component("splitRight",{
        transclude: true,
        template: '<div class="splitright">\n' +
            '    <div class="centered2">\n' +
            '        <div class="tablebox">\n' +
            '            <div class="tablecontent">\n' +
            '<ng-content></ng-content>' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>'
    })
.component("departmentTab",{
    transclude: true,
    template: '  <splitRight> <ul class="nav nav-tabs">\n' +
        '                    <li ng-repeat="tab in vm.tabs track by $index"\n' +
        '                        ng-class="{true:\'active\',false:\'\'}[tab==vm.activeTabNo]">\n' +
        '                        <a ng-click="vm.activateTab(tab)">{{tab.index}} <img\n' +
        '                                style="max-height: 20px" src="/app/img/department.png">\n' +
        '                            <i class="glyphicon glyphicon-remove" ng-click="vm.remove($index)"></i></a>\n' +
        '                    </li>\n' +
        '                </ul>\n' +
        '                <div class="tab-content">\n' +
        '                    <div class="tab-pane" ng-repeat="tab in vm.tabs"\n' +
        '                         ng-class="{true:\'active\',false:\'\'}[tab==vm.activeTabNo]">\n' +
        '                        <div ng-app="app" class="modal-body" id="modal-body">\n' +
        '                            <form class="form-horizontal" name="contact">\n' +
        '                                <div class="form-group">\n' +
        '                                    <label class="col-lg-3 control-label">Full name:</label>\n' +
        '                                    <div class="col-lg-8">\n' +
        '                                        <input class="form-control" type="text" id="fullName" name="fullName"\n' +
        '                                               ng-model="tab.organization.fullName "></div>\n' +
        '                                </div>\n' +
        '                                <div class="form-group">\n' +
        '                                    <label class="col-lg-3 control-label">Short Name:</label>\n' +
        '                                    <div class="col-lg-8">\n' +
        '                                        <input class="form-control" type="text" id="shortName" name="shortName"\n' +
        '                                               ng-model="tab.organization.shortName"></div>\n' +
        '                                </div>\n' +
        '                                <div class="form-group">\n' +
        '                                    <label class="col-lg-3 control-label">Supervisor:</label>\n' +
        '                                    <div class="col-lg-8">\n' +
        '                                        <input class="form-control" type="text" id="supervisor" name="supervisor"\n' +
        '                                               ng-model="tab.organization.supervisor "></div>\n' +
        '                                </div>\n' +
        '                                <div class="form-group">\n' +
        '                                    <label class="col-lg-3 control-label">Contact Numbers:</label>\n' +
        '                                    <div class="col-lg-8">\n' +
        '                                        <textarea class="form-control" rows="2" name="contactNumbers"\n' +
        '                                                  ng-model="tab.organization.contactNumbers "></textarea>\n' +
        '                                    </div>\n' +
        '                                </div>\n' +
        '                            </form>\n' +
        '                        </div>\n' +
        '                        <button ng-if="vm.activeTabNo!=0" ng-click="vm.openOrganizationModal()" class="info-button">Edit\n' +
        '                        </button>\n' +
        '                    </div>\n' +
        '                </div>' +
        '</splitRight>'
});