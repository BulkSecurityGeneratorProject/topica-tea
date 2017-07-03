(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .config(bootstrapMaterialDesignConfig);

    bootstrapMaterialDesignConfig.$inject = [];

    function bootstrapMaterialDesignConfig() {
        $.material.init();
    }
})();