import 'package:random_dog_flutter/common/data/responses/local_response.dart';
import 'package:random_dog_flutter/features/randomdog/data/datasources/network/random_image_network_datasource.dart';
import 'package:random_dog_flutter/features/randomdog/domain/entities/dog_image.dart';
import 'package:random_dog_flutter/features/randomdog/domain/repositories/random_dog_repository.dart';

class RandomDogRepositoryImpl implements RandomDogRepository {
  final RandomImageNetworkDatasource datasource;
  RandomDogRepositoryImpl(this.datasource);

  @override
  Future<LocalResponse<DogImage>> randomImage(int number) async{
    final response = await datasource.randomImage(number);
    final message = response.message;
    if(message != null){
      return LocalResponse(value: DogImage(imageUrls: message));
    } else {
      return const LocalResponse(error: "Cannot get image!");
    }
  }
}