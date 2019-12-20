package life.client.StorageItems.GWTStorage;

import com.google.gwt.event.dom.client.DragEnterEvent;
import com.google.gwt.event.dom.client.DragEnterHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Represents a column view as a VerticalPanel in the game FreeCell
 * @author Alex Life
 *
 */
public class GWTColumn extends VerticalPanel {
	private String name = "column ";
	
	/**
	 * Instantiates a new column, taking in a parameter for identification.
	 * @param i number of column for identification
	 */
	public GWTColumn(int i) {
		super();
		
		name = name + i;
		
		this.addDomHandler(new DragStartHandler() {

			@Override
			public void onDragStart(DragStartEvent event) {
				event.setData("source", name);
			}
			
		}, DragStartEvent.getType());
		
		this.addDomHandler(new DragEnterHandler() {

			@Override
			public void onDragEnter(DragEnterEvent event) {
				event.setData("destination", name);
			}
		}, DragEnterEvent.getType());
	}
}
