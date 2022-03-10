import 'package:flutter_test/flutter_test.dart';
import 'package:random_dog_flutter/common/data/services/dog_ceo_service.dart';
import 'package:random_dog_flutter/common/di/dependency_injection.dart';
import 'package:random_dog_flutter/features/randomdog/data/datasources/network/random_image_network_datasource.dart';
import 'package:random_dog_flutter/features/randomdog/data/repositories/random_dog_repository_impl.dart';
import 'package:random_dog_flutter/features/randomdog/domain/repositories/random_dog_repository.dart';
import 'package:random_dog_flutter/features/randomdog/domain/usecases/random_dog_image.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/providers/random_dog_page_provider.dart';
import 'package:random_dog_flutter/main.dart';

void main(){
  setUp((){
    initDi();
  });

  test('initDi() should initialize all dependencies correctly', () async {
    // assert
    // type tests
    expect(di<RandomImageNetworkDatasource>() is RandomImageNetworkDatasourceImpl, true);
    expect(di<RandomDogRepository>() is RandomDogRepositoryImpl, true);

    final dogCeoService = di<DogCeoService>();
    // singleton tests
    expect(dogCeoService, di<DogCeoService>());
    expect(di<RandomDogImage>(), di<RandomDogImage>());

    // factory tests
    expect(di<RandomDogPageProvider>() == di<RandomDogPageProvider>(), false);
  });
}