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
            try {
                await jobService.postJob(vm.jobsForm)
                _refreshCustomerData()
            } catch (error) {
                alert(err.data.errorMessage)
            }

        } else {
            try {
                await jobService.putJob(vm.jobsForm)
                _refreshCustomerData();
            } catch (error) {
                alert(err.data.errorMessage)
            }
        }
        $uibModalInstance.close();
    }

    vm.cancel = function () {
        $uibModalInstance.close()
    }

    vm.deleteJob = async function () {
        try {
            await jobService.deleteJob(vm.data.id)
            _refreshCustomerData();
        } catch (error) {
            alert(err.data.errorMessage)
        }
        $uibModalInstance.close();
    };

    async function _refreshCustomerData() {
        try {
            let response = await jobService.getJobs()
            $rootScope.$broadcast("refreshJobs", response.data);
        } catch (error) {
            alert(err.data.errorMessage)
        }
    }
};

