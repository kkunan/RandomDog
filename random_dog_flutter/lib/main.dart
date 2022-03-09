import 'package:flutter/material.dart';
import 'package:random_dog_flutter/common/di/dependency_injection.dart';
import 'package:random_dog_flutter/common/presentation/theme/colors.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/pages/random_dog_image_page.dart';

void main() {
  initDi();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Random Dog',
      theme: ThemeData(
       primaryColor: colorPrimary,
        backgroundColor: colorSecondary,
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            primary: colorPrimary,
            textStyle: TextStyle(
              color: Colors.white,
              fontSize: 24.0,
              fontWeight: FontWeight.bold
            ),
            padding: EdgeInsets.symmetric(vertical: 16.0, horizontal: 24.0),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(40)
            )
          )
        ),
        buttonTheme: ButtonThemeData(
          buttonColor: colorPrimary
        )
      ),
      home: RandomDogImageScreen(),
    );
  }
}
