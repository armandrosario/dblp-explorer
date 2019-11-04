import java.util.*; 
import java.util.stream.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;


public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException {
		Scanner input = new Scanner(System.in);
		JSONParser jsonparser=new JSONParser();
		try ( FileReader reader = new FileReader("C:\\Users\\arman_qia17n4\\eclipse-workspace\\Assignment5_4353\\src\\citation.json")){
			System.out.println("Enter keyword:");
			String keyword=input.next();
			keyword=keyword.toLowerCase();
			System.out.println("Enter k value:");
			int kValue=input.nextInt();
			PrintWriter writer = new PrintWriter("output.txt","UTF-8");
			//
			Object obj=jsonparser.parse(reader);
			JSONArray citations=new JSONArray();
			citations.add(obj);
			JSONArray keywordCitations=new JSONArray();
			JSONObject temp;
			String title="";
			writer.println("***Tier 1***");
			for(int i=0;i<citations.size();i++) {
				temp=(JSONObject)citations.get(i);
				title=(String)temp.get("title");
				title=title.toLowerCase();
				System.out.println(title);
				
				if(title.contains(keyword)) {
					writer.println(temp);
					keywordCitations.add(temp);
				}
			}
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void getTiers(JSONArray originalList,JSONArray keywordCitations,int k) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("output.txt","UTF-8");
		int currentTier=1;
		int index=0;
		String id="";
		String tempID="";
		JSONObject currObj=new JSONObject();
		JSONObject currCitations=new JSONObject();
		JSONArray tiers=new JSONArray();
		for(int i=0;i<keywordCitations.size();i++) {
			currentTier=1;
			currObj=(JSONObject)keywordCitations.get(i);
			while(currentTier<=k) {
				writer.println("**Tier" + " "+ Integer.toString(currentTier)+"***");
				currCitations=(JSONObject)currObj.get("citations");
				for(int j=0;i<currCitations.size();j++) {
					id=(String)currCitations.get(i);
					for(int a=0;a<originalList.size();a++) {
						JSONObject temp=(JSONObject)originalList.get(i);
						tempID=(String)temp.get("id");
						if(tempID.equals(id)) {
							writer.println(temp);
						}
					}
				}
				k++;
			}
		}
	}

}
