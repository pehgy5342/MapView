package com.example.mapview.model

sealed class SchoolFilter {

    enum class SchoolFilterType {
        SORT,
        FILTER,
        OTHER
    }

    abstract fun getType(): SchoolFilterType

    open fun getText(): String = ""


    class SortItem(private val id: String, var sort: Int = 0) : SchoolFilter() {
        override fun getType(): SchoolFilterType {
            return SchoolFilterType.SORT
        }
    }

    class FilterItem() : SchoolFilter() {
        override fun getType(): SchoolFilterType {
            return SchoolFilterType.FILTER
        }
    }

    class OtherItem() : SchoolFilter() {
        override fun getType(): SchoolFilterType {
            return SchoolFilterType.OTHER
        }

        override fun getText(): String {
            return super.getText()
        }
    }


}