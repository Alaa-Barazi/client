package data;

import java.io.Serializable;

public class ResponseWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private Object data;

	public ResponseWrapper(String type, Object data) {
		this.type = type;
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public Object getData() {
		return data;
	}

}
