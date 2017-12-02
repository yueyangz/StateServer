import java.awt.geom.Path2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class StateDecider {
	
	//Map that stores states and their border data
	//Hard-coded constants for parsing JSON file
	private Map<String, Path2D> map;
	static final int PREFIX_LENGTH = 11, BORDER = 9;
	
	public StateDecider() throws FileNotFoundException {
		map = new HashMap<>();
		buildMap();
	}
	
	/**
	 * One time work to build the borders of the United States, i.e. the collections of 50 states
	 * @throws FileNotFoundException
	 */
	private void buildMap() throws FileNotFoundException {
		File f = new File("states.json");
		if (!f.exists()) throw new FileNotFoundException();
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			int divide = line.indexOf("\"border\"");
			String state = getState(line, divide);
			Path2D border = getBorder(line, divide);
			map.put(state, border);
		}
		sc.close();
	}
	
	/**
	 * Extract state name from one line of JSON file
	 * @param raw
	 * @param divide
	 * @return
	 */
	private String getState(String raw, int divide) {
		String first = raw.substring(0, divide);
		return first.substring(PREFIX_LENGTH, first.length() - 3).trim();
	}
	
	/**
	 * Get the border of a state from one line of JSON file
	 * @param raw
	 * @param divide
	 * @return
	 */
	private Path2D getBorder(String raw, int divide) {
		String second = raw.substring(divide, raw.length());
		String arr = second.substring(BORDER);
		String cleaned = arr.trim().replaceAll("\\[|\\]|\\}", "");
		String[] numbers = cleaned.split(",");
		Path2D p2d = new Path2D.Double();
		double initX = Double.parseDouble(numbers[0].trim());
		double initY = Double.parseDouble(numbers[1].trim());
		p2d.moveTo(initX, initY);
		for (int i = 2; i < numbers.length; i += 2) {
			double val1 = Double.parseDouble(numbers[i].trim());
			double val2 = Double.parseDouble(numbers[i + 1].trim());
			p2d.lineTo(val1, val2);
		}
		p2d.closePath();
		return p2d;
	}
	
	/**
	 * Check if a point is inside any state or not
	 * @param qs
	 * @return
	 */
	public String decide(QueryString qs) {
		double longitude = Double.parseDouble(qs.getLong());
		double latitude = Double.parseDouble(qs.getLat());	
		for (Entry<String, Path2D> e: map.entrySet()) {		//Here, there could have been some improvement 
			Path2D state = e.getValue();						//Might not need to loop through all states
			if (state.contains(longitude, latitude)) return e.getKey();
		}
		return "Not in any U.S. state";
	}
	
}
