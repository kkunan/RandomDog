// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'random_dog_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

RandomDogResponse _$RandomDogResponseFromJson(Map<String, dynamic> json) =>
    RandomDogResponse(
      message:
          (json['message'] as List<dynamic>?)?.map((e) => e as String).toList(),
      status: json['status'] as String?,
    );

Map<String, dynamic> _$RandomDogResponseToJson(RandomDogResponse instance) =>
    <String, dynamic>{
      'message': instance.message,
      'status': instance.status,
    };
