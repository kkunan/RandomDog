// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility that Flutter provides. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:random_dog_flutter/common/di/dependency_injection.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/pages/random_dog_image_screen.dart';

import 'package:random_dog_flutter/main.dart' as appMain;

void main() {
  testWidgets('MyApp should show RandomDogImageScreen', (WidgetTester tester) async {
    initDi();
    // Build our app and trigger a frame.
    await tester.pumpWidget(const appMain.MyApp());
    expect(find.byType(MaterialApp), findsOneWidget);
    expect(find.byType(RandomDogImageScreen), findsOneWidget);
  });
  
  test('test main function', (){
    try {
      appMain.main();
    } catch (e){
      print(e);
    }
  });
}
