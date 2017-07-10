'use strict';

describe('Controller Tests', function() {

    describe('ChannelProduct Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockChannelProduct, MockProduct, MockAdsType;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockChannelProduct = jasmine.createSpy('MockChannelProduct');
            MockProduct = jasmine.createSpy('MockProduct');
            MockAdsType = jasmine.createSpy('MockAdsType');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'ChannelProduct': MockChannelProduct,
                'Product': MockProduct,
                'AdsType': MockAdsType
            };
            createController = function() {
                $injector.get('$controller')("ChannelProductDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'topicaEventAmplifyApp:channelProductUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
