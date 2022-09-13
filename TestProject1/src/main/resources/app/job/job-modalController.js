angular
    .module('app')
    .controller('jobsModalController', jobsModalController);

function jobsModalController($uibModalInstance, syncData, jobService, $rootScope) {

    const vm = this;
    vm.data = syncData;

    vm.jobsForm = {
        id: -1,
        name: ""
    };
    if (vm.data) {
        editJob(vm.data);
    }

    function editJob(job) {
        vm.jobsForm = job;
    }
    
    vm.ok = async function () {
        if (vm.jobsForm.id == -1) {
            vm.jobsForm.id = null
            await jobService.postJob(vm.jobsForm).catch(err=>alert(err.data.errorMessage))
            _refreshCustomerData()
        } else {
            await jobService.putJob(vm.jobsForm).catch(err=>alert(err.data.errorMessage))
            _refreshCustomerData();
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteJob = async function () {
        await jobService.deleteJob(vm.data.id).catch(err=>alert(err.data.errorMessage))
        _refreshCustomerData();
        $uibModalInstance.close();
    };

    async function _refreshCustomerData() {
        let response = await jobService.getJobs().catch(err=>alert(err.data.errorMessage))
        $rootScope.$broadcast("refreshJobs", response.data);
    }
};

