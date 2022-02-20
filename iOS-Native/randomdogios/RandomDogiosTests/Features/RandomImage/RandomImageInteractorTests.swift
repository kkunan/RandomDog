//
//  RandomImageInteractorTests.swift
//  RandomDogiosTests
//
//  Created by Komkrit Kunanusont on 20/2/2565 BE.
//

import XCTest
@testable import randomdogios

class RandomImageInteractorTests: XCTestCase {
    
    var sut: RandomImageInteractor?
    var presenterSpy: RandomImagePresenterSpy?
    var workerSpy: RandomImageWorkerSpy?

    override func setUp() {
        presenterSpy = RandomImagePresenterSpy()
        workerSpy = RandomImageWorkerSpy()
        sut = RandomImageInteractor()
        sut?.presenter = presenterSpy
        sut?.worker = workerSpy
    }
    

    func test_getRandomImage_should_pass_correct_parameters_to_worker() async {
       // arrange
        let number = Int.random(in: 1...100)
        let request = RandomImage.RandomDogImage.Request(number: number)
        // act
        await sut?.randomImage(request: request)
        
        // assert
        XCTAssertEqual(workerSpy?.receivedNumber, number)
    }
    
    func test_getRandomImage_should_call_presenter_with_nil_if_worker_value_nil() async {
        // arrange
        let request = RandomImage.RandomDogImage.Request(number: 3)
        workerSpy?.response = RandomImageWorkerResponse(message: nil, status: nil)
        
        // act
        await sut?.randomImage(request: request)
        
        // assert
        XCTAssertNil(presenterSpy?.receivedResponse?.value)
        
    }
    
    func test_getRandomImage_should_call_presenter_with_mapped_url_if_worker_value_not_nil() async {
        // arrange
        let request = RandomImage.RandomDogImage.Request(number: 3)
        let workerResponse = RandomImageWorkerResponse(message: ["url1", "url2"], status: nil)
        workerSpy?.response = workerResponse
        
        // act
        await sut?.randomImage(request: request)
        
        // assert
        let response = presenterSpy?.receivedResponse?.value
        XCTAssertNotNil(response)
        XCTAssertEqual(response!.count, workerResponse.message?.count)
        for (index,item) in response!.enumerated() {
            XCTAssertEqual(item.url, workerResponse.message![index])
        }
    }

}

class RandomImagePresenterSpy: RandomImagePresentationLogic {
    var receivedResponse: RandomImage.RandomDogImage.Response?
    func presentRandomImage(response: RandomImage.RandomDogImage.Response) {
        receivedResponse = response
    }
    
}

class RandomImageWorkerSpy: RandomImageWorker {
    
    var receivedNumber: Int? = nil
    var response: RandomImageWorkerResponse? = nil
    override func getRandomImage(number: Int) async -> RandomImageWorkerResponse {
        receivedNumber = number
        return response ?? RandomImageWorkerResponse(message: nil, status: nil)
    }
}
