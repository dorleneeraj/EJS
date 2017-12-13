package interviews.druva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

	Map<String, Server> servers = new HashMap<String, Server>();
	Map<String, Property> propertyToMaxValueMap = new HashMap<String, Property>();
	Set<String> serversToPrint = new HashSet<String>();

	public static void main(String[] args) {
		Solution sol = new Solution();
		if (null != args && args.length > 0) {
			String filePath = args[0];
			if (null != filePath && !("").equals(filePath)) {
				try {
					sol.processFile(filePath);
					sol.printResult();
				} catch (FileNotFoundException fnfe) {
					System.out.println(Common.FILE_NOT_FOUND_EXCEPTION);
				} catch (IOException ioe) {
					System.out.println(Common.INPUT_OUTPUT_EXCEPTION);
				} catch (Exception e) {
					System.out.println(Common.UNEXPECTED_BEHAVIOUR);
					e.printStackTrace();
				}
			}
		}

	}

	private void printResult() {
		for (String s : serversToPrint) {
			System.out.println(s);
		}

	}

	public void processFile(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		String line;
		Server server;
		String type;
		String subType;
		String value;
		while ((line = reader.readLine()) != null) {
			String[] inputServer = line.split(Common.SPLIT_BY_COMMA);
			
			if (inputServer.length == 4) {
				String name = inputServer[0].trim();
				if (servers.containsKey(name)) {
					server = servers.get(name);
				} else {
					server = new Server(name);
					servers.put(name, server);
				}
				type = inputServer[1].trim();
				subType = inputServer[2].trim();
				value = inputServer[3].trim();
				server.addProperty(type, new Property(subType, value));

				// update property to max value map
				Property p = propertyToMaxValueMap.get(subType);
				if (null == p) {
					p = new Property(name, value);
					propertyToMaxValueMap.put(subType, p);

				} else {
					boolean isGreater = compareValues(value, p.getValue());
					if (isGreater) {
						Property p1 = new Property(name, value);
						propertyToMaxValueMap.put(subType, p1);
						serversToPrint.add(p.getName());
					} else {
						serversToPrint.add(name);
					}
				}

			}

		}
		reader.close();
	}

	private boolean compareValues(String val1, String val2) {
		String[] value1 = val1.split(Common.SPLIT_BY_DOT);
		String[] value2 = val2.split(Common.SPLIT_BY_DOT);
		for (int i = 0; i < value2.length; i++) {
			if (Integer.parseInt(value1[i]) < Integer.parseInt(value2[i])) {
				return false;
			} else if (Integer.parseInt(value1[i]) > Integer
					.parseInt(value2[i])) {
				return true;
			}
		}
		return false;
	}

	private class Common {
		public static final String FILE_NOT_FOUND_EXCEPTION = "The mentioned File is not present. Please Try Again!";
		public static final String UNEXPECTED_BEHAVIOUR = "Unexpected behaviour. Processing Stopped!";
		public static final String INPUT_OUTPUT_EXCEPTION = "Problem occurred while reading the file";
		public static final String SPLIT_BY_COMMA = ",";
		public static final String SPLIT_BY_DOT = "\\.";

	}

}

class Server {
	String serverName = "";
	Map<String, Property> typeToPropertyMap = new HashMap<String, Property>();

	public Server(String serverName) {
		this.serverName = serverName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Map<String, Property> getTypeToPropertyMap() {
		return typeToPropertyMap;
	}

	public void setTypeToPropertyMap(Map<String, Property> typeToPropertyMap) {
		this.typeToPropertyMap = typeToPropertyMap;
	}

	public void addProperty(String type, Property p) {
		typeToPropertyMap.put(type, p);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((serverName == null) ? 0 : serverName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Server other = (Server) obj;
		if (serverName == null) {
			if (other.serverName != null)
				return false;
		} else if (!serverName.equals(other.serverName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Server [serverName=" + serverName + ", typeToPropertyMap="
				+ typeToPropertyMap + "]";
	}

}

class Property {
	String name = "";
	String value = "";

	public Property(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Property [name=" + name + ", value=" + value + "]";
	}

}