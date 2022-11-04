import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        KoinHelper.initKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}