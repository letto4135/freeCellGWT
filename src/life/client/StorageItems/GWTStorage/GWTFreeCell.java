package life.client.StorageItems.GWTStorage;

import com.google.gwt.event.dom.client.DragEnterEvent;
import com.google.gwt.event.dom.client.DragEnterHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Represents a freecell view as a VerticalPanel in the game FreeCell.
 * @author Alex Life
 *
 */
public class GWTFreeCell extends VerticalPanel {
	private String name = "freecell ";
	
	/**
	 * Instantiates a new freecell, taking in a parameter for identification.
	 * @param i number of freecell for identification
	 */
	public GWTFreeCell(int i) {
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
