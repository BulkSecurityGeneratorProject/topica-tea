(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('QuestionDialogController', QuestionDialogController);

    QuestionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Question', 'ChannelGroup', 'Brandkey'];

    function QuestionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Question, ChannelGroup, Brandkey) {
        var vm = this;
        
        vm.amplifyTypes = ["SHARE", "SPONSOR", "INJECT"];
        vm.changeAmplifyType = changeAmplifyType;
        vm.changeBrandkeys = changeBrandkeys;
        vm.changeMeatContent = changeMeatContent;
        vm.question = entity;
        vm.clear = clear;
        vm.save = save;
        vm.roles = ChannelGroup.query({filter: 'question-is-null'});
        
        function changeMeatContent() {
        	vm.checkMeatContent = [];
        	if ($('#field_isMeatContent').is(":checked")) {
        		vm.checkMeatContent.push('true');
        	}
        	
        	if ($('#field_isNoMeatContent').is(":checked")) {
        		vm.checkMeatContent.push('true');
        	}
        }
        
        function changeBrandkeys() {
        	var lstBrandkeys = [];
        	
        	angular.forEach(vm.brandkeys, function (val, key) {
        		if ($('#field_brandkey_' + val.id).is(":checked")) {
        			lstBrandkeys.push(val);
            	}
            });
        	
        	vm.question.brandkeys = lstBrandkeys;
        }
        
        function changeAmplifyType() {
        	vm.question.amplifyType = [];
        	if ($('#field_amplifyType_SHARE').is(":checked")) {
        		vm.question.amplifyType.push('SHARE');
        	}
        	
        	if ($('#field_amplifyType_SPONSOR').is(":checked")) {
        		vm.question.amplifyType.push('SPONSOR');
        	}
        	
        	if ($('#field_amplifyType_INJECT').is(":checked")) {
        		vm.question.amplifyType.push('INJECT');
        	}
        }
        
        
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
