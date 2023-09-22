package com.ilhomsoliev.consolepro.domain

import com.ilhomsoliev.consolepro.core.Platform
import com.ilhomsoliev.consolepro.data.local.PageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import okio.openZip

class ExtractPages(
    private val fileSystem: FileSystem,
) {
    suspend operator fun invoke(
        path: Path,
        selectedPlatforms: Regex = Platform.allPlatforms()
    ): List<PageModel> = withContext(Dispatchers.IO) {
        val zipFile = fileSystem.openZip(path)

        fun toPage(path: Path): PageModel {
            val platform = path.parent?.name?.let { Platform.formatFromLowercase(it) }
            val pageContent = zipFile.read(path) { readUtf8().trim() }

            return PageModel(
                name = pageContent.substringBefore("\n").removePrefix("#").trim(),
                platform = requireNotNull(platform),
                markdown = pageContent
            )
        }

        return@withContext zipFile.list(ROOT.toPath())
            .filter { it.name matches selectedPlatforms }
            .flatMap(zipFile::list)
            .map(::toPage)
    }
}

private const val ROOT = "/pages"
