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
 * Main function of problem 1 task 1. This program will use the above functions to convert the plain text string to binary
 * then convert it back to characters.
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

    //plainTextInBinary is a string holding the plaintext string in binary
    val plainTextInBinary = convertCharStringToBinaryString(plainText, charsInBinary, characters)
    println("\nThe binary representation of the plain text is: \n$plainTextInBinary")

    //plainTextBackToChar is a string holding the plaintext string in characters from binary
    val plainTextBackToChar = convertBinaryStringToChars(plainTextInBinary, characters, 5)
    println("The character representation of the binary plain text is: \n$plainTextBackToChar")

}

 */
