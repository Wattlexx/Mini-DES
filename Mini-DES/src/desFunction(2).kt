/*import java.lang.Integer.toBinaryString

/**
 * createBinaryArrayOfChars will create an array of numbers 0 to 31 then
 * create another array to hold the binary representation of these numbers
 * @return Array of binary strings
 */
fun createBinaryArrayOfChars():Array<String?> {
    //Creates an array of numbers from 0 to 31 to represent the array of chars
    val ints = IntArray(32)
    for (i in 0..31) {
        ints[i] = i
    }

    //Create an array of binary strings of the corresponding ints[]
    val charsInBinary: Array<String?> = arrayOfNulls(32)
    for (i in charsInBinary.indices){
        charsInBinary[i] = toBinaryString(ints[i])
    }
    return charsInBinary
}

/**
 * checkForLength will check if [str] is of size [len] bits
 * @param str binary string to check the length of
 * @param len the desired length of the string
 * @return bit-string of length [len] created by appending 0's to the front
 */
fun checkForLength(str: String, len: Int):String {
    val temp = StringBuilder()
    if (str.length < len) {
        //difference between str length and requested length
        val shortComings = len - str.length
        //add 0's to front of string
        for (i in 0 until shortComings) {
            temp.append("0")
        }
    }
    //then append the original bit-string and return the string
    return temp.append(str).toString()
}

/**
 * convertCharStringToBinaryString takes a plain text character string [pT] and creates
 * the binary representation of the string by taking the index of a char in [pT] in [chars] to append
 * the corresponding bit string [charsInBinary] index
 * @param pT the original plain text
 * @param charsInBinary array of binary strings representing the position of the characters in [chars]
 * @param chars array of characters -> Z32
 * @return the binary representation of the plain text
 */
fun convertCharStringToBinaryString(pT: String, charsInBinary: Array<String?>, chars: Array<Char>):String {
    val binaryStringOfPlainText = StringBuilder()
    //for every char in pT...
    for (i in 0 until pT.length) {
        var index = 0
        //for every element in chars...
        for (j in chars.indices){
            //find the pT char in chars and record the index
            if (pT[i] == chars[j])
                break
            index++
        }
        //append the binary representation of this character based on the index of the plain text char in chars
        binaryStringOfPlainText.append(charsInBinary[index])
    }
    return binaryStringOfPlainText.toString()
}

/**
 * partitionPlainText will partition the plain text binary string [pTBinary] into groups of [partitionSize]
 * @param pTBinary the plain text binary string
 * @param partitionSize the size of the groups to make
 * @return an array where each element is a string of length [partitionSize] of [pTBinary]
 */
fun partitionPlainText(pTBinary: String, partitionSize: Int):Array<String?> {
    val partitionedPTInB: Array<String?> = arrayOfNulls(pTBinary.length/partitionSize)
    var index = 0
    //iterate through pTBinary by partitionSize
    for (i in pTBinary.indices step partitionSize) {
        val joinedStrings = StringBuilder ()
        //append the string from i to i+partitionSize
        joinedStrings.append(pTBinary.slice(i until i+partitionSize))
        //put this string in the array
        partitionedPTInB[index] = joinedStrings.toString()
        index++
    }
    return partitionedPTInB
}

/**
 * evenExpansion accepts a [bitString] to expand by 1/2 by appending
 * the bits in the even positions to the end of the string
 * @param bitString the string to expand
 * @return the expanded bitString
 */
fun evenExpansion(bitString: String):String {
    val newStr = StringBuilder()
    newStr.append(bitString);
    for (i in bitString.indices) {
        if (i%2 != 0)
            newStr.append(bitString[i])
    }
    return newStr.toString()
}

/**
 * xorStrings takes [bitStringOne] and [bitStringTwo] and XOR's each bit to produce a result
 * @param bitStringOne a bit string of length n
 * @param bitStringTwo a bit string of length n
 * @return bit string of length n
 */
fun xorStrings(bitStringOne: String, bitStringTwo: String):String {
    val newStr = StringBuilder()
    //for every bit in both strings...
    for (i in bitStringOne.indices) {
        //take a bit of each, convert to int and xor together
        val oBit = bitStringOne[i].toInt()
        val tBit = bitStringTwo[i].toInt()
        val result = oBit.xor(tBit)
        //append the resulting xor bit to newStr
        newStr.append(result)
    }
    return newStr.toString()
}

/**
 * sBoxOne takes a 6-bit string [strBits] and passes it through the S-box 2D array declared in this function
 * @param strBits a 6-bit binary string
 * @return the 4-bit binary string of the intersection of the column and row determined by [strBits]
 */
