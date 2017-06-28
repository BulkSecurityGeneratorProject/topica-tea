(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('QuestionDialogController', QuestionDialogController);

    QuestionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Question', 'ChannelGroup', 'Brandkey'];

    function QuestionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Question, ChannelGroup, Brandkey) {
        var vm = this;

        vm.question = entity;
        vm.clear = clear;
        vm.save = save;
        vm.roles = ChannelGroup.query({filter: 'question-is-null'});
        $q.all([vm.question.$promise, vm.roles.$promise]).then(function() {
            if (!vm.question.roleId) {
                return $q.reject();
            }
            return ChannelGroup.get({id : vm.question.roleId}).$promise;
        }).then(function(role) {
            vm.roles.push(role);
        });
        vm.invitees = ChannelGroup.query({filter: 'question-is-null'});
        $q.all([vm.question.$promise, vm.invitees.$promise]).then(function() {
            if (!vm.question.inviteeId) {
                return $q.reject();
            }
            return ChannelGroup.get({id : vm.question.inviteeId}).$promise;
        }).then(function(invitee) {
            vm.invitees.push(invitee);
        });
        vm.scales = ChannelGroup.query({filter: 'question-is-null'});
        $q.all([vm.question.$promise, vm.scales.$promise]).then(function() {
            if (!vm.question.scaleId) {
                return $q.reject();
            }
            return ChannelGroup.get({id : vm.question.scaleId}).$promise;
        }).then(function(scale) {
            vm.scales.push(scale);
        });
        vm.brandkeys = Brandkey.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.question.id !== null) {
                Question.update(vm.question, onSaveSuccess, onSaveError);
            } else {
                Question.save(vm.question, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:questionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
