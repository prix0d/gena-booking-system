package prixod.meeting_room.calendar;

import com.calendarfx.model.Entry;
import com.calendarfx.view.DateControl;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputEvent;
import javafx.util.Callback;
import prixod.meeting_room.database.Meet;

public class ItemDetailsCallback implements Callback<DateControl.EntryDetailsParameter, Boolean> {
    @Override
    public Boolean call(DateControl.EntryDetailsParameter param) {
        InputEvent evt = param.getInputEvent();
        var entry = (Entry<Meet>) param.getEntry();

        if (evt instanceof ContextMenuEvent) {


            return true;
        }

        return true;
    }
}
