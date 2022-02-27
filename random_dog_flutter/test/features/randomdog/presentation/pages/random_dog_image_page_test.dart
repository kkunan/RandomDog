import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:random_dog_flutter/features/randomdog/domain/entities/dog_image.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/pages/random_dog_image_page.dart';
import 'package:random_dog_flutter/features/randomdog/presentation/providers/random_dog_page_provider.dart';

import 'random_dog_image_page_test.mocks.dart';

@GenerateMocks([RandomDogPageProvider])
void main() {
  late MockRandomDogPageProvider provider;
  late RandomDogImagePage page;
  late MaterialApp app;
  setUp(() {
    provider = MockRandomDogPageProvider();
    page = RandomDogImagePage(provider);
    app = MaterialApp(
      home: page,
    );
  });

  final mockImage = DogImage(imageUrls: ['https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png']);

  group('random_dog_image_page', (){
    testWidgets('page_should_have_random_text', (tester) async {
      // arrange
      when(provider.dogImage).thenReturn(mockImage);

      // act
      await tester.pumpWidget(app);

      // assert
      expect(find.text('RANDOM!'), findsOneWidget);
    });

    testWidgets('page_should_have_image_widget', (tester) async {
      // arrange
      when(provider.dogImage).thenReturn(mockImage);

      // act
      await tester.pumpWidget(app);

      // assert
      expect(find.byType(Image), findsOneWidget);
    });

    testWidgets('random_should_clickable_and_call_provider', (tester) async {
      // arrange
      when(provider.fetchImage()).thenAnswer((realInvocation) {});
      when(provider.dogImage).thenReturn(mockImage);

      // act
      await tester.pumpWidget(app);
      final widget = find.text('RANDOM!');
      await tester.tap(widget);
      await tester.pump();

      // assert
      verify(provider.fetchImage());
    });
  });
}

