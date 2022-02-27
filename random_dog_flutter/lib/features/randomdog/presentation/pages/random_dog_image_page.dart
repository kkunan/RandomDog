import 'package:flutter/material.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/providers/random_dog_page_provider.dart';

class RandomDogImageScreen extends StatelessWidget {
  const RandomDogImageScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container();
  }
}

class RandomDogImagePage extends StatelessWidget {

  final RandomDogPageProvider provider;
  RandomDogImagePage(this.provider);

  @override
  Widget build(BuildContext context) {
    final imageUrl = provider.dogImage?.imageUrls[0]
        ?? 'https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png';
    Image image = Image.network(imageUrl, errorBuilder: (context, obj, stackTrace) {
      return Container();
    },);
    return Column(
      children: [
        image,
        ElevatedButton(
          child: const Text('RANDOM!'),
          onPressed: () {
            provider.fetchImage();
          },
        ),
      ],
    );
  }
}

