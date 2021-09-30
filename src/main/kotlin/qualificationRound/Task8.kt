package qualificationRound

class Row {
    var column: Column? = null

    //other code
}

class Column {
    fun inc() {
        //some work
    }
}

fun extract(row: Row?): Column? = run {
    row?.let {
        with(it) {
            column?.also {
                it.apply { inc() }
            }
        }
    }
}