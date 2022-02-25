import 'package:random_dog_flutter/common/data/responses/local_response.dart';
import 'package:random_dog_flutter/features/randomdog/domain/entities/dog_image.dart';

abstract class RandomDogRepository {
  Future<LocalResponse<DogImage>> randomImage(int number);
}