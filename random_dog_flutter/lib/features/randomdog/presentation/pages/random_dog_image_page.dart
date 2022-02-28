import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:random_dog_flutter/common/di/dependency_injection.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/providers/random_dog_page_provider.dart';

void main() {
  initDi();
  runApp(MaterialApp(
    home: Scaffold(
      body: RandomDogImageScreen(),
    ),
  ));
}

class RandomDogImageScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider.value(value: di<RandomDogPageProvider>())
      ],
      child:
          Consumer<RandomDogPageProvider>(builder: (context, provider, widget) {
        return RandomDogImagePage(provider);
      }),
    );
  }
}

class RandomDogImagePage extends StatelessWidget {
  final RandomDogPageProvider provider;

  RandomDogImagePage(this.provider);

  @override
  Widget build(BuildContext context) {
    final imageUrl = provider.dogImage?.imageUrls[0] ??
        'https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png';
    print("imageUrl: $imageUrl");
    Image image = Image.network(
      imageUrl,
      errorBuilder: (context, obj, stackTrace) {
        return Container();
      },
    );
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
