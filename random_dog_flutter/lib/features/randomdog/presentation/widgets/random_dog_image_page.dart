import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:random_dog_flutter/common/presentation/theme/colors.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/providers/random_dog_page_provider.dart';

class RandomDogImagePage extends StatelessWidget {
  final RandomDogPageProvider provider;

  RandomDogImagePage(this.provider);

  @override
  Widget build(BuildContext context) {
    final imageUrl = provider.dogImage?.imageUrls[0] ?? "";
    Image image = Image.network(
      imageUrl,
      errorBuilder: (context, obj, stackTrace) {
        return Container();
      },
    );
    return Scaffold(
      body: Stack(
        children: [
          Container(
            color: colorSecondary,
          ),
          SvgPicture.asset(
              "assets/images/background_dog.svg",
            fit: BoxFit.fitHeight,
          ),
          SafeArea(
            child: Center(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(12.0),
                      border: Border.all(color: Theme.of(context).primaryColor, width: 4.0),
                      color: Theme.of(context).backgroundColor
                    ),
                    width: 200,
                    height: 200,
                    child: image,
                  ),
                  SizedBox(height: 24.0,),
                  ElevatedButton(
                    child: const Text('RANDOM!'),
                    onPressed: () {
                      provider.fetchImage();
                    },
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}