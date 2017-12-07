import java.util.ArrayList;

public class Parser
{
    public ArrayList<String> parse(String string){
	int noElements = 0;
	ArrayList<String> data = new ArrayList();
	char cursor;
	String newElement = "";
	data.add(string.charAt(0) + "");
	
	for(int i = 2; i<string.length(); i++){
	    cursor = string.charAt(i);
	    if(cursor != ';'){
		newElement += cursor;
	    }
	    else{
		String addElement = new String();
		addElement = newElement;
		newElement = "";
		data.add(addElement);
	    }
	}
	return data;
    }
}
