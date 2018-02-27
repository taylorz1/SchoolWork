package problem;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EncryptTextEditor extends FilterOutputStream {
	
	IEncryption e;

	public EncryptTextEditor(OutputStream out, IEncryption e) {
		super(out);
		this.e = e;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		super.close();
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		super.flush();
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		for (int i = off; i < len+off; i++) {
			write((int) b[i]);
		}
	}

	@Override
	public void write(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		for (int i = 0; i < b.length; i++) {
			write((int) b[i]);
		}
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		byte a = ((byte) b);
		super.write(this.e.encrypt((char) a));
	}
	
	
	

}
