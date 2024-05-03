package androidx.compose.samples.crane.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.setValue

class EditableUserInputState(private val hint: String, initialText: String) {

    var text by mutableStateOf(initialText)
        private set

    fun updateText(newText: String) {
        text = newText
    }

    val isHint: Boolean
        get() = text == hint

    companion object {
        private const val KEY_HINT = "KEY_HINT"
        private const val KEY_TEXT = "KEY_TEXT"
        val Saver: Saver<EditableUserInputState, *> = mapSaver(
            save = {
                mapOf(
                    Pair(KEY_HINT, it.hint),
                    Pair(KEY_TEXT, it.hint)
                )
            },
            restore = {
                EditableUserInputState(
                    hint = it[KEY_HINT].toString(),
                    initialText = it[KEY_TEXT].toString(),
                )
            }
        )
    }
}