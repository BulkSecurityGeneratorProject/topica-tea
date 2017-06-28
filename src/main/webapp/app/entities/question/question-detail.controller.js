(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('QuestionDetailController', QuestionDetailController);

    QuestionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Question', 'ChannelGroup', 'Brandkey'];

    function QuestionDetailController($scope, $rootScope, $stateParams, previousState, entity, Question, ChannelGroup, Brandkey) {
        var vm = this;

        vm.question = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('topicaEventAmplifyApp:questionUpdate', function(event, result) {
            vm.question = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
