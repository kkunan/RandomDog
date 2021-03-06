//
//  RandomImagePresenter.swift
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

protocol RandomImagePresentationLogic
{
  func presentRandomImage(response: RandomImage.RandomDogImage.Response)
}

class RandomImagePresenter: RandomImagePresentationLogic
{
var viewController: RandomImageDisplayLogic?
  
  // MARK: Do something
  
  func presentRandomImage(response: RandomImage.RandomDogImage.Response)
  {
      let urls = response.value?.map({ image in
          image.url
      })
      let shouldSendErrorMessage = urls?.isEmpty ?? true
      
      viewController?.displayRandomImage(viewModel: RandomImage.RandomDogImage.ViewModel(value: shouldSendErrorMessage ? nil : urls , errorMessage: shouldSendErrorMessage ? "Cannot get image" : nil))
  }
}
