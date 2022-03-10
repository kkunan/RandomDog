import 'dart:convert';
import 'dart:io';
import 'dart:math';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:random_dog_flutter/common/data/services/dog_ceo_service.dart';

import 'dog_ceo_service_test.mocks.dart';

@GenerateMocks([HttpClient, HttpClientRequest, HttpClientResponse, HttpHeaders])
Future<void> main() async {
  late DogCeoService service;
  late MockHttpClient client;
  HttpOverrides.global = _MyHttpOverrides();

  setUp((){
    service = DogCeoService();
    client = MockHttpClient();
  });
  // Setting a customer override that'll use an unmocked HTTP client
  test('getRandomImage_should_call_GET_at_correct_path', () async{
    var receivedMethodCalled;
    var receivedUrl;

    final number = Random().nextInt(1000);
    HttpOverrides.runZoned(() async {
     // act
      final data = await service.getRandomImage(number);
      // assert
      print("here $data");

    }, createHttpClient: (_) {

      // arrange
      when(client.openUrl(any, any)).thenAnswer((invocation) {
        final MockHttpClientRequest request = MockHttpClientRequest();
        final MockHttpClientResponse response = MockHttpClientResponse();

        receivedMethodCalled = invocation.positionalArguments[0] as String?;
        receivedUrl = (invocation.positionalArguments[1] as Uri?)?.toString();

        when(request.addStream(any)).thenAnswer((realInvocation) async {
          return Stream.value("test");
        });

        expect(receivedMethodCalled, 'GET');
        expect(receivedUrl, 'https://dog.ceo/api/breeds/image/random/$number');

        when(request.headers).thenReturn(MockHttpHeaders());
        when(response.headers).thenReturn(MockHttpHeaders());

        when(response.handleError(any, test: anyNamed("test"))).thenAnswer((_) => Stream.value([200]));
        when(response.statusCode).thenReturn(200);
        when(response.contentLength).thenReturn("Hello World!".length);
        when(response.isRedirect).thenReturn(false);
        when(response.persistentConnection).thenReturn(true);
        when(response.reasonPhrase).thenReturn("reasonPhrase");

        when(request.close()).thenAnswer((_) => Future.value(response));
        when(response.transform(any)).thenAnswer((invocation) {
          final decoder = invocation.positionalArguments[0];
          return Stream
              .fromFuture(Future.value(utf8.encode("Hello World!")))
              .transform(decoder);
        });
        return Future.value(request);
      });

      return client;
    });
  });
}

class _MyHttpOverrides extends HttpOverrides {}