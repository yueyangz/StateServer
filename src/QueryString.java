
public class QueryString {
	
	private String longitude = null;
	private String latitude = null;
	private String raw = null;
	
	/**
	 * Data container and parser for query string
	 * @param raw
	 */
	public QueryString(String raw) {
		this.raw = raw;
		extractCoordinates();
	}
	
	/**
	 * Parse the raw query string
	 */
	private void extractCoordinates() {
		if (raw == null) throw new IllegalArgumentException();
		String[] elements = raw.split("&");
		if (elements.length != 2) throw new IllegalArgumentException();
		for (String s: elements) {
			String[] each = s.split("=");
			if (each.length != 2) throw new IllegalArgumentException();
			if (each[0].equals("longitude")) this.longitude = each[1];
			else if (each[0].equals("latitude")) this.latitude = each[1];
			else throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Getter for longitude
	 * @return
	 */
	public String getLong() {
		return longitude;
	}
	
	/**
	 * Getter for latitude
	 * @return
	 */
	public String getLat() {
		return latitude;
	}
	
	
	
	
}
