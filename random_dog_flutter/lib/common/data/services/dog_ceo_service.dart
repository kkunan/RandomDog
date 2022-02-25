import 'package:http/http.dart' as http;

class DogCeoService {
  static const String _endPoint = 'https://dog.ceo/api';
  static String _getRandomImageEndpoint(int number) {
    return "$_endPoint/breeds/image/random/$number";
  }

  Future<http.Response> getRandomImage(int number) async {
     return await http.get(Uri.parse(_getRandomImageEndpoint(number)));
  }
}