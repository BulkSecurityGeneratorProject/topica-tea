(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('ArticleDialogController', ArticleDialogController);

    ArticleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Article', 'Event'];

    function ArticleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Article, Event) {
        var vm = this;

        vm.article = entity;
        vm.clear = clear;
        vm.save = save;
        vm.event = null;
        
        loadEvent();
        
        function loadEvent() {
        	vm.event = Event.get({id : 5});
        }

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.article.id !== null) {
                Article.update(vm.article, onSaveSuccess, onSaveError);
            } else {
                Article.save(vm.article, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:articleUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
