package life.client.StorageItems.GWTStorage;

import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Represents a foundation view as a VerticalPanel in the game FreeCell
 * @author Alex Life
 *
 */
public class GWTFoundation extends VerticalPanel {
	private String name = "foundation ";
	
	/**
	 * Instantiates a new foundation, taking in a parameter for identification.
	 * @param i number of foundation for identification
	 */
	public GWTFoundation(int i) {
		super();
		
		name = name + i;
		
		this.addDomHandler(new DragStartHandler() {

			@Override
			public void onDragStart(DragStartEvent event) {
				event.setData("source", name);
			}
			
		}, DragStartEvent.getType());
	}
}
