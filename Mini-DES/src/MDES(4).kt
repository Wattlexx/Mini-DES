/*
import java.lang.Integer.toBinaryString

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
fun checkForLength (str: String, len: Int):String {
    val temp = StringBuilder()
    if (str.length < len){
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
    for (i in pT.indices) {
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
 * desFunction takes an 8-bit binary string [strBits], expands it to 12-bits, XOR's it with [keyBits], divides it into
 * two halves, passes each half through their corresponding S-Box and then appends each result together
 * @param strBits 8-bit binary string representing the bit string to be encrypted
 * @param keyBits 12-bit binary string representing half the key
 * @return the result of the function
 */
fun desFunction (strBits: String, keyBits: String):String {
    val result = StringBuilder()
    //1. B is expanded into 12 bits -> the even position bits (from left to right) are appended to the end of the string
    val expandedBinary = evenExpansion(strBits)

    //2. The expanded strings are now XORed with the key postXOR 12-bits
    val postXOR = xorStrings(expandedBinary, keyBits)

    //3. Resulting 12-bit is split into two parts B1 and B2 of equal length (6-bits each)
    val b1 = postXOR.slice(0..5)
    val b2 = postXOR.slice(6..11)

    //4. Each sBox returns a 4-bit string -> result becomes an 8-bit string
    result.append(sBoxOne(b1))
    result.append(sBoxTwo(b2))

    //5. Output of f is an 8-bit string obtained from the output of the two boxes
    return result.toString()
}

/**
 * [encrypt] follows the definition in problem 1 task 3.
 * @param pT 16-bit binary string -> the plain text binary string
 * @param keyBits 24-bit binary string -> the key
 * @return corresponding 16-bit binary cipher text
 */
fun encrypt(pT:String, keyBits: String):String {
    val L0 = pT.slice(0..7)
    val R0 = pT.slice(8..15)

    val L1 = R0
    val R1 = xorStrings(L0, desFunction(R0, keyBits.slice(0..11)))
    val L2 = R1
    val R2 = xorStrings(L1, desFunction(R1, keyBits.slice(12..23)))

    val temp = StringBuilder()
    temp.append(L2)
    temp.append(R2)
    return temp.toString()
}

/**
 * [decrypt] does the reverse of [encrypt] to get the plain text from the ciphertext
 * @param cT 16-bit binary string -> the cipher text binary string
 * @param keyBits 24-bit binary string -> the key
 * @return corresponding 16-bit binary plaint text
 */
fun decrypt(cT:String, keyBits: String):String {
    val L2 = cT.slice(0..7)
    val R2 = cT.slice(8..15)

    val R1 = L2
    val L1 = xorStrings(R2, desFunction(R1, keyBits.slice(12..23)))
    val R0 = L1
    val L0 = xorStrings(R1, desFunction(R0, keyBits.slice(0..11)))

    val temp = StringBuilder()
    temp.append(L0)
    temp.append(R0)
    return temp.toString()
}

/**
 * convertBinaryStringToChars will convert a bit string to a char string based on [chars]
 * @param cTBinary cipher text binary string
 * @param chars array of characters Z32
 * @param partitionSize the size in bits of each character (5 in this case)
 * @return the cipher text in characters
 */
fun convertBinaryStringToChars(cTBinary:String, chars: Array<Char>, partitionSize: Int):String {
    val cipherTChars = StringBuilder()
    //for every [partitionSize] elements in cTBinary...
    for (i in cTBinary.indices step partitionSize) {
        val aCharBinary = StringBuilder ()
        //append the succeeding [partitionSize] bits
        aCharBinary.append(cTBinary.slice(i until i+partitionSize))
        //convert this 5-bit binary string to a decimal
        val index = aCharBinary.toString().toInt(2)
        //append the corresponding character at position index in chars
        cipherTChars.append(chars[index])
    }
    return cipherTChars.toString()
}

/**
 * Main function of the MDES system. This program will take the plain text and use the above functions to encrypt
 * binary plain text and then decrypt the binary cipher text.
 * @author Nicholas Pinney
 * @date Oct. 4, 2019
 */
fun main () {

    val plainText = "how do you like computer science"

    val characters: Array<Char> = arrayOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','.',',','?','(',')')
    val charsInBinary = createBinaryArrayOfChars()

    //Expand the binary representation to 5-bits
    for (i in charsInBinary.indices){
        charsInBinary[i] = checkForLength(charsInBinary[i].toString(), 5)
    }

    //plainTextInBinary is an array where each element is the corresponding 5-bit binary letter of the plainText
    val plainTextInBinary = convertCharStringToBinaryString(plainText, charsInBinary, characters)
    println("The binary representation of the plain text is: \n$plainTextInBinary")

    println()

    //Encryption Begins
    val partitionedPlainTextInBinary = partitionPlainText(plainTextInBinary, 16)

    val key = "101101010010100101101011" //101101010010 100101101011
    val cipherTextBinary = StringBuilder ()
    for (i in partitionedPlainTextInBinary.indices) {
        cipherTextBinary.append(encrypt(partitionedPlainTextInBinary[i].toString(), key))
    }
    println("The binary representation of the cipher text is: ")
    println("$cipherTextBinary")

    val cipherTextInChars = convertBinaryStringToChars(cipherTextBinary.toString(), characters, 5)
    println("The character representation of the cipher text is: \n$cipherTextInChars")
    //Encryption Ends

    println()

    //Decryption Begins
    val partitionedCipherTextInBinary = partitionPlainText(cipherTextBinary.toString(), 16)

    val decryptionOfCipherTextInBinary = StringBuilder()
    for (i in partitionedCipherTextInBinary.indices) {
        decryptionOfCipherTextInBinary.append(decrypt(partitionedCipherTextInBinary[i].toString(), key))
    }

    println("The binary representation of the decrypted cipher text is: ")
    println("$decryptionOfCipherTextInBinary")

    val decryptionOfCipherTextInChars = convertBinaryStringToChars(decryptionOfCipherTextInBinary.toString(), characters, 5)
    println("The character representation of the decrypted cipher text is: \n$decryptionOfCipherTextInChars")
    //Decryption Ends
}*/
