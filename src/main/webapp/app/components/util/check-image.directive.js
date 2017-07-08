(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .directive('checkImage', checkImage);

    function checkImage ($http) {
    	return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                attrs.$observe('ngSrc', function(ngSrc) {
                    $http.get(ngSrc).success(function(){
                        alert('image exist');
                    }).error(function(){
                        alert('image not exist');
                        element.attr('src', '/images/default_user.jpg'); // set default image
                    });
                });
            }
        };
    }

})();
