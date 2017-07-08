(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ProductHtmlTemplateDetailController', ProductHtmlTemplateDetailController);

    ProductHtmlTemplateDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ProductHtmlTemplate'];

    function ProductHtmlTemplateDetailController($scope, $rootScope, $stateParams, previousState, entity, ProductHtmlTemplate) {
        var vm = this;

        vm.productHtmlTemplate = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:productHtmlTemplateUpdate', function(event, result) {
            vm.productHtmlTemplate = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
