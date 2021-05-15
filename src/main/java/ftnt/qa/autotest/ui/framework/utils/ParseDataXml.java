package ftnt.qa.autotest.ui.framework.utils;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseDataXml {
	
	//private static String testDataFielsuffix = ".xml";
	private static String dataNodeTag = "data-node";
	private static String dataNodeID = "id";
	private static String dataTag = "data";
	private static String dataKey = "key";
	private static String dataValue = "value";

	public static HashMap<String, String> getDataNode(String dataNodeIds) throws DocumentException {
		String[] dataNodeId = dataNodeIds.split(",");
		HashSet<String> nodeSet = new HashSet<String>();
		for (String str : dataNodeId) {
			nodeSet.add(str);
		}
		HashMap<String, String> dataMap = new HashMap<String, String>();
		ArrayList<File> fs = InitPropertiesUtil.getConfigFiles(new File(PropertiesUtil.getProValue("testcase.testdata.path")));
		for (File f : fs) {
			setDataNodeMapFromFile(f, nodeSet, dataMap);
		}
		return dataMap;
	}

	@SuppressWarnings("unchecked")
	public static void setDataNodeMapFromFile(File f, HashSet<String> nodeSet, HashMap<String, String> dataMap)
			throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(f);
		Element root = document.getRootElement();
		List<Element> childList = root.elements(dataNodeTag);
		for (Element e : childList) {
			if (nodeSet.contains(e.attributeValue(dataNodeID))) {
				List<Element> dataList = e.elements(dataTag);
				for (Element de : dataList) {
					dataMap.put(de.attributeValue(dataKey), de.attributeValue(dataValue));
				}
				nodeSet.remove(e.attributeValue(dataNodeID));
			}
		}
		return;
	}
}