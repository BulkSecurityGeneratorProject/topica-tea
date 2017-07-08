(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('HtmlTemplateDialogController', HtmlTemplateDialogController);

    HtmlTemplateDialogController.$inject = ['$timeout', 'Upload', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'HtmlTemplate'];

    function HtmlTemplateDialogController ($timeout, Upload, $scope, $stateParams, $uibModalInstance, entity, HtmlTemplate) {
        var vm = this;

        vm.htmlTemplate = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.htmlTemplate.id !== null) {
                HtmlTemplate.update(vm.htmlTemplate, onSaveSuccess, onSaveError);
            } else {
                HtmlTemplate.save(vm.htmlTemplate, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('topicaEventAmplifyApp:htmlTemplateUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        // Upload
        $scope.$watch('files', function () {
            $scope.upload($scope.files);
        });
        $scope.$watch('file', function () {
            if ($scope.file != null) {
                $scope.files = [$scope.file]; 
            }
        });
        $scope.log = '';

        $scope.upload = function (files) {
            if (files && files.length) {
                for (var i = 0; i < files.length; i++) {
                  var file = files[i];
                  if (!file.$error) {
                    Upload.upload({
                        url: 'http://localhost:9301/upload',
                        data: {
                          file: file  
                        }
                    }).then(function (resp) {
                        $timeout(function() {
                            $scope.log = 'file: ' +
                            resp.config.data.file.name +
                            ', Response: ' + JSON.stringify(resp.data) +
                            '\n' + $scope.log;
                            vm.htmlTemplate.uploadedFilename = resp.data.result[0].name;
                        });
                    }, null, function (evt) {
//                        var progressPercentage = parseInt(100.0 *
//                        		evt.loaded / evt.total);
//                        $scope.log = 'progress: ' + progressPercentage + 
//                        	'% ' + evt.config.data.file.name + '\n' + 
//                          $scope.log;
                    });
                  }
                }
            }
        };
    }
})();
