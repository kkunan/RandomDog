//
//  RandomImagePresenterTests.swift
//  RandomDogiosTests
//
//  Created by Komkrit Kunanusont on 20/2/2565 BE.
//

import XCTest
@testable import randomdogios

class RandomImagePresenterTests: XCTestCase {

    private var sut: RandomImagePresenter?
    private var spy: RandomImageViewControllerSpy?
    
    override func setUp() {
        sut = RandomImagePresenter()
        spy = RandomImageViewControllerSpy()
        sut?.viewController = spy
    }

    func test_presentRandomImage_should_send_failure_message_if_nil(){
        // arrange
        let response = RandomImage.RandomDogImage.Response(value: nil)
        
        // act
        sut?.presentRandomImage(response: response)
        
        // assert
        XCTAssertEqual(spy?.receivedViewModel?.errorMessage, "Cannot get image")
        XCTAssertNil(spy?.receivedViewModel?.value)
    }
    
    func test_presentRandomImage_should_send_failure_message_if_empty(){
        let response = RandomImage.RandomDogImage.Response(value: [])
        
        // act
        sut?.presentRandomImage(response: response)
        
        // assert
        XCTAssertEqual(spy?.receivedViewModel?.errorMessage, "Cannot get image")
        XCTAssertNil(spy?.receivedViewModel?.value)
    }
    
    func test_presentRandomImage_should_send_correct_urls_if_has_values(){
        let urls = ["url\(Int.random(in: 1...100))", "url\(Int.random(in: 1...100))"]
        let response = RandomImage.RandomDogImage.Response(value: urls.map({ url in
            DogImage(url: url)
        }))
        
        // act
        sut?.presentRandomImage(response: response)
        
        // assert
        XCTAssertNil(spy?.receivedViewModel?.errorMessage)
        let value = spy?.receivedViewModel?.value
        XCTAssertNotNil(value)
        XCTAssertEqual(value!.count, urls.count)
        for (index, item) in value!.enumerated(){
            XCTAssertEqual(item, urls[index])
        }
    }
}

class RandomImageViewControllerSpy: RandomImageDisplayLogic {
    var receivedViewModel: RandomImage.RandomDogImage.ViewModel?
    func displayRandomImage(viewModel: RandomImage.RandomDogImage.ViewModel) {
        receivedViewModel = viewModel
    }
}
