/** 
 * @fileName: RestartableNonResettableInputStream.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:13:23 
 */

package com.wxhj.cloud.baidu.internal;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @className RestartableNonResettableInputStream.java
 * @author pjf
 * @date 2020年2月28日 上午9:13:23   
*/
/** 
 * @className RestartableNonResettableInputStream.java
 * @author pjf
 * @date 2020年2月28日 上午9:13:23 
*/

import java.io.IOException;
import java.io.InputStream;

import com.wxhj.cloud.baidu.BceClientException;

public class RestartableNonResettableInputStream extends RestartableInputStream {

	private byte[] buffer;

	private int offset = 0;

	private int length = 0;

	private boolean eof = false;

	private InputStream input;

	public RestartableNonResettableInputStream(InputStream input, int bufferSize) {
		checkNotNull(input, "input should not be null.");
		checkArgument(bufferSize >= 0, "bufferSize should not be negative: " + bufferSize);
		this.buffer = new byte[bufferSize];
		this.input = input;
		while (this.length < bufferSize) {
			int count;
			try {
				count = this.input.read(this.buffer, this.length, bufferSize - this.length);
			} catch (IOException e) {
				throw new BceClientException("Fail to read data from input.", e);
			}
			if (count < 0) {
				this.eof = true;
				break;
			}
			this.length += count;
		}
	}

	@Override
	public void restart() {
		if (this.buffer == null) {
			throw new IllegalStateException("Fail to restart. Input buffer exhausted.");
		}
		this.offset = 0;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		checkNotNull(b, "b should not be null.");
		if (off < 0 || len < 0 || len > b.length - off) {
			throw new IndexOutOfBoundsException();
		}
		if (len == 0) {
			return 0;
		}
		if (this.offset < this.length) {
			int copyLength = this.length - this.offset;
			if (copyLength > len) {
				copyLength = len;
			}
			System.arraycopy(this.buffer, this.offset, b, off, copyLength);
			this.offset += copyLength;
			return copyLength;
		}
		if (this.eof) {
			return -1;
		}
		int result = this.input.read(b, off, len);
		if (result < 0) {
			this.eof = true;
			return -1;
		}
		this.buffer = null;
		return result;
	}

	@Override
	public int read() throws IOException {
		if (this.offset < this.length) {
			return this.buffer[this.offset++] & 0xff;
		}
		if (this.eof) {
			return -1;
		}
		int result = this.input.read();
		if (result < 0) {
			this.eof = true;
			return -1;
		}
		this.buffer = null;
		return result;
	}

	@Override
	public void close() throws IOException {
		this.input.close();
	}
}
