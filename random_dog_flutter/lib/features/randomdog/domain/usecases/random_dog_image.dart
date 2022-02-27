import 'package:random_dog_flutter/common/data/responses/local_response.dart';
import 'package:random_dog_flutter/features/randomdog/domain/entities/dog_image.dart';
import 'package:random_dog_flutter/features/randomdog/domain/repositories/random_dog_repository.dart';

class RandomDogImage {
  final RandomDogRepository repository;
  RandomDogImage(this.repository);

  Future<LocalResponse<DogImage>> randomImage(int number) async {
    return await repository.randomImage(number);
  }
}