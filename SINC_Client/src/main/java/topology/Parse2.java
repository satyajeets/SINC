package topology;
import java.lang.reflect.Type;
import java.util.ArrayList;

import packetObjects.DataObj;
import packetObjects.IntrestObj;
import packetObjects.LinkObj;
import packetObjects.PrefixListObj;
import packetObjects.PrefixObj;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * This class parses raw packets into there corresponding object
 * @author spufflez
 *
 */
public class Parse2 {

	Gson gson = new Gson();

	public Parse2(){

	}



	/**
	 * Parses a prefix packet 
	 * @param jsonObject
	 * @param originalPacket
	 * @return prefix object 
	 * @throws Exception
	 */
	public PrefixObj parsePrefixJson(JsonObject jsonObject, String originalPacket) throws Exception{

		//JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		JsonElement jsonContentNameElement = jsonObject.get("prefix");
		String contentName = jsonContentNameElement.getAsString();

		JsonElement jsonIDElement = jsonObject.get("msgID");
		String msgID = jsonIDElement.getAsString();

		JsonElement jsonAddRemoveElement = jsonObject.get("addRemove");
		boolean addRemove = jsonAddRemoveElement.getAsBoolean();

		JsonElement jsonAdvertiserElement = jsonObject.get("advertiser");
		String advertiser = jsonAdvertiserElement.getAsString();

		PrefixObj prefixInfo = new PrefixObj(contentName, msgID, advertiser, addRemove, originalPacket);
		return prefixInfo;
	}


	/**
	 * Parses an interest packet 
	 * @param jsonObject
	 * @param originalPacket
	 * @return interest object 
	 * @throws Exception
	 */
	public IntrestObj parseIntrestJson(JsonObject jsonObject, String originalPacket) throws Exception{

		//JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		JsonElement jsonContentNameElement = jsonObject.get("contentName");
		String contentName = jsonContentNameElement.getAsString();

		JsonElement jsonSenderNameElement = jsonObject.get("originRouter");
		String senderName = jsonSenderNameElement.getAsString();

		JsonElement jsonNonceElement = jsonObject.get("nonce");
		int nonce = jsonNonceElement.getAsInt();

		IntrestObj intrestInfo = new IntrestObj(contentName, senderName, nonce, originalPacket);
		return intrestInfo;
	}

	/**
	 * Parses a data packet 
	 * @param jsonObject
	 * @param originalPacket
	 * @return data object 
	 * @throws Exception
	 */
	public DataObj parseDataJson(JsonObject jsonObject, String originalPacket) throws Exception{

		//JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		JsonElement jsonContentNameElement = jsonObject.get("contentName");
		String contentName = jsonContentNameElement.getAsString();

		JsonElement jsonOriginRouterElement = jsonObject.get("originRouter");
		String originRouter = jsonOriginRouterElement.getAsString();

		JsonElement jsonFlagElement = jsonObject.get("flag");
		byte flag = jsonFlagElement.getAsByte();

		JsonElement jsonDataElement = jsonObject.get("data");
		String data = jsonDataElement.getAsString();

		JsonElement jsonCacheFlagElement = jsonObject.get("cacheFlag");
		byte cacheFlag = jsonCacheFlagElement.getAsByte();

		JsonElement jsonLastChunkElement = jsonObject.get("lastChunk");
		boolean lastChunk = jsonLastChunkElement.getAsBoolean();

		DataObj dataInfo = new DataObj(contentName, originRouter, flag, data, originalPacket, cacheFlag, lastChunk);
		return dataInfo; 
	}

	/**
	 * Parses a prefix list packet ( update containing multiple content names)
	 * @param jsonObject
	 * @return prefix list object
	 * @throws Exception
	 */
	public PrefixListObj parsePrefixListJson(JsonObject jsonObject) throws Exception{
		//JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

		JsonElement jsonIDElement = jsonObject.get("msgID");
		String msgID = jsonIDElement.getAsString();

		JsonElement jsonAddRemoveElement = jsonObject.get("addRemove");
		boolean addRemove = jsonAddRemoveElement.getAsBoolean();

		JsonElement jsonAdvertiserElement = jsonObject.get("advertiser");
		String advertiser = jsonAdvertiserElement.getAsString();

		JsonElement JE = jsonObject.get("prefixList");
		Type TYPE = new TypeToken<ArrayList<String>>(){}.getType();
		ArrayList<String> prefixList = new Gson().fromJson(JE.getAsString(), TYPE);

		PrefixListObj prefixListObj = new PrefixListObj(prefixList, advertiser, addRemove, msgID);
		return prefixListObj;
	}

	/**
	 * Parses a add client packet 
	 * @param jsonObject
	 * @return link object
	 * @throws Exception
	 */
	public LinkObj parseClientAddNodeJson(JsonObject jsonObject) throws Exception{

		//JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		JsonElement jsonNameElement = jsonObject.get("nodeName");
		String nodeName = jsonNameElement.getAsString();

		JsonElement jsonCostElement = jsonObject.get("cost");
		int cost = jsonCostElement.getAsInt();

		LinkObj linkObjInfo = new LinkObj(nodeName, cost);

		return linkObjInfo;

	}

	/**
	 * Parses a remove clinet packet 
	 * @param jsonObject
	 * @return link object 
	 * @throws Exception
	 */
	public LinkObj parseClientRemoveNodeJson(JsonObject jsonObject) throws Exception{
		//JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		JsonElement jsonNameElement = jsonObject.get("nodeName");
		String removeNodeName = jsonNameElement.getAsString();

		JsonElement jsonCostElement = jsonObject.get("cost");
		int cost = jsonCostElement.getAsInt();

		LinkObj linkObjInfo = new LinkObj(removeNodeName, cost);

		return linkObjInfo;
	}

	/**
	 * Parses an add link packet 
	 * @param jsonObject
	 * @return link object
	 * @throws Exception
	 */
	public LinkObj parseAddLink(JsonObject jsonObject) throws Exception{
		//JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

		JsonElement jsonNodeNameElement = jsonObject.get("nodeName");
		String nodeName = jsonNodeNameElement.getAsString();

		JsonElement jsonCostElement = jsonObject.get("cost");
		int cost = jsonCostElement.getAsInt();

		return new LinkObj(nodeName, cost);
	}


	/**
	 * Parses a remove link packet 
	 * @param jsonObject
	 * @return link object 
	 * @throws Exception
	 */
	public LinkObj parseRemoveLink(JsonObject jsonObject) throws Exception{
		//JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

		JsonElement jsonNodeNameElement = jsonObject.get("nodeName");
		String nodeName = jsonNodeNameElement.getAsString();

		JsonElement jsonCostElement = jsonObject.get("cost");
		int cost = jsonCostElement.getAsInt();

		return new LinkObj(nodeName, cost);
	}

	/**
	 * Parses a modify link packet 
	 * @param jsonObject
	 * @param originalPacket
	 * @return link object 
	 * @throws Exception
	 */
	public LinkObj parseModifyLink(JsonObject jsonObject, String originalPacket) throws Exception{
		//JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

		JsonElement jsonNodeNameElement = jsonObject.get("nodeName");
		String nodeName = jsonNodeNameElement.getAsString();

		JsonElement jsonCostElement = jsonObject.get("cost");
		int cost = jsonCostElement.getAsInt();

		return new LinkObj(nodeName, cost);
	}


}
