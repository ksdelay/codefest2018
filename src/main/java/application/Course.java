package application;

import java.util.*;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.*;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

public class Course {
	private List<String> tags = new ArrayList<>();
	
	public Course(){
		
	}
	
	public boolean searchTag(String s){
		if (tags.contains(s)){
			return true;
		}
		else return false;
	}
	
	public void addTag(String tag){
		tags.add(tag);
	}
	
	public void getTags( String courseDescription) {
		NaturalLanguageUnderstanding NLUservice = startNLUservice();
		ConceptsOptions concepts = new ConceptsOptions.Builder()
				.limit(3).build();
		Features features = new Features.Builder()
				.concepts(concepts).build();
		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.text(courseDescription).features(features).build();
		AnalysisResults response = NLUservice.analyze(parameters).execute();
		for (int i = 0; i < 3; i++) {
			this.addTag(response.getConcepts().get(i).getText());
		}
		System.out.println(this.tags);
	}
	
	private static startNLUservice() {
		return new NaturalLanguageUnderstanding(
				  "2018-04-7",
				  "6dc0cf71-c1ef-4c0d-a2b1-989742aa5877",
				  "4fyitpGXB48Y"
				);
	}
}
