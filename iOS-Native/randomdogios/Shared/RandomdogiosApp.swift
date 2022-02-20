//
//  randomdogiosApp.swift
//  Shared
//
//  Created by Komkrit Kunanusont on 20/2/2565 BE.
//

import SwiftUI

@main
struct RandomdogiosApp: App {
    @ObservedObject var object = DogImageObject()
    
    var body: some Scene {
        WindowGroup {
            ContentView(onRandomClicked: {
                randomImage()
            }, image: object.image)
        }
    }
    
    func randomImage(){
        let interactor = RandomImageInteractor()
        interactor.worker = RandomImageWorker()
        interactor.worker?.apiService = DogCeoApiService()
        let presenter = RandomImagePresenter()
        presenter.viewController = DisplayLogic(updateImage: { image in
            DispatchQueue.main.async {
                object.image = image
            }
        })
        interactor.presenter = presenter
        
        Task {
            await interactor.randomImage(request: RandomImage.RandomDogImage.Request())
        }
        
    }
}

class DogImageObject: ObservableObject {
    @Published var image: UIImage = UIImage()
}

class DisplayLogic: RandomImageDisplayLogic {
    private let updateImage: (UIImage) -> Void
    init(updateImage: @escaping (UIImage) -> Void){
        self.updateImage = updateImage
    }
    func displayRandomImage(viewModel: RandomImage.RandomDogImage.ViewModel) {
        if(viewModel.value?.isEmpty ?? true){
            // do nothing
        } else {
            do {
            let url = URL(string: viewModel.value![0])
            let data = try? Data(contentsOf: url!)
                if let uiImage = UIImage(data: data!) {
                    updateImage(uiImage)
                }
            } catch {
                // throw error
            }
        }
    }
}
