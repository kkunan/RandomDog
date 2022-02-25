import 'package:equatable/equatable.dart';
import 'package:json_annotation/json_annotation.dart';
part 'random_dog_response.g.dart';

@JsonSerializable()
class RandomDogResponse extends Equatable {

  final List<String>? message;
  final String? status;

  RandomDogResponse({required this.message, required this.status});

  factory RandomDogResponse.fromJson(Map<String, dynamic> json) => _$RandomDogResponseFromJson(json);
  Map<String, dynamic> toJson() => _$RandomDogResponseToJson(this);

  // {
  // "message": [
  // "https://images.dog.ceo/breeds/setter-irish/n02100877_4898.jpg",
  // "https://images.dog.ceo/breeds/spaniel-sussex/n02102480_1525.jpg",
  // "https://images.dog.ceo/breeds/papillon/n02086910_4609.jpg"
  // ],
  // "status": "success"
  // }

  @override
  List<Object?> get props => [message, status];

}