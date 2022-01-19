package prixod.meeting_room.calendar;

import com.calendarfx.view.DateControl;
import javafx.scene.control.ContextMenu;
import javafx.util.Callback;

public class ContextMenuProvider extends com.calendarfx.view.ContextMenuProvider {
    @Override
    protected ContextMenu getWeekDayViewMenu(DateControl.ContextMenuParameter param) {
        var menu = super.getWeekDayViewMenu(param);

        menu.getItems().remove(1,5);

        return menu;
    }
}
