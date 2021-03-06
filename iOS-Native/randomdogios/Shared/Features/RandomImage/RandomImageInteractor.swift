//
//  RandomImageInteractor.swift
//  randomdogios
//
//  Created by Komkrit Kunanusont on 20/2/2565 BE.
//  Copyright (c) 2565 BE ___ORGANIZATIONNAME___. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit

protocol RandomImageBusinessLogic
{
  func randomImage(request: RandomImage.RandomDogImage.Request) async
}

protocol RandomImageDataStore
{
  //var name: String { get set }
}

class RandomImageInteractor: RandomImageBusinessLogic, RandomImageDataStore
{
  var presenter: RandomImagePresentationLogic?
  var worker: RandomImageWorker?
  
  // MARK: randomImage
  
  func randomImage(request: RandomImage.RandomDogImage.Request) async
  {
      let response = await worker?.getRandomImage(number: request.number)
      let dogImages = response?.message?.map({ url in
          DogImage(url: url)
      })
      
      presenter?.presentRandomImage(response: RandomImage.RandomDogImage.Response(value: dogImages))
  }
}
