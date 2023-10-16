package main

import main.IDictionary.Companion.DICTIONARY_NAME
import java.io.File

object HashSetDictionary : IDictionary {

    private var words = mutableSetOf<String>()

    init {
        File(DICTIONARY_NAME).forEachLine {
            words.add(it)
        }
    }
    override fun add(word: String): Boolean {
        return words.add(word)
    }

    override fun find(word: String): Boolean {
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }
}