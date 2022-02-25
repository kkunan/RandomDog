import 'dart:math';

import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:random_dog_flutter/common/data/responses/local_response.dart';
import 'package:random_dog_flutter/features/randomdog/data/datasources/network/random_image_network_datasource.dart';
import 'package:random_dog_flutter/features/randomdog/data/models/random_dog_response.dart';
import 'package:random_dog_flutter/features/randomdog/data/repositories/random_dog_repository_impl.dart';
import 'package:random_dog_flutter/features/randomdog/domain/entities/dog_image.dart';

import 'random_dog_repository_impl_test.mocks.dart';

@GenerateMocks([RandomImageNetworkDatasource])
void main(){
  late MockRandomImageNetworkDatasource datasource;
  late RandomDogRepositoryImpl repository;

  setUp((){
    datasource = MockRandomImageNetworkDatasource();
    repository = RandomDogRepositoryImpl(datasource);
  });

  test('randomImage_should_pass_correct_parameters_to_datasource', () async {
    // arrange
    when(datasource.randomImage(any)).thenAnswer((realInvocation) async {
      return RandomDogResponse(message: [], status: 'status');
    });
    final number = Random().nextInt(1000);

    // act
    await repository.randomImage(number);

    // assert
    verify(datasource.randomImage(number));
  });

  test('randomImage_should_return_error_if_datasource_message_null', () async {
    // arrange
    when(datasource.randomImage(any)).thenAnswer((realInvocation) async {
      return RandomDogResponse(message: null, status: 'status');
    });
    const expected = LocalResponse<DogImage>(value: null, error: "Cannot get image!");

    // act
    final response = await repository.randomImage(4);

    // assert
    expect(response, expected);
  });

  test('randomImage_should_map_each_url_to_dogImage_and_return_success', () async {
    // arrange
    final expected = DogImage(imageUrls: ['url1', 'url2']);
    when(datasource.randomImage(any)).thenAnswer((realInvocation) async {
      return RandomDogResponse(message: expected.imageUrls, status: 'status');
    });

    // act
    final response = await repository.randomImage(4);

    // assert
    expect(response, LocalResponse(value: expected));

  });
}