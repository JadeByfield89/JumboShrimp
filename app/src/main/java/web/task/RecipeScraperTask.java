package web.task;

import android.os.AsyncTask;
import android.util.Log;

import com.permoveo.apps.jumboshrimp.model.Recipe;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by byfieldj on 5/12/15.
 */

//This AsyncTask grabs and parses the HTML from a webpage using JSOUP
//It also constructs a recipe object using specific elements and returns that to the caller
public class RecipeScraperTask extends AsyncTask<String, Void, Recipe> {

    private static final String[] mElementsIngredients = {"Ingredients"};
    private static final String[] mElementsDirections = {"Instructions", "Directions", "Preparation Instructions"};

    private static final String DIV_TAG = "<div>";
    private static final String[] HEADER_TAGS = {"h0", "h1", "h2", "h3", "h4", "h5", "h6"};

    private static final String TAG = RecipeScraperTask.class.getSimpleName();

    @Override
    protected Recipe doInBackground(String... params) {

        //String url = params[0];

        String url = "http://www.bettycrocker.com/recipes/scrumptious-apple-pie/a41b6992-efb5-4b8a-998c-26d25f05e05a";

        try {
            Document page = Jsoup.connect(url).get();

            //Get the document title
            String title = page.title();
            Log.d(TAG, "Page Title: " + title);


            //Get the 'title' meta tag
            Elements metaTitle = page.select("meta[name=title]");
            Log.d(TAG, "Meta Title: " + metaTitle.text());


            //Get ALL the header tags on the page
            Elements headerTags = page.select("h0, h1, h2, h3, h4, h5, h6");

            //Print each header tag's name
            for (Element e : headerTags) {
                String headerText = e.text();
                //Log.d(TAG, "Header: " + headerText);

                //Look specifically for the ingredients element and get it's children
                if (headerText.equalsIgnoreCase(mElementsIngredients[0]) || headerText.contains(mElementsIngredients[0])) {
                    Log.d(TAG, "Ingredients element found! -> " + headerText);


                    Element nextElement = e.nextElementSibling();

                    //First, check the tag type of the next sibling element
                    String tagName = nextElement.tagName();
                    Log.d(TAG, "nextElement tag name: " + tagName);

                    //Check if the first sibling is a <div> tag
                    if(DIV_TAG.contains(tagName) || DIV_TAG.equals(tagName)){
                        Log.d(TAG, "Sibling element is of type DIV");

                        Element divSibling = nextElement.nextElementSibling();
                        String divSiblingName = divSibling.tagName();


                        Elements divChildren = divSibling.getAllElements();
                        Log.d(TAG, "Div children contains " + divChildren.size() + " elements!");


                        //TODO://Get all <dl> tags under this div(if any)


                        //Need to dig deeper for ingredients list
                        //Start by looking for a sibling <h>, and seeing if the ingredients are
                        //at that level
                        for(int h = 0; h < HEADER_TAGS.length; h++){
                            if(divSiblingName.contains(HEADER_TAGS[h])){
                                Log.d(TAG, "divSibling is of type HEADER");
                                Log.d(TAG, "divSibling name is " + divSibling.text());
                            }
                        }


                    }


                    //Now, check if the sibling element is an <h> tag
                    else {
                        for (int x = 0; x < HEADER_TAGS.length; x++) {
                            if (tagName.contains(HEADER_TAGS[x])) {
                                Log.d(TAG, "Sibling element is of type HEADING");
                            }
                        }
                    }





                    Elements childElements = e.getAllElements();
                    Log.d(TAG, "childElement size: " + childElements.size());
                    Log.d(TAG, "childElement text: " + childElements.text());
                    Log.d(TAG, "childElement html: " + childElements.html());

                    Log.d(TAG, "nextElement text: " + nextElement.text());
                    Log.d(TAG, "nextElement html: " + nextElement.html());


                    //Get the child count of the Ingredients element
                    //Log.d(TAG, "Ingredients element contains: " + e.g + " children");
                }

                //Now look for the instructions or ingredients element
                for (int i = 0; i < mElementsDirections.length; i++) {
                    if (headerText.equalsIgnoreCase(mElementsDirections[i])) {
                        Log.d(TAG, "Directions element found! -> " + headerText);

                        Elements childElements = e.getAllElements();
                        Log.d(TAG, "childElement size: " + childElements.size());


                        Element nextElement = e.nextElementSibling();
                        Log.d(TAG, "nextElement text: " + nextElement.text());
                        Log.d(TAG, "nextElement html: " + nextElement.html());

                        //Get the child count of the Directions element
                        //Log.d(TAG, "Directions element contains: " + e.childNodeSize() + " children");

                    }
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Recipe recipe) {
        super.onPostExecute(recipe);
    }
}
