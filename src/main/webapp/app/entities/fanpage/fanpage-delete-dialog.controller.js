(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('FanpageDeleteController',FanpageDeleteController);

    FanpageDeleteController.$inject = ['$uibModalInstance', 'entity', 'Fanpage'];

    function FanpageDeleteController($uibModalInstance, entity, Fanpage) {
        var vm = this;

        vm.fanpage = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Fanpage.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
