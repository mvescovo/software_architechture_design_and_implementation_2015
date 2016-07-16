/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author "Michael Vescovo - s3459317"
 *
 */
public class Response implements Serializable {
	private static final long serialVersionUID = -8453712624016389984L;
	private boolean success = false;
	
	public Response(boolean success) {
		this.success = success;
	}

	public boolean getResponse() {
		return success;
	}
}
