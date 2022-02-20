//
//  RandomImageWorkerTests.swift
//  Tests iOS
//
//  Created by Komkrit Kunanusont on 20/2/2565 BE.
//

import XCTest
import randomdogios
@testable import randomdogios

class RandomImageWorkerTests: XCTestCase {
    
    var sut: RandomImageWorker?
    var spy: DogCeoApiServiceSpy?

    override func setUp() {
        spy = DogCeoApiServiceSpy()
        sut = RandomImageWorker()
        sut?.apiService = spy
    }
    
    override func tearDown() {
        sut = nil
        spy = nil
    }

    func test_getRandomImage_should_pass_correct_parameters_to_service() async {
        // arrange
        let number = Int.random(in: 1...100)
        spy?.response = RandomImageWorkerResponse(message: nil, status: "success")
        
        // act
        await sut?.getRandomImage(number: number)
        
        // assert
        XCTAssertEqual(spy?.receivedNumber, number)
    }
    
    func test_getRandomImage_should_return_error_if_response_status_is_failed() async{
        // arrange
        spy?.response = RandomImageWorkerResponse(message: nil, status: "failed")
        
        // act
        let response = await sut?.getRandomImage(number: 3)
        
        // assert
        XCTAssertEqual(response?.message, nil)
        XCTAssertEqual(response?.status, "failed")
    }
    
    func test_getRandomImage_should_return_success_if_response_status_is_success() async {
        // arrange
        spy?.response = RandomImageWorkerResponse(message: [""], status: "success")
        
        // act
        let response = await sut?.getRandomImage(number: 3)
        
        // assert
        XCTAssertEqual(response?.message, spy?.response?.message)
        XCTAssertEqual(response?.status, "success")
    }
}

class DogCeoApiServiceSpy: DogCeoApiService {
    var response: RandomImageWorkerResponse?
    
    var receivedNumber: Int? = nil
    override func getRandomImage(number: Int, completion: @escaping (RandomImageWorkerResponse) -> Void){
        receivedNumber = number
        if let response = response {
            completion(response)
        }
    }
}
