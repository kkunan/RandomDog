import 'dart:convert';
import 'dart:math';

import 'package:flutter_test/flutter_test.dart';
import 'package:http/http.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:random_dog_flutter/common/data/services/dog_ceo_service.dart';
import 'package:random_dog_flutter/features/randomdog/data/datasources/network/random_image_network_datasource.dart';
import 'package:random_dog_flutter/features/randomdog/data/models/random_dog_response.dart';

import 'random_image_network_datasource_test.mocks.dart';

@GenerateMocks([DogCeoService])
void main(){
  late RandomImageNetworkDatasourceImpl datasource;
  late MockDogCeoService service;

  setUp((){
    service = MockDogCeoService();
    datasource = RandomImageNetworkDatasourceImpl(service);
  });

  test('randomImage_should_pass_correct_parameters_to_service', () async {
    // arrange
    when(service.getRandomImage(any)).thenAnswer((realInvocation) async {
      return Response('body', 200);
    });
    int number = Random().nextInt(1000);

    // act
    await datasource.randomImage(number);

    // assert
    verify(service.getRandomImage(number));
  });

  test('randomImage_should_return_failed_if_response_status_not_200', () async {
    // arrange
    when(service.getRandomImage(any)).thenAnswer((realInvocation) async {
      return Response('body', 400);
    });

    // act
    final response = await datasource.randomImage(3);

    // assert
    expect(response.status, 'failed');
    expect(response.message, null);
  });

  test('randomImage_should_return_failed_if_body_cannot_parse_to_response_class', () async {
    // arrange
    when(service.getRandomImage(any)).thenAnswer((realInvocation) async {
      return Response('body', 200);
    });

    // act
    final response = await datasource.randomImage(3);

    // assert
    expect(response.status, 'failed');
    expect(response.message, null);
  });

  test('randomImage_should_return_parsed_result_if_parsable', () async {
    // arrange
    final expected = RandomDogResponse(message: const ['url1', 'url2'], status: 'success');
    when(service.getRandomImage(any)).thenAnswer((realInvocation) async {
      return Response(json.encode(expected.toJson()), 200);
    });

    // act
    final response = await datasource.randomImage(3);

    // assert
    expect(response, expected);
  });

}