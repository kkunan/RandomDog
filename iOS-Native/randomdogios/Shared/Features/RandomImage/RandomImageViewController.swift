//
//  RandomImageViewController.swift
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

protocol RandomImageDisplayLogic: class
{
  func displayRandomImage(viewModel: RandomImage.RandomDogImage.ViewModel)
}

class RandomImageViewController: UIViewController, RandomImageDisplayLogic
{
  var interactor: RandomImageBusinessLogic?
  var router: (NSObjectProtocol & RandomImageRoutingLogic & RandomImageDataPassing)?

  // MARK: Object lifecycle
  
  override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?)
  {
    super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
    setup()
  }
  
  required init?(coder aDecoder: NSCoder)
  {
    super.init(coder: aDecoder)
    setup()
  }
  
  // MARK: Setup
  
  private func setup()
  {
    let viewController = self
    let interactor = RandomImageInteractor()
    let presenter = RandomImagePresenter()
    let router = RandomImageRouter()
    viewController.interactor = interactor
    viewController.router = router
    interactor.presenter = presenter
    presenter.viewController = viewController
    router.viewController = viewController
    router.dataStore = interactor
  }
  
  // MARK: Routing
  
  override func prepare(for segue: UIStoryboardSegue, sender: Any?)
  {
    if let scene = segue.identifier {
      let selector = NSSelectorFromString("routeTo\(scene)WithSegue:")
      if let router = router, router.responds(to: selector) {
        router.perform(selector, with: segue)
      }
    }
  }
  
  // MARK: View lifecycle
  
  override func viewDidLoad()
  {
    super.viewDidLoad()
    randomImage()
  }
  
  // MARK: Do something
  
  //@IBOutlet weak var nameTextField: UITextField!
  
  func randomImage()
  {
    let request = RandomImage.RandomDogImage.Request()
//    interactor?.randomImage(request: request)
  }
  
  func displayRandomImage(viewModel: RandomImage.RandomDogImage.ViewModel)
  {
    //nameTextField.text = viewModel.name
  }
}
