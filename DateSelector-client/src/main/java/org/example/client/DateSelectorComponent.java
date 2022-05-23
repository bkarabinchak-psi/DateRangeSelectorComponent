package org.example.client;

import com.inductiveautomation.factorypmi.application.components.PMIDateTimePopupSelector;
import com.inductiveautomation.factorypmi.application.components.PMIComboBox;
import com.inductiveautomation.ignition.common.Dataset;
import com.inductiveautomation.ignition.common.script.builtin.DateUtilities;
import com.inductiveautomation.ignition.common.util.DatasetBuilder;
import com.inductiveautomation.vision.api.client.components.model.AbstractVisionPanel;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

//Should this be extending AbstractVisionPanel instead?
public class DateSelectorComponent extends AbstractVisionPanel {

//    private final JPanel container;
    private final JLabel dropdownLabel;
    private final PMIComboBox dateSelections;
    private final JLabel startDateLabel;
    private final JLabel toLabel;
    private final PMIDateTimePopupSelector startDate;
    private final PMIDateTimePopupSelector endDate;
    //TODO list of ints and strings instead of string and strings
    private final Dataset dataSelectionOptions;
    private final String[] dateSelectionOptionsStr = {"All", "Today", "This Week", "This Month", "This Year", "Last Week", "Last Month", "Last Year", "Last 3 Months", "Last 6 Months", "Last 12 Months", "Two Week Period", "Post Sept. 2014"};
    private final String formattedDateString = "MM/dd/yyyy";

    public DateSelectorComponent() {
        //Leftover from archetype example.  setPreferredSize seems to do stuff, but unsure what else is appropriate to go here over onStartup.
        setFont(new Font("Dialog", Font.PLAIN, 16));
        //Dropdown row
        dropdownLabel = new JLabel("Date:");
        this.setLayout(new MigLayout("wrap 4"));
        dateSelections = new PMIComboBox();
        DatasetBuilder db = new DatasetBuilder().newBuilder();
        db.colNames("Value", "Label").colTypes(Integer.class, String.class);
        for (int i=0; i < dateSelectionOptionsStr.length; i++) {
            db.addRow(i, dateSelectionOptionsStr[i]);
        };
        dataSelectionOptions = db.build();
        dateSelections.setData(dataSelectionOptions);
        dateSelections.setSelectedLabel("All");
        this.add(dropdownLabel);
        this.add(dateSelections, "span 3");
        //Datetime row
        startDateLabel = new JLabel("Start Date:");
        startDate = new PMIDateTimePopupSelector();
        startDate.setFormat(formattedDateString);
        toLabel = new JLabel("to");
        endDate = new PMIDateTimePopupSelector();
        endDate.setFormat(formattedDateString);
        this.add(startDateLabel);
        this.add(startDate);
        this.add(toLabel);
        this.add(endDate);

        //Is this necessary?
        this.setVisible(true);
        dateSelections.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentSelection = String.valueOf(dateSelections.getItemAt(dateSelections.getSelectedIndex()));
                System.out.println("Selected " + currentSelection);
                calculateDates(currentSelection);
            }
        });
    }

    private void calculateDates(@NotNull String dateSelection) {
        switch(dateSelection){
            case "All":
                calculateAllTime();
                break;
            case "Today":
                calculateToday();
                break;
            case "This Week":
                calculateThisWeek();
                break;
        }
    }

    private void calculateToday() {
        DateUtilities du = new DateUtilities();
        Date now = du.now();
        Date lastMidnight = du.midnight(now);
        Date tomorrowMidnight = du.midnight(du.addDays(now, 1));
        startDate.setDate(lastMidnight);
        endDate.setDate(tomorrowMidnight);
    }

    private void calculateAllTime() {
        startDate.setDate(new Date(0L));
        DateUtilities du = new DateUtilities();
        Date now = du.now();
        endDate.setDate(now);
    }

    private void calculateThisWeek() {
        DateUtilities du = new DateUtilities();
//        Date beginningOfWeek = du.midnight(du.addDays(du.now(),));
        //TODO Figure out getting start to end of week - think I need to use Joda directly
    }

    protected void onStartup() {

    }

    protected void onShutdown() {

    }
}