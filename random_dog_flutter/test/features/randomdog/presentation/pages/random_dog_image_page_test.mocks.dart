// Mocks generated by Mockito 5.1.0 from annotations
// in random_dog_flutter/test/features/randomdog/presentation/pages/random_dog_image_page_test.dart.
// Do not manually edit this file.

import 'dart:ui' as _i5;

import 'package:mockito/mockito.dart' as _i1;
import 'package:random_dog_flutter/features/randomdog/domain/entities/dog_image.dart'
    as _i4;
import 'package:random_dog_flutter/features/randomdog/domain/usecases/random_dog_image.dart'
    as _i2;
import 'package:random_dog_flutter/features/randomdog/presentation/providers/random_dog_page_provider.dart'
    as _i3;

// ignore_for_file: type=lint
// ignore_for_file: avoid_redundant_argument_values
// ignore_for_file: avoid_setters_without_getters
// ignore_for_file: comment_references
// ignore_for_file: implementation_imports
// ignore_for_file: invalid_use_of_visible_for_testing_member
// ignore_for_file: prefer_const_constructors
// ignore_for_file: unnecessary_parenthesis
// ignore_for_file: camel_case_types

class _FakeRandomDogImage_0 extends _i1.Fake implements _i2.RandomDogImage {}

/// A class which mocks [RandomDogPageProvider].
///
/// See the documentation for Mockito's code generation for more information.
class MockRandomDogPageProvider extends _i1.Mock
    implements _i3.RandomDogPageProvider {
  MockRandomDogPageProvider() {
    _i1.throwOnMissingStub(this);
  }

  @override
  set dogImage(_i4.DogImage? _dogImage) =>
      super.noSuchMethod(Invocation.setter(#dogImage, _dogImage),
          returnValueForMissingStub: null);
  @override
  bool get isLoading =>
      (super.noSuchMethod(Invocation.getter(#isLoading), returnValue: false)
          as bool);
  @override
  set isLoading(bool? _isLoading) =>
      super.noSuchMethod(Invocation.setter(#isLoading, _isLoading),
          returnValueForMissingStub: null);
  @override
  _i2.RandomDogImage get randomDogImage =>
      (super.noSuchMethod(Invocation.getter(#randomDogImage),
          returnValue: _FakeRandomDogImage_0()) as _i2.RandomDogImage);
  @override
  bool get hasListeners =>
      (super.noSuchMethod(Invocation.getter(#hasListeners), returnValue: false)
          as bool);
  @override
  void addListener(_i5.VoidCallback? listener) =>
      super.noSuchMethod(Invocation.method(#addListener, [listener]),
          returnValueForMissingStub: null);
  @override
  void removeListener(_i5.VoidCallback? listener) =>
      super.noSuchMethod(Invocation.method(#removeListener, [listener]),
          returnValueForMissingStub: null);
  @override
  void dispose() => super.noSuchMethod(Invocation.method(#dispose, []),
      returnValueForMissingStub: null);
  @override
  void notifyListeners() =>
      super.noSuchMethod(Invocation.method(#notifyListeners, []),
          returnValueForMissingStub: null);
}
