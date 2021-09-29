package practiceRound

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val sc = Scanner(System.`in`)
    val input = sc.nextLine().split(" ")
    val imgHeight = input[0].toInt()
    val imgWeight = input[1].toInt()
    val qrSide = input[2].toInt()
    val imgInCodeRGB = Array(imgHeight) { IntArray(imgWeight) }

    for (i in 0 until imgHeight) {
        val line = sc.nextLine().split(" ")
        for (j in 0 until imgWeight) {
            imgInCodeRGB[i][j] = line[j].toInt()
        }
    }

    print(getMsg(imgInCodeRGB, qrSide))

}

private fun getMsg(
    imgInCodeRGB: Array<IntArray>,
    qrSide: Int,
): String {
    var msg = ""
    val binCode = convertRGBCodeToBinCode(imgInCodeRGB, qrSide)
    binCode.forEach {
        when (val dec = convertBinToDec(it)) {
            0 -> { }
            else -> {
                val char = convertASCIIToSymbol(dec)
                msg += char
            }
        }
    }
    return msg
}

private fun convertRGBCodeToBinCode(
    codeRGB: Array<IntArray>,
    qrSide: Int
): ArrayList<Long> {
    val binCode = ArrayList<Long>()
    val conciseFirstBinCode = getConciseFirstCode(getFirstBinCode(codeRGB), qrSide)
    (conciseFirstBinCode.indices).forEach {
        val byteLines = convertLineToByteLines(
            getArrLineByNumber(conciseFirstBinCode, it)
        )
        for (i in byteLines.indices) {
            binCode
                .add(
                    convertFirstBinCodeLineToBin(getArrLineByNumber(byteLines, i))
                )
        }
    }
    return binCode
}

fun convertFirstBinCodeLineToBin(firstBinLine: ArrayList<Int>): Long {
    var bin = ""
    firstBinLine.forEach {
        bin = bin.plus("$it")
    }
    return bin.toLong()
}

private fun getConciseFirstCode(
    firstBinCode: Array<IntArray>,
    qrSide: Int
): Array<IntArray> {
    val conciseHeight = firstBinCode.size / qrSide
    val conciseWeight = firstBinCode[0].size / qrSide
    val conciseFirstBinCode = Array(conciseHeight) { IntArray(conciseWeight) }
    var i = 0
    while (i < firstBinCode.size) {
        var j = 0
        while (j < firstBinCode[0].size) {
            var qrSum = 0
            for (nextI in 0 until qrSide) {
                for (nextJ in 0 until qrSide) {
                    qrSum += firstBinCode[i + nextI][j + nextJ]
                }
            }
            conciseFirstBinCode[i / qrSide][j / qrSide] = qrSum / (qrSide * qrSide)
            j += qrSide
        }
        i += qrSide
    }
    return conciseFirstBinCode
}

private fun getFirstBinCode(codeRGB: Array<IntArray>): Array<IntArray> {
    val codeRGBHeight = codeRGB.size
    val codeRGBWeight = codeRGB[0].size
    val firstBinCode = Array(codeRGBHeight) { IntArray(codeRGBWeight) }
    for (i in 0 until codeRGBHeight) {
        for (j in 0 until codeRGBWeight) {
            firstBinCode[i][j] = convertRGBCodeCellToBinCode(codeRGB[i][j]).toInt()
        }
    }
    return firstBinCode
}

private fun getArrLineByNumber(
    arr: Array<IntArray>,
    lineNumber: Int
): ArrayList<Int> {
    val arrLine = ArrayList<Int>()
    for (i in arr[0].indices) {
        arrLine.add(arr[lineNumber][i])
    }
    return arrLine
}

private fun convertLineToByteLines(rgbCodeLine: ArrayList<Int>): Array<IntArray> {
    val byteSize = 8
    val rgbCodeBitLinesNum = rgbCodeLine.size / byteSize
    val rgbCodeBitLines = Array(rgbCodeBitLinesNum) { IntArray(byteSize) }
    var rgbCodeLinePosition = 0
    while (rgbCodeLinePosition < rgbCodeLine.size) {
        for (i in 0 until rgbCodeBitLinesNum) {
            for (j in 0 until byteSize) {
                rgbCodeBitLines[i][j] = rgbCodeLine[rgbCodeLinePosition]
                rgbCodeLinePosition++
            }
        }
    }
    return rgbCodeBitLines
}

private fun convertRGBCodeCellToBinCode(rgbCell: Int): String {
    var cell = rgbCell
    var colorCode = 0
    while (cell != 0) {
        colorCode += cell % 10
        cell /= 10
    }
    return if (colorCode >= 10) {
        "0"
    } else {
        "1"
    }
}

private fun convertBinToDec(bin: Long): Int {
    var binary: Long = bin
    var dec = 0
    var coefficient = 1
    while (binary != 0L) {
        dec += (binary % 10 * coefficient).toInt()
        binary /= 10
        coefficient *= 2
    }
    return dec
}

private fun convertASCIIToSymbol(code: Int): Char {
    return code.toChar()
}
