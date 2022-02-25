import 'package:equatable/equatable.dart';

class DogImage extends Equatable {
  final List<String> imageUrls;

  const DogImage({required this.imageUrls});

  @override
  List<Object?> get props => [imageUrls];
}