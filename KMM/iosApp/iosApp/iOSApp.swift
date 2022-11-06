import SwiftUI
import shared

@main
struct iOSApp: App {
    
    private var stateHandler: RandomDogViewStateHandler
    @ObservedObject var object = DogImageObject()
    
    init() {
        KoinHelperKt.doInitKoin()
        stateHandler = RandomDogHelper().getStateHandler()
    }
	var body: some Scene {
        let stateHandler = RandomDogHelper().getStateHandler()
        WindowGroup<ContentView> {
            ContentView(image: object.image, onRandomClicked: {
                stateHandler.randomNextImage { newState in
                    if let imageUrl = newState.dogImageUrl {
                        
                        print("imageUrl: \(imageUrl)")
                        if let url = URL(string: imageUrl){
                            print("url: \(url)")
                            if let data = try? Data(contentsOf: url) {
                                print("data: \(data)")
                                object.image = UIImage(data: data) ?? UIImage()
                                print("image: \(object.image)")
                            }
                        }
                    }
                    print("newState: \(newState)")
                } completionHandler: { error in
                    // do nothing
                    print("error: \(error)")
                }
            })
		}
	}
}


@MainActor
class DogImageObject: ObservableObject {
    @Published var image: UIImage = UIImage()
}
