package problem;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DecryptTextEditor extends FilterInputStream {
	
	IDecryption e;

	public DecryptTextEditor(InputStream out, IDecryption e) {
		super(out);
		this.e = e;
	}

	@Override
	public int available() throws IOException {
		return super.available();
	}

	@Override
	public void close() throws IOException {
		super.close();
	}

	@Override
	public synchronized void mark(int arg0) {
		super.mark(arg0);
	}

	@Override
	public boolean markSupported() {
		return super.markSupported();
	}

	@Override
	public int read() throws IOException {
		int i = super.read();
		if (i == -1) {
			return i;
		}
		return e.decrypt((char) i);
	}

	@Override
	public int read(byte[] arg0, int arg1, int arg2) throws IOException {
		int toReturn = super.read(arg0, arg1, arg2);
		for (int i = arg1; i < toReturn; i++) {
			arg0[i] = (byte) e.decrypt((char) arg0[i]);
		}
		return toReturn;
	}

	@Override
	public int read(byte[] arg0) throws IOException {
		int a = super.read(arg0);
		return this.e.decrypt((char) a);
	}

	@Override
	public synchronized void reset() throws IOException {
		super.reset();
	}

	@Override
	public long skip(long arg0) throws IOException {
		return super.skip(arg0);
	}
}
