import 'dart:convert';

import 'package:random_dog_flutter/common/data/services/dog_ceo_service.dart';
import 'package:random_dog_flutter/features/randomdog/data/models/random_dog_response.dart';

abstract class RandomImageNetworkDatasource {
  Future<RandomDogResponse> randomImage(int number);
}

class RandomImageNetworkDatasourceImpl implements RandomImageNetworkDatasource {
  RandomImageNetworkDatasourceImpl(this.service);
  DogCeoService service;

  @override
  Future<RandomDogResponse> randomImage(int number) async {
    final response = await service.getRandomImage(number);
    try {
      return RandomDogResponse.fromJson(json.decode(response.body));
    } catch (e){
      return RandomDogResponse(message: null ,status: 'failed');
    }
  }
}