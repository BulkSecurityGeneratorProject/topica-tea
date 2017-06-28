'use strict';

describe('Controller Tests', function() {

    describe('BrandkeyProduct Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockBrandkeyProduct, MockBrandkey, MockProduct;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockBrandkeyProduct = jasmine.createSpy('MockBrandkeyProduct');
            MockBrandkey = jasmine.createSpy('MockBrandkey');
            MockProduct = jasmine.createSpy('MockProduct');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'BrandkeyProduct': MockBrandkeyProduct,
                'Brandkey': MockBrandkey,
                'Product': MockProduct
            };
            createController = function() {
                $injector.get('$controller')("BrandkeyProductDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'topicaEventAmplifyApp:brandkeyProductUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
