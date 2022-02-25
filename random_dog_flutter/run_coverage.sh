brew install lcov
flutter test --coverage
genhtml coverage/lcov.info -o coverage/html
