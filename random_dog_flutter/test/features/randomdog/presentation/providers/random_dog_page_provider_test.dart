import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:random_dog_flutter/common/data/responses/local_response.dart';
import 'package:random_dog_flutter/features/randomdog/domain/entities/dog_image.dart';
import 'package:random_dog_flutter/features/randomdog/domain/usecases/random_dog_image.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/providers/random_dog_page_provider.dart';

import 'random_dog_page_provider_test.mocks.dart';

@GenerateMocks([RandomDogImage])
void main(){
  late MockRandomDogImage randomDogImage;
  late RandomDogPageProvider provider;

  setUp((){
    randomDogImage = MockRandomDogImage();
    provider = RandomDogPageProvider(randomDogImage);
  });

  test('isLoading_should_be_false_and_dogImage_null_at_the_beginning', () async {
    // assert
    expect(provider.dogImage, null);
    expect(provider.isLoading, false);
  });

  test('fetchImage_should_set_isLoading_true_then_false_when_finished', () async {
    // arrange
    var state = [];
    provider.addListener(() {
      state.add(provider.isLoading);
    });
    when(randomDogImage.randomImage(any)).thenAnswer((realInvocation) async {
      return LocalResponse();
    });

    // act
    await provider.fetchImage();

    // assert
    expect(state, [true, false]);
  });

  test('fetchImage_should_call_randomDogImage_with_1_when_isLoading_true', () async {
    // arrange
    var isLoadingStatus = false;
    when(randomDogImage.randomImage(any)).thenAnswer((realInvocation) async {
      isLoadingStatus = provider.isLoading;
      return LocalResponse();
    });

    // act
    await provider.fetchImage();


    // assert
    verify(randomDogImage.randomImage(1));
    expect(isLoadingStatus, true);
  });

  test('fetchImage_should_notify_listener_with_value_from_usecase', () async {
    // arrange
    final value = DogImage(imageUrls: ['url1']);
    when(randomDogImage.randomImage(any)).thenAnswer((realInvocation) async {
      return LocalResponse(value: value);
    });
    var notifiedValues = [];
    provider.addListener(() {
      if(provider.isLoading == false)
        notifiedValues.add(provider.dogImage);
    });
    // act
    await provider.fetchImage();

    // assert
    expect(notifiedValues.length, 1);
    expect(notifiedValues[0], value);

  });
}