package com.example.filmsearcher.manager

class ImageHelper {
    companion object {

        fun getImageUrl(imageUrl: String) : String {
            return "https://image.tmdb.org/t/p/w500$imageUrl"
        }
    }
}