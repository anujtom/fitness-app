package seemoo.fitbit.information;

import android.widget.ListView;

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import seemoo.fitbit.miscellaneous.DumpGraphDataPoints;
import seemoo.fitbit.miscellaneous.DumpListItem;

/**
 * A list of information.
 */
public class InformationList {

    private final String TAG = this.getClass().getSimpleName();

    private ArrayList<DumpListItem> list = new ArrayList<>();
    private String name;
    private boolean alreadyUploaded = false;

    private DumpGraphDataPoints steps = null;
    private int stepPos = 0;

    /**
     * Creates a list of information.
     *
     * @param name The name of the list.
     */
    public InformationList(String name) {
        this.name = name;
    }

    public void initSteps(int size) {
        steps = new DumpGraphDataPoints(size);
    }

    public void addStep(DataPoint dataPoint) {
        if(steps!=null) {
            steps.addDataPoint(stepPos++,dataPoint);
        }
    }

    public int getStepCount() {
        return stepPos;
    }

    public DumpGraphDataPoints getSteps() {
        return steps;
    }

    public void addAllDataPoints(DataPoint[] dataPoints) {
        steps = new DumpGraphDataPoints(dataPoints);
    }

    public void addItemFinally(){
        steps.setType(DumpListItem.GRAPH_VIEW);
        list.add(steps);
    }

    /**
     * Adds a piece of information to this list.
     *
     * @param information The information to add.
     */
    public void add(Information information) {
        list.add(information);
    }

    /**
     * Adds all pieces of information of the given information list to this list.
     *
     * @param informationList The information list containing the pieces to add.
     */
    public void addAll(InformationList informationList) {
        for (int i = 0; i < informationList.size(); i++) {
            list.add(informationList.get(i));
        }
        if (informationList.getStepCount() != 0) {
            steps = informationList.getSteps();
            stepPos = informationList.getStepCount();
        }
    }

    /**
     * The size of this information list.
     *
     * @return The size.
     */
    public int size() {
        return list.size();
    }

    /**
     * The list of this information list.
     *
     * @return The list.
     */
    public ArrayList<DumpListItem> getList() {
        return list;
    }

    //

    /**
     * Overrides the current list with the one given.
     * Use only for information lists which should be displayed on as ListView.
     *
     * @param informationList The information list with the new data.
     * @param listView        The list view object to show the data.
     */
    public void override(InformationList informationList, ListView listView) {
        list.clear();
        list.addAll(informationList.getList());
        listView.invalidateViews();
    }

    /**
     * Sets a piece of information into the list at a certain position.
     *
     * @param position    The position to set the piece of information.
     * @param information The information to set.
     */
    public void set(int position, Information information) {
        list.set(position, information);
    }

    /**
     * Returns a piece of information from the given position in the list.
     *
     * @param position The position of the information.
     * @return The information.
     */
    public DumpListItem get(int position) {
        return list.get(position);
    }

    /**
     * Returns the name of this list.
     *
     * @return The name of this list.
     */
    public String getName() {
        return name;
    }

    /**
     * Removes a piece of information from this list at the given position.
     *
     * @param position The position to remove.
     */
    public void remove(int position) {
        list.remove(position);
    }

    /**
     * Removes several pieces of information from the start to the end position.
     *
     * @param start The start position.
     * @param end   The end position.
     */
    public void remove(int start, int end) {
        for (int i = 0; i < end - start; i++) {
            remove(start);
        }
    }

    /**
     * Concatenates all information of this list and returns it as string.
     *
     * @return The concatenated string.
     */
    public String getData() {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result = result + list.get(i);
        }
        return result;
    }

    /**
     * Concatenates all information of this list in a beauty way and returns it as string.
     *
     * @return The beautiful concatenated string.
     */
    public String getBeautyData() {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result = result + list.get(i) + "\n";
        }
        return result;
    }

    /**
     * Returns the position of a piece of information in this list.
     *
     * @param information The information.
     * @return The position of the information.
     */
    public int getPosition(Information information) {
        return list.indexOf(information);
    }

    /**
     * Returns the value of already uploaded.
     * This variable shows, if this information was already uploaded to the fitbit server in the past.
     *
     * @return The value of already uploaded.
     */
    public boolean getAlreadyUploaded() {
        return alreadyUploaded;
    }

    /**
     * Sets the value of already uploaded.
     * This variable shows, if this information was already uploaded to the fitbit server in the past.
     *
     * @param value The value to set already uploaded to.
     */
    public void setAlreadyUploaded(boolean value) {
        alreadyUploaded = value;
    }
}
