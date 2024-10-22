# Compression Program - README

## Overview

This Java program provides an implementation of a compression and decompression algorithm for sequences of integer values. The algorithm compresses a sequence of integers by calculating the differences between consecutive values and encoding them using variable-length bit codes, optimized to minimize the size of sequences with small differences or repeated values.

The compression scheme is particularly effective for sequences where consecutive values are similar, allowing for more efficient storage and transmission. The program can also decompress an encoded sequence back to its original form.

## Features

- **Compression**: Encodes integer sequences by calculating differences and applying various encoding rules to achieve compression.
- **Decompression**: Reconstructs the original sequence from the compressed binary representation.
- **Support for Repeated Values**: Efficiently encodes sequences with repeated values to minimize storage size.
- **Java Implementation**: Written entirely in Java, making it easy to run and modify.

## How It Works

1. **Input Sequence**: The program accepts an array of integer values.
2. **Compression Algorithm**:
   - The first value is encoded with 8 bits.
   - Differences between consecutive values are encoded based on the size of the difference:
     - Small differences are encoded with fewer bits.
     - Larger differences use more bits or are encoded with a special "absolute coding" mechanism.
   - Repeated values are encoded using a repetition code to minimize redundancy.
   - A special end code (`11`) marks the end of the sequence.
3. **Decompression Algorithm**: Reverses the compression process to reconstruct the original sequence from the encoded data.

## Usage

To use the compression program, compile and run the Java class `Compression`. The main class includes a sample input sequence to demonstrate the compression and decompression process.

### Example

```java
public static void main(String[] args) {
    int[] sequence = {55, 53, 53, 53, 53, 53, 10, 10, 11, 11, 11, 11};
    String encoded = compressSequence(sequence);
    System.out.println("Encoded sequence: " + encoded);
    int[] decoded = decompressSequence(encoded);
    System.out.print("Decoded sequence: ");
    for (int value : decoded) {
        System.out.print(value + " ");
    }
}
```

Running the above code will output:

- **Encoded Sequence**: The binary representation of the compressed data.
- **Decoded Sequence**: The reconstructed sequence that matches the original input.

## Compilation and Execution

To compile and run the program, follow these steps:

1. **Compile the Java Class**:

   ```sh
   javac Compression.java
   ```

2. **Run the Program**:

   ```sh
   java Compression
   ```

## File Structure

- **Compression.java**: Contains the implementation of the compression and decompression algorithms, along with the main method to demonstrate usage.

## Algorithm Details

- **Difference Encoding**: Uses a variable number of bits to represent differences between consecutive values.
- **Repetition Encoding**: Encodes repeated values using a special 3-bit code to indicate the number of repetitions.
- **Absolute Encoding**: Values with large differences are encoded with a dedicated 9-bit representation.

## Limitations

- This compression scheme is most effective when the differences between consecutive values are small.
- The maximum supported difference for efficient encoding is ±30; differences beyond this range use more bits.

## Requirements

- **Java 8 or higher**: The program requires a Java Development Kit (JDK) to compile and run.

## License

This program is released under the MIT License. Feel free to use, modify, and distribute it as needed.

## Contact

If you have any questions or suggestions for improvements, feel free to reach out.

## Acknowledgements

This project is based on an algorithm designed for educational purposes to demonstrate effective data compression techniques for integer sequences.

https\://github.com/vankaler/RV\_01 i created this repo and I want to push my code to it thru the cli. Can you write me cli commands for git 

