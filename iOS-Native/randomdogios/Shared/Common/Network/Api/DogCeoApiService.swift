//
//  DogCeoApiService.swift
//  randomdogios (iOS)
//
//  Created by Komkrit Kunanusont on 20/2/2565 BE.
//

import Foundation

class DogCeoApiService {
    func getRandomImage(number: Int, completion: @escaping (RandomImageWorkerResponse) -> Void) {
        // Create URL
                guard let url = URL(string: "https://dog.ceo/api/breeds/image/random/\(number)") else {
                    completion(RandomImageWorkerResponse(message: nil, status: "failed"))
                    return
                }
                
                // Create URL session data task
                URLSession.shared.dataTask(with: url) { data, _, error in

                    if let error = error {
                        completion(RandomImageWorkerResponse(message: nil, status: "failed"))
                        return
                    }
                    
                    guard let data = data else {
                        completion(RandomImageWorkerResponse(message: nil, status: "failed"))
                        return
                    }
                    
                    do {
                        // Parse the JSON data
                        let result = try JSONDecoder().decode(RandomImageWorkerResponse.self, from: data)
                        completion(result)
                    } catch {
                        completion(RandomImageWorkerResponse(message: nil, status: "failed"))
                    }
                    
                }.resume()
    }
}
