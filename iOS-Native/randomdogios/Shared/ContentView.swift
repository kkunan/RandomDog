//
//  ContentView.swift
//  Shared
//
//  Created by Komkrit Kunanusont on 20/2/2565 BE.
//

import SwiftUI

struct ContentView: View {
    var onRandomClicked: (() -> Void)?
    var image: UIImage = UIImage()
    
    var body: some View {
        ZStack {
            Color.init(hex: 0xFBDEB3).ignoresSafeArea()
            Image("DogIconForeground").resizable().scaledToFill()
            
            VStack {
                Image(uiImage: image).frame(width: 200, height: 200, alignment: Alignment.center).background(Color.init(hex: 0xF9A01C))
                    .cornerRadius(16).shadow(radius: 8)
                    

                Spacer(minLength: 24)
                
                Button(action:{
                    onRandomClicked?()
                }){
                    Text("RANDOM!").padding(.vertical, 16)
                        .padding(.horizontal, 24)
                        .background(Color.init(hex: 0xF9A01C))
                        .foregroundColor(Color.white)
                        .cornerRadius(50)
                        .shadow(radius: 4)
                        .font(.system(size: 30, weight: .heavy, design: .default))
                }
            }.fixedSize()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(onRandomClicked: {
            print("clicked")
        }, image: UIImage(imageLiteralResourceName: "DogIconForeground")
                    )
    }
}


extension Color {
    init(hex: UInt, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
}
