//
//  RandomImageRouter.swift
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

@objc protocol RandomImageRoutingLogic
{
  //func routeToSomewhere(segue: UIStoryboardSegue?)
}

protocol RandomImageDataPassing
{
  var dataStore: RandomImageDataStore? { get }
}

class RandomImageRouter: NSObject, RandomImageRoutingLogic, RandomImageDataPassing
{
  weak var viewController: RandomImageViewController?
  var dataStore: RandomImageDataStore?
  
  // MARK: Routing
  
  //func routeToSomewhere(segue: UIStoryboardSegue?)
  //{
  //  if let segue = segue {
  //    let destinationVC = segue.destination as! SomewhereViewController
  //    var destinationDS = destinationVC.router!.dataStore!
  //    passDataToSomewhere(source: dataStore!, destination: &destinationDS)
  //  } else {
  //    let storyboard = UIStoryboard(name: "Main", bundle: nil)
  //    let destinationVC = storyboard.instantiateViewController(withIdentifier: "SomewhereViewController") as! SomewhereViewController
  //    var destinationDS = destinationVC.router!.dataStore!
  //    passDataToSomewhere(source: dataStore!, destination: &destinationDS)
  //    navigateToSomewhere(source: viewController!, destination: destinationVC)
  //  }
  //}

  // MARK: Navigation
  
  //func navigateToSomewhere(source: RandomImageViewController, destination: SomewhereViewController)
  //{
  //  source.show(destination, sender: nil)
  //}
  
  // MARK: Passing data
  
  //func passDataToSomewhere(source: RandomImageDataStore, destination: inout SomewhereDataStore)
  //{
  //  destination.name = source.name
  //}
}
