package work.ip.servlet;

import java.util.HashMap;
import java.util.Map;

public enum ServletOperation {
	ADD("/add"), DELETE("/delete"), UPDATE("/update");

	private static final Map<String, ServletOperation> lookup = new HashMap<String, ServletOperation>();

	static {
		for (ServletOperation d : ServletOperation.values()) {
			lookup.put(d.getValue(), d);
		}
	}

	private final String value;

	private ServletOperation(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ServletOperation get(String value) {
		return lookup.get(value);
	}

	@Override
	public String toString() {
		return getValue();
	}
}
