package problem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class EncryptionDecryptionStreamTest {
	SubstitutionCipher sCipher;
	String expectedPlain;

	@Before
	public void setup() {
		sCipher = new SubstitutionCipher();
		expectedPlain = "This is a the first line!\nAnd this is the 2nd line!\nAlso the third line, added!";
	}

	/**
	 * Students can generally pass this test no problem.
	 * 
	 */
	@Test
	public final void testSingleByteReadWrite() throws IOException {
		// Configure and encrypt data
		ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
		EncryptTextEditor eOutStream = new EncryptTextEditor(bOutStream, sCipher);

		// Write to the stream
		for (char c : expectedPlain.toCharArray()) {
			eOutStream.write(c);
		}
		eOutStream.close();

		// Read the encrypted data and check if the transformation worked
		String cipher = bOutStream.toString();
		assertNotEquals("Encryption did not work!", expectedPlain, cipher);

		// Configure and decrypt data
		ByteArrayInputStream bInStream = new ByteArrayInputStream(cipher.getBytes());
		DecryptTextEditor dInStream = new DecryptTextEditor(bInStream, sCipher);
		char[] buffer = new char[expectedPlain.length()];

		int input = 0;
		int i = 0;
		while ((input = dInStream.read()) != -1) {
			buffer[i++] = (char) input;
		}
		dInStream.close();

		// Read the decrypted data and check if it matches the original one
		String actualPlain = new String(buffer);

		assertEquals("Symmetry broken!", expectedPlain, actualPlain);
	}

	/**
	 * Student code tends to fail one of the next two tests.
	 * 
	 * A decorator must decorate EVERY method.
	 * 
	 */
	@Test
	public final void testVariableByteReadWrite() throws IOException {
		// Configure and encrypt data
		ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
		EncryptTextEditor eOutStream = new EncryptTextEditor(bOutStream, sCipher);

		int byteLength = expectedPlain.getBytes().length;
		// Write entire byte array to stream
		eOutStream.write(expectedPlain.getBytes(), 0, byteLength);

		eOutStream.close();

		// Read the encrypted data and check if the transformation worked
		String cipher = bOutStream.toString();
		System.err.println(cipher);
		assertNotEquals("Encryption did not work!", expectedPlain, cipher);

		// Configure and decrypt data
		ByteArrayInputStream bInStream = new ByteArrayInputStream(cipher.getBytes());
		DecryptTextEditor dInStream = new DecryptTextEditor(bInStream, sCipher);
		byte[] buffer = new byte[byteLength];

		dInStream.read(buffer, 0, byteLength);
		dInStream.close();

		// Read the decrypted data and check if it matches the original one
		String actualPlain = new String(buffer);

		assertEquals("Symmetry broken!", expectedPlain, actualPlain);
	}

	@Test
	public final void testMultiByteReadWrite() throws IOException {
		// Configure and encrypt data
		ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
		EncryptTextEditor eOutStream = new EncryptTextEditor(bOutStream, sCipher);

		// Write entire byte array to stream
		eOutStream.write(expectedPlain.getBytes());

		eOutStream.close();

		// Read the encrypted data and check if the transformation worked
		String cipher = bOutStream.toString();
		System.err.println(cipher);
		assertNotEquals("Encryption did not work!", expectedPlain, cipher);

		// Configure and decrypt data
		ByteArrayInputStream bInStream = new ByteArrayInputStream(cipher.getBytes());
		DecryptTextEditor dInStream = new DecryptTextEditor(bInStream, sCipher);
		byte[] buffer = new byte[expectedPlain.getBytes().length];

		dInStream.read(buffer);
		dInStream.close();

		// Read the decrypted data and check if it matches the original one
		String actualPlain = new String(buffer);

		assertEquals("Symmetry broken!", expectedPlain, actualPlain);
	}
}
