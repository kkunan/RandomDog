
import 'dart:math';

import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:random_dog_flutter/common/data/responses/local_response.dart';
import 'package:random_dog_flutter/features/randomdog/domain/entities/dog_image.dart';
import 'package:random_dog_flutter/features/randomdog/domain/repositories/random_dog_repository.dart';
import 'package:random_dog_flutter/features/randomdog/domain/usecases/random_dog_image.dart';

import 'random_dog_image_test.mocks.dart';

@GenerateMocks([RandomDogRepository])
void main(){
  late RandomDogImage randomDogImage;
  late MockRandomDogRepository repository;

  setUp((){
    repository = MockRandomDogRepository();
    randomDogImage = RandomDogImage(repository);
  });

  test('randomImage_should_pass_correct_parameters_to_repository', () async {
    // arrange
    when(repository.randomImage(any)).thenAnswer((realInvocation) async {
      return const LocalResponse();
    });
    final number = Random().nextInt(1000);

    // act
    await randomDogImage.randomImage(number);

    // assert
    verify(repository.randomImage(number));
  });

  test('randomImage_should_return_the_same_response_from_repository', () async {
    // arrange
    final urls = ['url1', 'url2'];
    final error = 'randomError';
    final expected = LocalResponse(value: DogImage(imageUrls: urls), error: error);
    when(repository.randomImage(any)).thenAnswer((realInvocation) async {
      return expected;
    });

    // act
    final response = await randomDogImage.randomImage(3);

    // assert
    expect(response, expected);
  });
}