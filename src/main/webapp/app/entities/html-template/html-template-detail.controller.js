(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('HtmlTemplateDetailController', HtmlTemplateDetailController);

    HtmlTemplateDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'HtmlTemplate'];

    function HtmlTemplateDetailController($scope, $rootScope, $stateParams, previousState, entity, HtmlTemplate) {
        var vm = this;

        vm.htmlTemplate = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:htmlTemplateUpdate', function(event, result) {
            vm.htmlTemplate = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
