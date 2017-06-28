(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('BrandkeyProductDialogController', BrandkeyProductDialogController);

    BrandkeyProductDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'BrandkeyProduct', 'Brandkey', 'Product'];

    function BrandkeyProductDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, BrandkeyProduct, Brandkey, Product) {
        var vm = this;

        vm.brandkeyProduct = entity;
        vm.clear = clear;
        vm.save = save;
        vm.brandkeys = Brandkey.query({filter: 'brandkeyproduct-is-null'});
        $q.all([vm.brandkeyProduct.$promise, vm.brandkeys.$promise]).then(function() {
            if (!vm.brandkeyProduct.brandkeyId) {
                return $q.reject();
            }
            return Brandkey.get({id : vm.brandkeyProduct.brandkeyId}).$promise;
        }).then(function(brandkey) {
            vm.brandkeys.push(brandkey);
        });
        vm.products = Product.query({filter: 'brandkeyproduct-is-null'});
        $q.all([vm.brandkeyProduct.$promise, vm.products.$promise]).then(function() {
            if (!vm.brandkeyProduct.productId) {
                return $q.reject();
            }
            return Product.get({id : vm.brandkeyProduct.productId}).$promise;
        }).then(function(product) {
            vm.products.push(product);
        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.brandkeyProduct.id !== null) {
                BrandkeyProduct.update(vm.brandkeyProduct, onSaveSuccess, onSaveError);
            } else {
                BrandkeyProduct.save(vm.brandkeyProduct, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:brandkeyProductUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
