import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:random_dog_flutter/common/di/dependency_injection.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/providers/random_dog_page_provider.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/widgets/random_dog_image_page.dart';

// void main() {
//   initDi();
//   runApp(MaterialApp(
//     home: Scaffold(
//       body: RandomDogImageScreen(),
//     ),
//   ));
// }

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


