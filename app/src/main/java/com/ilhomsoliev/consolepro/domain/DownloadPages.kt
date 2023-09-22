package com.ilhomsoliev.consolepro.domain

import okio.FileSystem
import io.ktor.client.*
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.Path
import okio.Path.Companion.toPath
import io.ktor.utils.io.jvm.javaio.*

class DownloadPages(
    private val fileSystem: FileSystem,
    private val httpClient: HttpClient,
) {
    suspend operator fun invoke(): Path = withContext(Dispatchers.IO) {
        val temporaryFile = (FileSystem.SYSTEM_TEMPORARY_DIRECTORY.toString() +
                Path.DIRECTORY_SEPARATOR +
                FILE_NAME).toPath()

        fileSystem.write(temporaryFile) {
            // note: cannot use http client after closed.
            httpClient.use { client ->
                client.get(ZIP_ASSET_URL)
                    .bodyAsChannel()
                    .copyTo(outputStream())
            }
        }
        return@withContext temporaryFile
    }
}

private const val FILE_NAME = "tldr.zip"
private const val ZIP_ASSET_URL = "https://tldr.sh/assets/tldr.zip"