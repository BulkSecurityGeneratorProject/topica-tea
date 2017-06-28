(function () {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
