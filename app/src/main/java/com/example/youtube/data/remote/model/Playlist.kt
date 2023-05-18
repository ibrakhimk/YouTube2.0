package com.example.youtube.data.remote.model


data class Playlist(
    val etag: String?,
    val items: List<Item?>?,
    val kind: String?,
    val nextPageToken: String?,
    val pageInfo: PageInfo?
) {
    data class Item(
        val contentDetails: ContentDetails?,
        val etag: String?,
        val id: String?,
        val kind: String?,
        val snippet: Snippet?
    ) {
        data class ContentDetails(
            val itemCount: Int?
        )

        data class Snippet(
            val channelId: String?,
            val channelTitle: String?,
            val description: String?,
            val localized: Localized?,
            val publishedAt: String?,
            val thumbnails: Thumbnails?,
            val title: String?
        ) {
            data class Localized(
                val description: String?,
                val title: String?
            )

            data class Thumbnails(
                val default: Default?,
                val high: High?,
                val maxres: Maxres?,
                val medium: Medium?,
                val standard: Standard?
            ) {
                data class Default(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                )

                data class High(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                )

                data class Maxres(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                )

                data class Medium(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                )

                data class Standard(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                )
            }
        }
    }

    data class PageInfo(
        val resultsPerPage: Int?,
        val totalResults: Int?
    )
}
//data class Playlist(
//    val kind: String,
//    val etag: String,
//    val nextPageToken: String,
//    val items: List<Item>,
//    val pageInfo: PageInfo
//) {
//    data class Item(
//        val kind: String,
//        val etag: String,
//        val id: String,
//        val snippet: Snippet,
//        val contentDetails: ContentDetails
//    ) {
//        data class Snippet(
//            val publishedAt: String,
//            val channelId: String,
//            val title: String,
//            val description: String,
//            val thumbnails: Thumbnails,
//            val channelTitle: String,
//            val playlistId: String,
//            val position: Int,
//            val resourceId: ResourceId,
//            val videoOwnerChannelTitle: String,
//            val videoOwnerChannelId: String
//        ) {
//            data class Thumbnails(
//                val default: Default,
//                val medium: Medium,
//                val high: High,
//                val standard: Standard
//            ) {
//                data class Default(
//                    val url: String,
//                    val width: Int,
//                    val height: Int
//                )
//
//                data class Medium(
//                    val url: String,
//                    val width: Int,
//                    val height: Int
//                )
//
//                data class High(
//                    val url: String,
//                    val width: Int,
//                    val height: Int
//                )
//
//                data class Standard(
//                    val url: String,
//                    val width: Int,
//                    val height: Int
//                )
//            }
//
//            data class ResourceId(
//                val kind: String,
//                val videoId: String
//            )
//        }
//
//        data class ContentDetails(
//            val videoId: String,
//            val videoPublishedAt: String
//        )
//    }
//
//    data class PageInfo(
//        val totalResults: Int,
//        val resultsPerPage: Int
//    )
//}