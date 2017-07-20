(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ChannelProductDialogController', ChannelProductDialogController);

    ChannelProductDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'ChannelProduct', 'Product', 'AdsType', 'HtmlTemplate'];

    function ChannelProductDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, ChannelProduct, Product, AdsType, HtmlTemplate) {
        var vm = this;

        vm.channelProduct = entity;
        vm.clear = clear;
        vm.save = save;
        vm.htmlTemplates;
        vm.products = Product.query({filter: 'channelproduct-is-null'});
        $q.all([vm.channelProduct.$promise, vm.products.$promise]).then(function() {
            if (!vm.channelProduct.productId) {
                return $q.reject();
            }
            return Product.get({id : vm.channelProduct.productId}).$promise;
        }).then(function(product) {
            vm.products.push(product);
        });
        vm.adstypes = AdsType.query({filter: 'channelproduct-is-null'});
        $q.all([vm.channelProduct.$promise, vm.adstypes.$promise]).then(function() {
            if (!vm.channelProduct.adsTypeId) {
                return $q.reject();
            }
            return AdsType.get({id : vm.channelProduct.adsTypeId}).$promise;
        }).then(function(adsType) {
            vm.adstypes.push(adsType);
        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });
        
        loadAllHtmlTemplate();
        
        function loadAllHtmlTemplate () {
        	HtmlTemplate.query({
            }, onSuccess, onError);
            function onSuccess(data, headers) {
                vm.htmlTemplates = data;
            }
            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.channelProduct.id !== null) {
                ChannelProduct.update(vm.channelProduct, onSaveSuccess, onSaveError);
            } else {
                ChannelProduct.save(vm.channelProduct, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:channelProductUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
