package com.jetpack.master.diceKoin

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.security.SecureRandom
import java.util.concurrent.atomic.AtomicReference

private const val ASSET_FILENAME = "eff_short_wordlist_2_0.txt"
class PassphraseRepositoryKoin(private val context: Context, private val random: SecureRandom) {
    private val wordsCache = AtomicReference<List<String>>()

    suspend fun generate(count: Int): List<String> {
        val words: List<String>? = wordsCache.get()
        return words?.let { rollDemBones(it, count) }
            ?: loadAndGenerate(count)
    }

    private suspend fun loadAndGenerate(
        count: Int
    ): List<String> = withContext(Dispatchers.IO) {
        val inputStream = context.assets.open(ASSET_FILENAME)

        inputStream.use {
            val words = it.readLines()
                .map { line -> line.split("\t") }
                .filter { pieces -> pieces.size == 2 }
                .map { pieces -> pieces[1] }
            wordsCache.set(words)
            rollDemBones(words, count)
        }
    }

    private fun InputStream.readLines(): List<String> {
        val result = mutableListOf<String>()
        BufferedReader(InputStreamReader(this)).forEachLine { result.add(it); }
        return result
    }

    private fun rollDemBones(words: List<String>, wordCount: Int) =
        List(wordCount) {words[random.nextInt(words.size)]}
}