fun sBoxOne(strBits:String):String {
    val row = StringBuilder()
    //row is the first and last bits of strBits
    row.append(strBits[0]); row.append(strBits[5])
    //column are all the middle bits
    val column = strBits.slice(1..4)

    val sBOX: Array<IntArray> = arrayOf(
        intArrayOf(15, 3, 0, 13),
        intArrayOf(1, 13, 14, 8),
        intArrayOf(8, 4, 7, 10),
        intArrayOf(14, 7, 11, 1),
        intArrayOf(6, 15, 10, 3),
        intArrayOf(11, 2, 4, 15),
        intArrayOf(3, 8, 13, 4),
        intArrayOf(4, 14, 1, 2),
        intArrayOf(9, 12, 5, 11),
        intArrayOf(7, 0, 8, 6),
        intArrayOf(2, 1, 12, 7),
        intArrayOf(13, 10, 6, 12),
        intArrayOf(12, 6, 9, 0),
        intArrayOf(0, 9, 3, 5),
        intArrayOf(5, 11, 2, 14),
        intArrayOf(10, 5, 15, 9)
    )

    //get the corresponding S-box number by converting these binary strings to decimal ints
    val fourBitFromBox = sBOX[column.toInt(2)][row.toString().toInt(2)]
    //return a 4-bit string by converting S-box number to binary string then checking its length
    return checkForLength(toBinaryString(fourBitFromBox), 4)
}

/**
 * sBoxTwo takes a 6-bit string [strBits] and passes it through the S-box 2D array declared in this function
 * @param strBits a 6-bit binary string
 * @return the 4-bit binary string of the intersection of the column and row determined by [strBits]
 */
fun sBoxTwo(strBits:String):String {
    val rowTwo = StringBuilder()
    //row is the first and last bits of strBits
    rowTwo.append(strBits[0]); rowTwo.append(strBits[5])
    //column are all the middle bits
    val columnTwo = strBits.slice(1..4)

    val sBOXTwo: Array<IntArray> = arrayOf(
        intArrayOf(7, 13, 10, 3),
        intArrayOf(13, 8, 6, 15),
        intArrayOf(14, 11, 9, 0),
        intArrayOf(3, 5, 0, 6),
        intArrayOf(0, 6, 12, 10),
        intArrayOf(6, 15, 11, 1),
        intArrayOf(9, 0, 7, 13),
        intArrayOf(10, 3, 13, 8),
        intArrayOf(1, 4, 15, 9),
        intArrayOf(2, 7, 1, 4),
        intArrayOf(8, 2, 3, 5),
        intArrayOf(5, 12, 14, 11),
        intArrayOf(11, 1, 5, 12),
        intArrayOf(12, 10, 2, 7),
        intArrayOf(4, 14, 8, 2),
        intArrayOf(15, 9, 4, 14)
    )

    //get the corresponding S-box number by converting these binary strings to decimal ints
    val fourBitFromBox = sBOXTwo[columnTwo.toInt(2)][rowTwo.toString().toInt(2)]
    //return a 4-bit string by converting S-box number to binary string then checking its length
    return checkForLength(toBinaryString(fourBitFromBox), 4)
}

/**
 * Main function of problem 1 task 2. This program will take the plain text and use the above functions to output the
 * 8-bit string of S-box 1 + S-Box 2.
 * @author Nicholas Pinney
 * @date Oct. 4, 2019
 */
fun main () {

    val plainText = "abcdefghijklmnopqrstuvwxyz .,?()"

    val characters: Array<Char> = arrayOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','.',',','?','(',')')
    val charsInBinary = createBinaryArrayOfChars()

    //Expand the binary representation to 5-bits
    for (i in charsInBinary.indices){
        charsInBinary[i] = checkForLength(charsInBinary[i].toString(), 5)
    }
    println("The characters in binary are:")
    charsInBinary.forEach { print("$it ") }
    println()

    //plainTextInBinary is an array where each element is the corresponding 5-bit binary letter of the plainText
    val plainTextInBinary = convertCharStringToBinaryString(plainText, charsInBinary, characters)
    println("The binary representation of the plain text is: \n$plainTextInBinary")

    //The plaintext is partitioned into groups of 8-bits to be properly expanded
    val expandedBinary: Array<String?> = partitionPlainText(plainTextInBinary, 8)

    //1. B is expanded into 12 bits -> the even position bits (from left to right) are appended to the end of the string
    for (i in expandedBinary.indices) {
        expandedBinary[i] = evenExpansion(expandedBinary[i].toString())
    }

    val key = "100000001111"//101101011110"

    //2. Expanded strings are now XORed with the round key K
    for (i in expandedBinary.indices) {
        expandedBinary[i] = xorStrings(expandedBinary[i].toString(), key)
    }
    println("The expanded binary elements XORed with key are:")
    expandedBinary.forEach { print("$it ") }

    //The XORed bit-string are broken up into 2 halves and put through their corresponding S-box
    for (i in expandedBinary.indices) {
        //3. Resulting 12-bit is split into two parts B1 and B2 of equal length
        val b1 = expandedBinary[i].toString().slice(0..5)
        val b2 = expandedBinary[i].toString().slice(6..11)

        //4. B1 passes through S1 and B2 -> S2
        val newB1 = sBoxOne(b1)
        val newB2 = sBoxTwo(b2)
        expandedBinary[i] = StringBuilder().append(newB1, newB2).toString()
    }
    //5. Output of f is an 8-bit string obtained from the output of the two boxes
    println("\n\nAfter passing through S-boxes, results are (newB1newB2):")
    expandedBinary.forEach { print("$it ") }

}

 */
