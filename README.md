# Projet Stéganographie

A Java steganography library that implements the Least Significant Bit (LSB) technique to hide messages within image files. This academic project provides a complete framework for concealing and revealing data in PNG and JPG images with optional encryption and compression features.

## Features

- **LSB Steganography**: Hide messages in image files using the Least Significant Bit technique
- **Message Encryption**: Optional Blowfish encryption for enhanced security
- **Data Compression**: Optional gzip compression to reduce message size
- **Multiple Image Formats**: Support for PNG and JPG image files
- **Modular Design**: Abstract base class with extensible architecture
- **Complete Testing Suite**: JUnit tests for all core functionality

## Project Structure

### Core Classes

#### Steganography Framework
- **`Steganographie`** (abstract): Base class defining the steganography interface
- **`Stegano_image`**: Main implementation for image-based steganography
- **`Lettre`**: Data class representing the message to be hidden
- **`Enveloppe`**: Data class representing the carrier image

#### Utility Classes
- **`Compresser`**: Handles gzip compression and decompression
- **`Cryptage`**: Provides Blowfish encryption and decryption
- **`GestionFichier`**: File I/O operations and binary data handling

### Directory Structure

```
projetSteganographie/
├── src/                          # Source code
│   ├── steganographie/           # Core steganography classes
│   │   ├── Steganographie.java   # Abstract base class
│   │   ├── Stegano_image.java    # Main implementation
│   │   ├── Lettre.java           # Message data class
│   │   ├── Enveloppe.java        # Image data class
│   │   └── test_JUnit/           # JUnit tests for steganography
│   └── classeStatic/             # Utility classes
│       ├── Compresser.java       # Compression utilities
│       ├── Cryptage.java         # Encryption utilities
│       ├── GestionFichier.java   # File management
│       └── test_JUnit/           # JUnit tests for utilities
├── bin/                          # Compiled Java classes
├── fichierTest/                  # Test files and sample images
│   ├── messageACacher.txt        # Sample message file
│   ├── imageTest.jpg             # Sample JPG image
│   ├── imageTest2.png            # Sample PNG image
│   ├── imageTest3.png            # Large test image
│   ├── imageTest4.png            # Large test image
│   ├── imageAvecMessageCacher.png # Image with hidden message
│   └── MessageDevoiler           # Revealed message output
├── fichierTemporaire/            # Temporary files for processing
└── Rapport/                      # Project documentation (French)
```

## Requirements

### Software Dependencies
- **Java**: JDK with `javax.crypto` and `javax.imageio` libraries
- **External Tools**: `gzip` command-line utility for compression
- **Testing**: JUnit framework for running tests

### Java Libraries Used
- `java.awt.image.BufferedImage` - Image manipulation
- `javax.imageio.ImageIO` - Image I/O operations
- `javax.crypto.Cipher` - Blowfish encryption
- `java.nio.ByteBuffer` - Binary data handling

## Compilation

Compile the entire project using the following commands:

```bash
# Compile all steganography classes
javac -d bin -cp bin src/steganographie/*.java

# Compile all utility classes
javac -d bin -cp bin src/classeStatic/*.java

# Compile all classes at once
javac -d bin -cp bin src/steganographie/*.java src/classeStatic/*.java
```

## Usage Examples

### Basic Usage

```java
import steganographie.Stegano_image;

// Create steganography instance
Stegano_image stegano = new Stegano_image(
    "path/to/message.txt",        // Message file path
    "path/to/carrier_image.png"   // Carrier image path
);

// Hide message in image
stegano.dissimulerDonnee(
    1,                            // Algorithm number (1 = LSB)
    "path/to/output_image.png",   // Output image path
    false,                        // Compress message (true/false)
    0                             // Encryption key (0 = no encryption)
);

// Reveal hidden message
stegano.devoilerDonnee(
    1,                            // Algorithm number
    "path/to/image_with_message.png", // Image containing hidden message
    "path/to/revealed_message.txt",   // Output message file
    false,                        // Message was compressed (true/false)
    0                             // Decryption key (0 = no decryption)
);
```

### Advanced Usage with Encryption and Compression

```java
// Hide message with compression and encryption
stegano.dissimulerDonnee(
    1,                            // LSB algorithm
    "output_secure.png",          // Output image
    true,                         // Enable compression
    12345                         // Encryption key
);

// Reveal compressed and encrypted message
stegano.devoilerDonnee(
    1,                            // LSB algorithm
    "output_secure.png",          // Image with hidden message
    "revealed_secure.txt",        // Output file
    true,                         // Message was compressed
    12345                         // Decryption key
);
```

### Capacity Verification

```java
// Check if image can hold the message
boolean canHide = stegano.verificationComptabilite("carrier_image.png");
if (canHide) {
    System.out.println("Image has sufficient capacity for the message");
} else {
    System.out.println("Image is too small for the message");
}
```

## Supported Image Formats

- **PNG**: Recommended format for lossless steganography
- **JPG/JPEG**: Supported but may introduce compression artifacts

## Algorithm Details

### LSB Steganography (Algorithm 1)
- Replaces the least significant bit of each color channel (RGB)
- Processes pixels sequentially from top-left to bottom-right
- Reserves header space for message length and metadata
- Uses 48 bits total for headers (16 reserved + 32 for message length)

### Data Processing Flow
1. **Encoding**: Message → Optional Encryption → Optional Compression → LSB Embedding
2. **Decoding**: LSB Extraction → Optional Decompression → Optional Decryption → Message

## Testing

The project includes comprehensive JUnit tests for all major components:

### Running Tests
```bash
# Compile test classes
javac -d bin -cp bin:junit.jar src/steganographie/test_JUnit/*.java
javac -d bin -cp bin:junit.jar src/classeStatic/test_JUnit/*.java

# Run specific test classes
java -cp bin:junit.jar org.junit.runner.JUnitCore steganographie.test_JUnit.Stegano_imageTest
java -cp bin:junit.jar org.junit.runner.JUnitCore classeStatic.test_JUnit.CompresserTest
```

### Test Coverage
- **Stegano_imageTest**: Tests message hiding and revealing functionality
- **CompresserTest**: Tests gzip compression and decompression
- **CryptageTest**: Tests Blowfish encryption and decryption
- **GestionFichierTest**: Tests file I/O operations

## Limitations

- **Image Capacity**: Message size is limited by image dimensions (approximately width × height ÷ 3 bytes)
- **Format Restrictions**: Only supports images with standard RGB color channels
- **External Dependency**: Requires system `gzip` command for compression features
- **Sequential Processing**: Processes images pixel by pixel (not optimized for very large images)

## Academic Project Information

**Institution**: IUT de Nantes  
**Year**: 2015  
**Project Type**: Academic Research Project  

### Contributors
- **Jezequel Corentin**
- **Saingre Dimitri**  
- **Beaulieu Loic**
- **Caillaud Pierre-Antoine**

## Technical Notes

- **Language**: Java (with French comments and variable names)
- **Architecture**: Object-oriented design with abstract base classes
- **Design Pattern**: Template method pattern in the abstract `Steganographie` class
- **Error Handling**: Basic exception handling with console output
- **Memory Management**: Uses temporary files for compression operations

## License

This is an academic project created for educational purposes at IUT de Nantes. Please refer to your institution's policies regarding the use and distribution of academic work.
