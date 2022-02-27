import 'package:flutter/cupertino.dart';
import 'package:random_dog_flutter/features/randomdog/domain/entities/dog_image.dart';
import 'package:random_dog_flutter/features/randomdog/domain/usecases/random_dog_image.dart';

class RandomDogPageProvider extends ChangeNotifier {
  DogImage? dogImage;
  bool isLoading = false;

  final RandomDogImage randomDogImage;
  RandomDogPageProvider(this.randomDogImage);

  fetchImage() async{
    isLoading = true;
    notifyListeners();
    final response = await randomDogImage.randomImage(1);
    dogImage = response.value;
    isLoading = false;
    notifyListeners();
  }
}