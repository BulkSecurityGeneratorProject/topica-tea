(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ChannelProductDeleteController',ChannelProductDeleteController);

    ChannelProductDeleteController.$inject = ['$uibModalInstance', 'entity', 'ChannelProduct'];

    function ChannelProductDeleteController($uibModalInstance, entity, ChannelProduct) {
        var vm = this;

        vm.channelProduct = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ChannelProduct.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
