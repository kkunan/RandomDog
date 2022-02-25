// Mocks generated by Mockito 5.1.0 from annotations
// in random_dog_flutter/test/features/randomdog/data/repositories/random_dog_repository_impl_test.dart.
// Do not manually edit this file.

import 'dart:async' as _i4;

import 'package:mockito/mockito.dart' as _i1;
import 'package:random_dog_flutter/features/randomdog/data/datasources/network/random_image_network_datasource.dart'
    as _i3;
import 'package:random_dog_flutter/features/randomdog/data/models/random_dog_response.dart'
    as _i2;

// ignore_for_file: type=lint
// ignore_for_file: avoid_redundant_argument_values
// ignore_for_file: avoid_setters_without_getters
// ignore_for_file: comment_references
// ignore_for_file: implementation_imports
// ignore_for_file: invalid_use_of_visible_for_testing_member
// ignore_for_file: prefer_const_constructors
// ignore_for_file: unnecessary_parenthesis
// ignore_for_file: camel_case_types

class _FakeRandomDogResponse_0 extends _i1.Fake
    implements _i2.RandomDogResponse {}

/// A class which mocks [RandomImageNetworkDatasource].
///
/// See the documentation for Mockito's code generation for more information.
class MockRandomImageNetworkDatasource extends _i1.Mock
    implements _i3.RandomImageNetworkDatasource {
  MockRandomImageNetworkDatasource() {
    _i1.throwOnMissingStub(this);
  }

  @override
  _i4.Future<_i2.RandomDogResponse> randomImage(int? number) =>
      (super.noSuchMethod(Invocation.method(#randomImage, [number]),
              returnValue: Future<_i2.RandomDogResponse>.value(
                  _FakeRandomDogResponse_0()))
          as _i4.Future<_i2.RandomDogResponse>);
}
