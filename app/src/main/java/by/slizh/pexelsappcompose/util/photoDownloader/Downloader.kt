package by.slizh.pexelsappcompose.util.photoDownloader

interface Downloader {
    fun downloadFile(url: String): Long
}
