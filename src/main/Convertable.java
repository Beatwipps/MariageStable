package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Convertable {

	public Map<String, List<String>> convert(Map<String, ArrayList<List<String>>> map);
}
