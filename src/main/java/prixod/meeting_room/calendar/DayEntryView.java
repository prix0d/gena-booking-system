package prixod.meeting_room.calendar;

import com.calendarfx.model.Entry;
import javafx.scene.control.Skin;

public class DayEntryView extends com.calendarfx.view.DayEntryView {
    public DayEntryView(Entry<?> entry) {
        super(entry);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new DayEntryViewSkin(this);
    }
}
