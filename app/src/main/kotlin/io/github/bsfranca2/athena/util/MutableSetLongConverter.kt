package io.github.bsfranca2.athena.util

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class MutableSetLongConverter : AttributeConverter<MutableSet<Long>, String> {
    override fun convertToDatabaseColumn(longMutableSet: MutableSet<Long>): String {
        return longMutableSet.joinToString(separator = SPLIT_CHAR)
    }

    override fun convertToEntityAttribute(string: String): MutableSet<Long> {
        if (string == "") return mutableSetOf()
        return string.split(SPLIT_CHAR).map { it.toLong() }.toMutableSet()
    }

    companion object {
        private const val SPLIT_CHAR = ";"
    }
}