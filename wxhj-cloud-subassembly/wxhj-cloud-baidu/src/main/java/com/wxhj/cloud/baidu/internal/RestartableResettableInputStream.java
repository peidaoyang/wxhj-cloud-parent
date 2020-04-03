/** 
 * @fileName: RestartableResettableInputStream.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:15:53 
 */

package com.wxhj.cloud.baidu.internal;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;

import com.wxhj.cloud.baidu.BceClientException;

/**
 * @className RestartableResettableInputStream.java
 * @author pjf
 * @date 2020年2月28日 上午9:15:53   
*/
/**
 * @className RestartableResettableInputStream.java
 * @author pjf
 * @date 2020年2月28日 上午9:15:53
 */

public class RestartableResettableInputStream extends RestartableInputStream {
	private InputStream input;

	public RestartableResettableInputStream(InputStream input) {
		checkNotNull(input, "input should not be null.");
		checkArgument(input.markSupported(), "input does not support mark.");
		this.input = input;
	}

	@Override
	public void restart() {
		try {
			this.input.reset();
		} catch (IOException e) {
			throw new BceClientException("Fail to reset the underlying input stream.", e);
		}
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return this.input.read(b, off, len);
	}

	@Override
	public int read() throws IOException {
		return this.input.read();
	}

	@Override
	public void close() throws IOException {
		this.input.close();
	}
}
