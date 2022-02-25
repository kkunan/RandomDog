import 'package:equatable/equatable.dart';

class LocalResponse<T> extends Equatable {
  final T? value;
  final String? error;

  const LocalResponse({this.value, this.error});

  @override
  List<Object?> get props => [value, error];
}