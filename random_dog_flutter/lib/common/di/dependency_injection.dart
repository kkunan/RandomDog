import 'package:get_it/get_it.dart';
import 'package:random_dog_flutter/common/data/services/dog_ceo_service.dart';
import 'package:random_dog_flutter/features/randomdog/data/datasources/network/random_image_network_datasource.dart';
import 'package:random_dog_flutter/features/randomdog/data/repositories/random_dog_repository_impl.dart';
import 'package:random_dog_flutter/features/randomdog/domain/repositories/random_dog_repository.dart';
import 'package:random_dog_flutter/features/randomdog/domain/usecases/random_dog_image.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/providers/random_dog_page_provider.dart';

final di = GetIt.instance;

void initDi(){
  di.registerSingleton<DogCeoService>(DogCeoService());
  di.registerSingleton<RandomImageNetworkDatasource>(RandomImageNetworkDatasourceImpl(di()));
  di.registerSingleton<RandomDogRepository>(RandomDogRepositoryImpl(di()));
  di.registerSingleton<RandomDogImage>(RandomDogImage(di()));
  di.registerFactory<RandomDogPageProvider>(() => RandomDogPageProvider(di()));
}