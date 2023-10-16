package main

data class Date(val year: Int, val month: Int, val day: Int) : Comparable<Date> {
    //Define an extension function that checks whether the year of the date is a leap year.
    fun isLeapYear() = year % 4 == 0 && year % 100 != 0 || year % 400 == 0
    //Define an extension function that checks whether the date is a valid one!
    fun isValid() : Boolean {
        if (month < 1 || month > 12) {
            return false
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30) {
                return false
            }
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day > 31) {
                return false
            }
        }
        if(month == 2 && !isLeapYear()) {
            if (day > 28) {
                return false
            }
        }
        if (isLeapYear()){
            if (day > 29) {
                return false
            }
        }
        return true
    }

    override fun compareTo(other: Date): Int {
        if (year > other.year) return 1
        if (year < other.year) return -1
        if (month > other.month) return 1
        if (month < other.month) return -1
        if (day > other.day) return 1
        if (day < other.day) return -1
        return 0
    }


}